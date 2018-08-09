package com.prudential.africa.payments.requestproccesor.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.prudential.africa.payments.requestproccesor.dao.entities.DirectDebitTransaction;
import com.prudential.africa.payments.requestproccesor.dao.repository.DirectDebitTransactionRepository;
import com.prudential.africa.payments.requestproccesor.transfom.StanbicXmlTransalator;

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
	
	public List<DirectDebitTransaction> csvToPojoConvertor(File file) {
		CsvMapper csvMapper = new CsvMapper();
		CsvSchema csvSchema = csvMapper.typedSchemaFor(DirectDebitTransaction.class).withHeader();
		List<DirectDebitTransaction> directDebitTransaction = null;
		try {
			MappingIterator<DirectDebitTransaction> list = new CsvMapper().readerFor(DirectDebitTransaction.class)
					.with(csvSchema.withColumnSeparator(CsvSchema.DEFAULT_COLUMN_SEPARATOR)).readValues(file);
			directDebitTransaction = list.readAll();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return directDebitTransaction;

	}

	public void saveAll(List<DirectDebitTransaction> directDebitTransactions) {
		directDebitTransactionRepository.saveAll(directDebitTransactions);
	}
	
}
