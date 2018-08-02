package com.prudential.africa.payments.xmlprocessor;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.prudential.payments.stanbic.directdebit.pain008.AccountIdentification3Choice;
import com.prudential.payments.stanbic.directdebit.pain008.BranchAndFinancialInstitutionIdentification3;
import com.prudential.payments.stanbic.directdebit.pain008.CashAccount7;
import com.prudential.payments.stanbic.directdebit.pain008.CashAccountType2;
import com.prudential.payments.stanbic.directdebit.pain008.CashAccountType4Code;
import com.prudential.payments.stanbic.directdebit.pain008.ClearingChannel2Code;
import com.prudential.payments.stanbic.directdebit.pain008.ClearingSystemMemberIdentification3Choice;
import com.prudential.payments.stanbic.directdebit.pain008.CurrencyAndAmount;
import com.prudential.payments.stanbic.directdebit.pain008.DirectDebitTransactionInformation1;
import com.prudential.payments.stanbic.directdebit.pain008.Document;
import com.prudential.payments.stanbic.directdebit.pain008.FinancialInstitutionIdentification3;
import com.prudential.payments.stanbic.directdebit.pain008.FinancialInstitutionIdentification5Choice;
import com.prudential.payments.stanbic.directdebit.pain008.GroupHeader1;
import com.prudential.payments.stanbic.directdebit.pain008.Grouping1Code;
import com.prudential.payments.stanbic.directdebit.pain008.Pain00800101;
import com.prudential.payments.stanbic.directdebit.pain008.PartyIdentification8;
import com.prudential.payments.stanbic.directdebit.pain008.PaymentCategoryPurpose1Code;
import com.prudential.payments.stanbic.directdebit.pain008.PaymentIdentification1;
import com.prudential.payments.stanbic.directdebit.pain008.PaymentInstructionInformation2;
import com.prudential.payments.stanbic.directdebit.pain008.PaymentMethod2Code;
import com.prudential.payments.stanbic.directdebit.pain008.PaymentTypeInformation2;
import com.prudential.payments.stanbic.directdebit.pain008.Priority2Code;
import com.prudential.payments.stanbic.directdebit.pain008.RemittanceInformation1;
import com.prudential.payments.stanbic.directdebit.pain008.SimpleIdentificationInformation2;

public class CreateXML {

	public static void main(String args[]) throws Exception {
		Document doc = new Document();
		
		Pain00800101 pain = new Pain00800101();
		GroupHeader1 grpHd = new GroupHeader1();
		grpHd.setMsgId("msgId");
		XMLGregorianCalendar xmlGrCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()); 
		grpHd.setCreDtTm(xmlGrCal);
		List<String> authstn = grpHd.getAuthstn();
		authstn.add("auth");
		grpHd.setNbOfTxs("nbOftxs");
		grpHd.setGrpg(Grouping1Code.valueOf("SNGL"));
		PartyIdentification8 initgPty = new PartyIdentification8();
		initgPty.setNm("name");
		grpHd.setInitgPty(initgPty);
		pain.setGrpHdr(grpHd);
		
