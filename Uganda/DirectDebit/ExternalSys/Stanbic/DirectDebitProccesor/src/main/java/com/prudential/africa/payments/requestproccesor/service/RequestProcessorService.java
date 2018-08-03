package com.prudential.africa.payments.requestproccesor.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prudential.africa.payments.requestproccesor.dao.entities.DirectDebitTransaction;
import com.prudential.africa.payments.requestproccesor.dao.repository.DirectDebitTransactionRepository;
import com.prudential.payments.stanbic.directdebit.pain008.AccountIdentification3Choice;
import com.prudential.payments.stanbic.directdebit.pain008.CashAccount7;
import com.prudential.payments.stanbic.directdebit.pain008.CashAccountType2;
import com.prudential.payments.stanbic.directdebit.pain008.CashAccountType4Code;
import com.prudential.payments.stanbic.directdebit.pain008.DirectDebitTransactionInformation1;
import com.prudential.payments.stanbic.directdebit.pain008.Document;
import com.prudential.payments.stanbic.directdebit.pain008.GroupHeader1;
import com.prudential.payments.stanbic.directdebit.pain008.Grouping1Code;
import com.prudential.payments.stanbic.directdebit.pain008.Pain00800101;
import com.prudential.payments.stanbic.directdebit.pain008.PartyIdentification8;
import com.prudential.payments.stanbic.directdebit.pain008.PaymentInstructionInformation2;
import com.prudential.payments.stanbic.directdebit.pain008.PaymentMethod2Code;
import com.prudential.payments.stanbic.directdebit.pain008.SimpleIdentificationInformation2;

@Service
public class RequestProcessorService {

	
	@Autowired
	private DirectDebitTransactionRepository directDebitTransactionRepository;
	
	public Document prepareDocumentForStanbicTransactions() throws DatatypeConfigurationException
	{
		Iterable<DirectDebitTransaction> directDebitTransactions = directDebitTransactionRepository.findAll();
		
		List<DirectDebitTransactionInformation1> directDebitTransactionInformations = new ArrayList<>();
		BigDecimal totalCollection = BigDecimal.ZERO;
		
		for (DirectDebitTransaction directDebitTransaction : directDebitTransactions)
		{
			DirectDebitTransactionInformation1 directDebitTransactionInformation = DirectDebitTransactionInformationMapper.mapDirectDebitTransaction(directDebitTransaction);
			directDebitTransactionInformations.add(directDebitTransactionInformation);
			totalCollection.add(directDebitTransaction.getTransactionAmount());
		}
		
		
		PaymentInstructionInformation2 paymentInstructionInformation = new PaymentInstructionInformation2();
		
		paymentInstructionInformation.setPmtInfId(UUID.randomUUID().toString());//Generate human readable text for this column should be no of policies and bank and date
		paymentInstructionInformation.setPmtMtd(PaymentMethod2Code.DD);
		XMLGregorianCalendar dateOfTheTransaction = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()); 
		paymentInstructionInformation.setReqdColltnDt(dateOfTheTransaction);//This should indicate the date of the batch
		
		//Creditor information
		
		PartyIdentification8 creditor = new PartyIdentification8();
		creditor.setNm("Prudential Uganda Assurance Limited");//Country registered party name from property
		paymentInstructionInformation.setCdtr(creditor);
		
		//Creditor Account information
		CashAccount7 creditorAccount = new CashAccount7();
		AccountIdentification3Choice creditorAccountIdentifier = new AccountIdentification3Choice();
		SimpleIdentificationInformation2 prAcc = new SimpleIdentificationInformation2();
		prAcc.setId("1234567890");//Get the creditor account from a property
		creditorAccountIdentifier.setPrtryAcct(prAcc);
		creditorAccount.setId(creditorAccountIdentifier);
		CashAccountType2 creditorAccountType = new CashAccountType2();
		creditorAccountType.setCd(CashAccountType4Code.COMM);//Get the creditor account type from a property
		creditorAccount.setTp(creditorAccountType);
		
		
		paymentInstructionInformation.setCdtrAcct(creditorAccount);
		
		paymentInstructionInformation.getDrctDbtTxInf().addAll(directDebitTransactionInformations);//Direct Debit Instructions
		List<PaymentInstructionInformation2>  paymentInformations = new ArrayList<>();
		paymentInformations.add(paymentInstructionInformation);
		
		GroupHeader1 groupHeader = new GroupHeader1();
		groupHeader.setMsgId(UUID.randomUUID().toString());
		XMLGregorianCalendar dateCreated = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()); 
		groupHeader.setCreDtTm(dateCreated);
		groupHeader.setGrpg(Grouping1Code.SNGL);
		groupHeader.getAuthstn().add("Stanbic Payment Processor");//Get this from a property file
		
		groupHeader.setNbOfTxs(Integer.valueOf(directDebitTransactionInformations.size()).toString());
		groupHeader.setCtrlSum(totalCollection);//This is the actual sum that is expected
		
		PartyIdentification8 initgPty = new PartyIdentification8();
		initgPty.setNm("Prudential Uganda Assurance Limited");//Country registered party name from property
		groupHeader.setInitgPty(initgPty);
		
		Pain00800101 directDebitInstructionMessage = new Pain00800101();
		directDebitInstructionMessage.setGrpHdr(groupHeader);
		directDebitInstructionMessage.getPmtInf().addAll(paymentInformations);
		
		Document doc = new Document();
		doc.setPain00800101(directDebitInstructionMessage);
		
		return doc;
	}
}
