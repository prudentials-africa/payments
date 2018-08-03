package com.prudential.africa.payments.requestproccesor.dao.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prudential.africa.payments.requestproccesor.dao.entities.DirectDebitTransaction;

@Repository
public interface DirectDebitTransactionRepository extends CrudRepository<DirectDebitTransaction, Long> {

	List<DirectDebitTransaction> findByBatchNumber(String batchNumber);
}
