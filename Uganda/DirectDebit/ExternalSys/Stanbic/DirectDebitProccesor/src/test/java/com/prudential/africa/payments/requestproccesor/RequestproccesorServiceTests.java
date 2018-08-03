package com.prudential.africa.payments.requestproccesor;

import java.io.StringWriter;
import java.math.BigDecimal;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.namespace.QName;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.prudential.africa.payments.requestproccesor.dao.entities.DirectDebitTransaction;
import com.prudential.africa.payments.requestproccesor.dao.repository.DirectDebitTransactionRepository;
import com.prudential.africa.payments.requestproccesor.service.RequestProcessorService;
import com.prudential.payments.stanbic.directdebit.pain008.Document;
import com.prudential.payments.stanbic.directdebit.pain008.Pain00800101;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestproccesorServiceTests {

	@Autowired
	private DirectDebitTransactionRepository directDebitTransactionRepository;
	
	@Autowired
	private RequestProcessorService requestProcessorService;
	
	@Test
	public void contextLoads() throws JAXBException, DatatypeConfigurationException {
		
		setupDirectDebitTransactions();
		
		Document document = requestProcessorService.prepareDocumentForStanbicTransactions();
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Pain00800101.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		StringWriter stringWritter = new StringWriter();
		
		Object directDebitPaymentInitiationMessage = new JAXBElement<Pain00800101>(new QName("urn:iso:std:iso:20022:tech:xsd:pain.008.001.01", "pain.008.001.01"), Pain00800101.class, null, document.getPain00800101());
		jaxbMarshaller.marshal(directDebitPaymentInitiationMessage, stringWritter);
		String xmlString = stringWritter.toString();
		
		System.out.println(xmlString);
	}

	private void setupDirectDebitTransactions() {
		
		for (int i=0; i<10; i++)
		{
			DirectDebitTransaction directDebitTransaction = new DirectDebitTransaction();
			directDebitTransaction.setBatchNumber("Batc0000" + i);
			directDebitTransaction.setTransactionAmount(new BigDecimal(100.00).add(new BigDecimal(i)));
			directDebitTransaction.setTransactionCurrency("UGS");
			directDebitTransaction.setClientTransactionReference("PRU/0001/001/1" + i);
			directDebitTransaction.setPayeeBankAccountNumber("CLI12345678");
			directDebitTransaction.setPayeeBankCode("001");
			
			directDebitTransactionRepository.save(directDebitTransaction);
		}
		
	}

}
