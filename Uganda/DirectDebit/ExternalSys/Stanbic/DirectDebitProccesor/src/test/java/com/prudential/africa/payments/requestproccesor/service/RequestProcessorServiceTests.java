package com.prudential.africa.payments.requestproccesor.service;

import java.math.BigDecimal;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.prudential.africa.payments.requestproccesor.dao.entities.DirectDebitTransaction;
import com.prudential.africa.payments.requestproccesor.dao.repository.DirectDebitTransactionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestProcessorServiceTests {

	@Autowired
	private RequestProcessorService requestProcessorService;
	
	@Autowired
	private DirectDebitTransactionRepository directDebitTransactionRepository;


	@Before
	public void setup() {
	}

	@Test
	public void test() throws DatatypeConfigurationException, JAXBException {
		DirectDebitTransaction directDebitTransaction = new DirectDebitTransaction();
		directDebitTransaction.setId(122L);
		directDebitTransaction.setTransactionAmount(new BigDecimal("123"));
		directDebitTransaction.setTransactionCurrency("test");
		directDebitTransaction.setPayeeFirstName("First Name");
		directDebitTransaction.setPayeeAddressCountry("Country");
		directDebitTransaction.setPayeeBankAccountNumber("123456");
		directDebitTransactionRepository.save(directDebitTransaction);
		String doc = requestProcessorService.prepareDocumentForStanbicTransactions();
		System.err.println(doc);
		
	}

}
