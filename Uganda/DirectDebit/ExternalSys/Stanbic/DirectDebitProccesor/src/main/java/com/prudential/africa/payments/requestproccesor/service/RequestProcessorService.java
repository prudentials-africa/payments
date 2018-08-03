package com.prudential.africa.payments.requestproccesor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prudential.africa.payments.requestproccesor.dao.entities.DirectDebitTransaction;
import com.prudential.africa.payments.requestproccesor.dao.repository.DirectDebitTransactionRepository;
import com.prudential.payments.stanbic.directdebit.pain008.DirectDebitTransactionInformation1;

@Service
public class RequestProcessorService {

	
	@Autowired
	private DirectDebitTransactionRepository directDebitTransactionRepository;
	
	public void processStanbicPayments()
	{
		Iterable<DirectDebitTransaction> directDebitTransactions = directDebitTransactionRepository.findAll();
		
		List<DirectDebitTransactionInformation1> directDebitTransactionInformations = new ArrayList<>();
		for (DirectDebitTransaction directDebitTransaction : directDebitTransactions)
		{
			DirectDebitTransactionInformation1 directDebitTransactionInformation = DirectDebitTransactionInformationMapper.mapDirectDebitTransaction(directDebitTransaction);
			directDebitTransactionInformations.add(directDebitTransactionInformation);
		}
		
	}
}