		List<PaymentInstructionInformation2> pmtInfList = pain.getPmtInf();
		PaymentInstructionInformation2 pmtInf = new PaymentInstructionInformation2();
		pmtInf.setPmtInfId("pmtInfId");
		pmtInf.setPmtMtd(PaymentMethod2Code.valueOf("DD"));
		PaymentTypeInformation2 pmtTpInf = new PaymentTypeInformation2();
		pmtTpInf.setInstrPrty(Priority2Code.valueOf("HIGH"));
		pmtTpInf.setClrChanl(ClearingChannel2Code.valueOf("RTGS"));
		pmtInf.setPmtTpInf(pmtTpInf);
		pmtInf.setReqdColltnDt(xmlGrCal);
		PartyIdentification8 cdtr = new PartyIdentification8();
		cdtr.setNm("name");
		pmtInf.setCdtr(cdtr);
		CashAccount7 cdtrAcc = new CashAccount7();
		AccountIdentification3Choice id = new AccountIdentification3Choice();
		SimpleIdentificationInformation2 prAcc = new SimpleIdentificationInformation2();
		prAcc.setId("id");
		id.setPrtryAcct(prAcc);
		cdtrAcc.setId(id);
		CashAccountType2 tp = new CashAccountType2();
		tp.setCd(CashAccountType4Code.valueOf("CACC"));
		cdtrAcc.setTp(tp);
		cdtrAcc.setNm("name");
		pmtInf.setCdtrAcct(cdtrAcc);
		BranchAndFinancialInstitutionIdentification3 crAgt = new BranchAndFinancialInstitutionIdentification3();
		FinancialInstitutionIdentification5Choice fId = new FinancialInstitutionIdentification5Choice();
		FinancialInstitutionIdentification3 cmbId = new FinancialInstitutionIdentification3();
		ClearingSystemMemberIdentification3Choice clrSysMmbId = new ClearingSystemMemberIdentification3Choice();
		clrSysMmbId.setId("id");
		cmbId.setClrSysMmbId(clrSysMmbId);
		fId.setCmbndId(cmbId);
		crAgt.setFinInstnId(fId);
		pmtInf.setCdtrAgt(crAgt);
		List<DirectDebitTransactionInformation1> drctDbtTxInf = pmtInf.getDrctDbtTxInf();
		DirectDebitTransactionInformation1 drctDbtTx = new DirectDebitTransactionInformation1();
		PaymentIdentification1 pmtId = new PaymentIdentification1();
		pmtId.setInstrId("id");
		pmtId.setEndToEndId("endtoendId");
		drctDbtTx.setPmtId(pmtId);
		PaymentTypeInformation2 pmtTyIn = new PaymentTypeInformation2();
		pmtTyIn.setCtgyPurp(PaymentCategoryPurpose1Code.valueOf("CORT"));
		drctDbtTx.setPmtTpInf(pmtTyIn);
		CurrencyAndAmount instAmt = new CurrencyAndAmount();
		instAmt.setCcy("ccy");
		drctDbtTx.setInstdAmt(instAmt);
		BranchAndFinancialInstitutionIdentification3 dbtAgt = new BranchAndFinancialInstitutionIdentification3();
		FinancialInstitutionIdentification5Choice finInId = new FinancialInstitutionIdentification5Choice();
		FinancialInstitutionIdentification3 cmbndId = new FinancialInstitutionIdentification3();
		ClearingSystemMemberIdentification3Choice clrSysMbId = new ClearingSystemMemberIdentification3Choice();
		clrSysMmbId.setId("id");
		cmbndId.setClrSysMmbId(clrSysMbId);
		finInId.setCmbndId(cmbndId);
		dbtAgt.setFinInstnId(finInId);
		drctDbtTx.setDbtrAgt(dbtAgt);
		PartyIdentification8 dbtr = new PartyIdentification8();
		dbtr.setNm("name");
		drctDbtTx.setDbtr(dbtr);
		CashAccount7 dbtrAcc = new CashAccount7();
		AccountIdentification3Choice dbtrAccId = new AccountIdentification3Choice();
		SimpleIdentificationInformation2 prtryAcc = new SimpleIdentificationInformation2();
		prtryAcc.setId("id");
		dbtrAccId.setPrtryAcct(prtryAcc);
		dbtrAcc.setId(dbtrAccId);
		CashAccountType2 caTp = new CashAccountType2();
		caTp.setCd(CashAccountType4Code.valueOf("CACC"));
		dbtrAcc.setTp(caTp);
		drctDbtTx.setDbtrAcct(dbtrAcc);
		RemittanceInformation1 rmtInf = new RemittanceInformation1();
		List<String> ustrd = rmtInf.getUstrd();
		ustrd.add("ustrd");
		drctDbtTx.setRmtInf(rmtInf);
		drctDbtTxInf.add(drctDbtTx);
		pmtInfList.add(pmtInf);
		doc.setPain00800101(pain);
		
		
		createXml(doc);

	}

	private static void createXml(Object obj) throws JAXBException, PropertyException, IOException {
		JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		StringWriter sw = new StringWriter();
		jaxbMarshaller.marshal(obj, sw);
		String xmlString = sw.toString();
		System.out.println(xmlString);

		FileWriter fw = new FileWriter("target/pojoXml.xml");
		fw.write(xmlString);
		fw.close();
	}

}
