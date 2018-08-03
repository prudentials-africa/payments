package com.prudential.africa.payments.requestproccesor.service;

import com.prudential.africa.payments.requestproccesor.dao.entities.DirectDebitTransaction;
import com.prudential.payments.stanbic.directdebit.pain008.AccountIdentification3Choice;
import com.prudential.payments.stanbic.directdebit.pain008.CashAccount7;
import com.prudential.payments.stanbic.directdebit.pain008.CashAccountType2;
import com.prudential.payments.stanbic.directdebit.pain008.CashAccountType4Code;
import com.prudential.payments.stanbic.directdebit.pain008.CurrencyAndAmount;
import com.prudential.payments.stanbic.directdebit.pain008.DirectDebitTransactionInformation1;
import com.prudential.payments.stanbic.directdebit.pain008.PartyIdentification8;
import com.prudential.payments.stanbic.directdebit.pain008.PaymentIdentification1;
import com.prudential.payments.stanbic.directdebit.pain008.SimpleIdentificationInformation2;


public class DirectDebitTransactionInformationMapper {

	
	public static DirectDebitTransactionInformation1 mapDirectDebitTransaction(DirectDebitTransaction directDebitTransaction)
	{
		DirectDebitTransactionInformation1 drctDbtTx = new DirectDebitTransactionInformation1();
		
		//Payment Identification
		PaymentIdentification1 paymentIdentification = new PaymentIdentification1();
		paymentIdentification.setEndToEndId(directDebitTransaction.getId().toString());//Unique identifier for the transaction we should receive this back
		drctDbtTx.setPmtId(paymentIdentification);
		
		//Currency and the Debit Amount
		CurrencyAndAmount instAmt = new CurrencyAndAmount();
		instAmt.setValue(directDebitTransaction.getTransactionAmount());//
		instAmt.setCcy(directDebitTransaction.getTransactionCurrency());//Check for mapping between the currency codes
		drctDbtTx.setInstdAmt(instAmt);
		
		//Debtor Identification
		PartyIdentification8 debtorInformation = new PartyIdentification8();
		debtorInformation.setNm(directDebitTransaction.getPayeeFirstName());
		debtorInformation.setCtryOfRes(directDebitTransaction.getPayeeAddressCountry());
		drctDbtTx.setDbtr(debtorInformation);
		
		// Map address here
		
		//Debtor Account
		CashAccount7 debtorAccountDetails = new CashAccount7();
		AccountIdentification3Choice debtorAccountIdentifier = new AccountIdentification3Choice();
		SimpleIdentificationInformation2 prtryAcc = new SimpleIdentificationInformation2();
		prtryAcc.setId(directDebitTransaction.getPayeeBankAccountNumber());//Payee Bank account number
		debtorAccountIdentifier.setPrtryAcct(prtryAcc);
		debtorAccountDetails.setId(debtorAccountIdentifier);
		CashAccountType2 payeeAccountType = new CashAccountType2();
		
		payeeAccountType.setCd(CashAccountType4Code.valueOf("SVGS"));//Map to directDebitTransaction.getPayeeBankAccountType()
		debtorAccountDetails.setTp(payeeAccountType);
		
		drctDbtTx.setDbtrAcct(debtorAccountDetails);
		
		return drctDbtTx;
		
	}
}
