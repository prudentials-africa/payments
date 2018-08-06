package com.prudential.africa.payments.requestproccesor;

import java.math.BigDecimal;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.prudential.africa.payments.requestproccesor.dao.entities.DirectDebitTransaction;
import com.prudential.africa.payments.requestproccesor.dao.repository.DirectDebitTransactionRepository;
import com.prudential.africa.payments.requestproccesor.service.RequestProcessorService;

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
		
		String document = requestProcessorService.prepareDocumentForStanbicTransactions();
		System.out.println(document);
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
