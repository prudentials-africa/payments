package com.prudential.africa.payments.requestproccesor;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.beanio.BeanIOConfigurationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.prudential.africa.payments.requestproccesor.dao.entities.DirectDebitTransaction;
import com.prudential.africa.payments.requestproccesor.service.IntegralLifeDirectDebitRequestTranslator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegralLifeDirectDebitRequestTranslatorTests {

	@Autowired
	private IntegralLifeDirectDebitRequestTranslator integralLifeDirectDebitRequestTranslator;
	
	@Test
	public void testIntegeralCsvLoader() throws BeanIOConfigurationException, IOException, URISyntaxException
	{
		
		URL fileUrl = this.getClass().getClassLoader().getResource("direct_debit_load.csv");
		List<DirectDebitTransaction> directDebitTransactions = 
				integralLifeDirectDebitRequestTranslator.transalteDataForPaymentsInitiation(new File(fileUrl.toURI().getPath()));
		System.out.println(directDebitTransactions.size());
	}
}
