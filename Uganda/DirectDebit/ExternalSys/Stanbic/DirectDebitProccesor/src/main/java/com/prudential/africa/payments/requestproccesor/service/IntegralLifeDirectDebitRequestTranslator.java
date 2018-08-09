package com.prudential.africa.payments.requestproccesor.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.beanio.BeanIOConfigurationException;
import org.beanio.BeanReader;
import org.beanio.BeanWriter;
import org.beanio.StreamFactory;
import org.springframework.stereotype.Component;

import com.prudential.africa.payments.requestproccesor.dao.entities.DirectDebitTransaction;

@Component
public class IntegralLifeDirectDebitRequestTranslator {

	
	public List<DirectDebitTransaction> transalteDataForPaymentsInitiation(File paymentsInitiationFile) throws BeanIOConfigurationException, IOException
	{
		// create a StreamFactory
        StreamFactory factory = StreamFactory.newInstance();
        // load the mapping file
        factory.load(this.getClass().getClassLoader().getResourceAsStream("mappings/IntegeralLifeDirectDebitTransactionMapping.xml"));
        
        // use a StreamFactory to create a BeanReader
        BeanReader in = factory.createReader("ILDirectDebitReader", paymentsInitiationFile);
        
        List<DirectDebitTransaction> directDebitTransactions = new ArrayList<>();
        DirectDebitTransaction directDebitTransaction = null;
        
        while ((directDebitTransaction = (DirectDebitTransaction) in.read()) != null) {
        	
        	directDebitTransactions.add(directDebitTransaction);
        }
        in.close();
        
        return directDebitTransactions;
	}
	
	public void transaltePojoToXml(List<DirectDebitTransaction> directDebitTransactions)
			throws BeanIOConfigurationException, IOException {
		// create a StreamFactory
		StreamFactory factory = StreamFactory.newInstance();
		// load the mapping file
		factory.load(this.getClass().getClassLoader()
				.getResourceAsStream("mappings/IntegeralLifeDirectDebitTransactionXmlMapping.xml"));

		// use a StreamFactory to create a BeanWriter
		BeanWriter out = factory.createWriter("ILDirectDebitReader", new File("target/direct_debit_load.xml"));
		// write an Employee object directly to the BeanWriter
		for (DirectDebitTransaction directDebitTransaction : directDebitTransactions) {
			out.write(directDebitTransaction);
		}

		out.flush();
		out.close();
	}
}
