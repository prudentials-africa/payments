package com.prudential.africa.payments.requestproccesor.service;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prudential.africa.payments.requestproccesor.dao.entities.DirectDebitTransaction;
import com.prudential.africa.payments.requestproccesor.dao.repository.DirectDebitTransactionRepository;

@Service
public class RequestProcessorService {

	
	@Autowired
	private DirectDebitTransactionRepository directDebitTransactionRepository;
	
	@Autowired
	private StanbicXmlTransalator stanbicXmlTransalator;
	
	public String prepareDocumentForStanbicTransactions() throws DatatypeConfigurationException, JAXBException
	{
		Iterable<DirectDebitTransaction> directDebitTransactions = directDebitTransactionRepository.findAll();
		String stanbicXmlData = stanbicXmlTransalator.translateDirectDebitTransactions(directDebitTransactions);
		
		return stanbicXmlData;
	}
}
