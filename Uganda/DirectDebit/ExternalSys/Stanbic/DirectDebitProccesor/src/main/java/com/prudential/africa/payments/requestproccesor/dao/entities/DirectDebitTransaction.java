package com.prudential.africa.payments.requestproccesor.dao.entities;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "batchNumber", "clientTransactionReference", "payeeAddressCity", "payeeAddressCountry",
		"payeeAddressLine1", "payeeAddressState", "payeeAddressZipCode", "payeeBankAccountNumber",
		"payeeBankAccountType", "payeeBankBranchCode", "payeeBankCode", "payeeBankName", "payeeFirstName",
		"payeeLastName", "payeeTitle", "paymentMethod", "purpose", "status", "transactionAmount", "transactionCurrency",
		"transactionDate", "transactionReceivedDate" })

@Entity
public class DirectDebitTransaction {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	//Indicates the reference for all the transaction in this group
	private String batchNumber;
	
	//DirectDebit
	//Cheque
	private String paymentMethod ;
	
	//Premium collection
	//Late fees
	//Tax
	//One time fee
	private String purpose ;
	
	//Policy Number
	private String clientTransactionReference;
	
	//Mr., MRS., Dr. etc
	private String payeeTitle;
	
	//First Name of the client
	private String payeeFirstName;
	
	//Last Name of the client
	private String payeeLastName;
	
	//Client address details
	private String payeeAddressLine1;
	
	//Client address details
	private String payeeAddressLine2;
	
	//Client address details
	private String payeeAddressLine3;
	
	//Client address details
	private String payeeAddressCity;

	//Client address details
	private String payeeAddressState;
	
	//Client address details
	private String payeeAddressCountry;
	
	//Client address details
	private String payeeAddressZipCode;
	
	//Client bank account number 
	private String payeeBankAccountNumber;
	
	private String payeeBankCode;
	
	private String payeeBankBranchCode;
	
	//Savings
	//Current
	//Bond
	//Corporate
	private String payeeBankAccountType;
	
	private String payeeBankName;
	
	//Actual date the transaction is supossed to be executed
	private Date transactionDate;
	
	//Date the transaction is submitted
	private Date  submittedDate;
	
	//Amount of transaction 
	private BigDecimal transactionAmount;
	
	//Currency in which the transaction is taking place
	private String transactionCurrency;
	
	//Status of the transaction 
	//Submitted
	//Sent for Processing
	//Acknowledged
	//Failed
	//Sucessful
	private String  status;
	
	//Date when the transaction response is received
	private Date  transactionReceivedDate;
	
	//Date when the actual transaction took place. ie the date money is received in the system
	private Date  transactionProcessedDate;
	
	//Short code 
	private String  errorCode;
	
	//Detailed  description of the Error
	private String  errorDescription;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getClientTransactionReference() {
		return clientTransactionReference;
	}

	public void setClientTransactionReference(String clientTransactionReference) {
		this.clientTransactionReference = clientTransactionReference;
	}

	public String getPayeeTitle() {
		return payeeTitle;
	}

	public void setPayeeTitle(String payeeTitle) {
		this.payeeTitle = payeeTitle;
	}

	public String getPayeeFirstName() {
		return payeeFirstName;
	}

	public void setPayeeFirstName(String payeeFirstName) {
		this.payeeFirstName = payeeFirstName;
	}

	public String getPayeeLastName() {
		return payeeLastName;
	}

	public void setPayeeLastName(String payeeLastName) {
		this.payeeLastName = payeeLastName;
	}

	public String getPayeeAddressLine1() {
		return payeeAddressLine1;
	}

	public void setPayeeAddressLine1(String payeeAddressLine1) {
		this.payeeAddressLine1 = payeeAddressLine1;
	}

	public String getPayeeAddressLine2() {
		return payeeAddressLine2;
	}

	public void setPayeeAddressLine2(String payeeAddressLine2) {
		this.payeeAddressLine2 = payeeAddressLine2;
	}

	public String getPayeeAddressLine3() {
		return payeeAddressLine3;
	}

	public void setPayeeAddressLine3(String payeeAddressLine3) {
		this.payeeAddressLine3 = payeeAddressLine3;
	}

	public String getPayeeAddressCity() {
		return payeeAddressCity;
	}

	public void setPayeeAddressCity(String payeeAddressCity) {
		this.payeeAddressCity = payeeAddressCity;
	}

	public String getPayeeAddressState() {
		return payeeAddressState;
	}

	public void setPayeeAddressState(String payeeAddressState) {
		this.payeeAddressState = payeeAddressState;
	}

	public String getPayeeAddressCountry() {
		return payeeAddressCountry;
	}

	public void setPayeeAddressCountry(String payeeAddressCountry) {
		this.payeeAddressCountry = payeeAddressCountry;
	}

	public String getPayeeAddressZipCode() {
		return payeeAddressZipCode;
	}

	public void setPayeeAddressZipCode(String payeeAddressZipCode) {
		this.payeeAddressZipCode = payeeAddressZipCode;
	}

	public String getPayeeBankAccountNumber() {
		return payeeBankAccountNumber;
	}

	public void setPayeeBankAccountNumber(String payeeBankAccountNumber) {
		this.payeeBankAccountNumber = payeeBankAccountNumber;
	}

	public String getPayeeBankCode() {
		return payeeBankCode;
	}

	public void setPayeeBankCode(String payeeBankCode) {
		this.payeeBankCode = payeeBankCode;
	}

	public String getPayeeBankBranchCode() {
		return payeeBankBranchCode;
	}

	public void setPayeeBankBranchCode(String payeeBankBranchCode) {
		this.payeeBankBranchCode = payeeBankBranchCode;
	}

	public String getPayeeBankAccountType() {
		return payeeBankAccountType;
	}

	public void setPayeeBankAccountType(String payeeBankAccountType) {
		this.payeeBankAccountType = payeeBankAccountType;
	}

	public String getPayeeBankName() {
		return payeeBankName;
	}

	public void setPayeeBankName(String payeeBankName) {
		this.payeeBankName = payeeBankName;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Date getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionCurrency() {
		return transactionCurrency;
	}

	public void setTransactionCurrency(String transactionCurrency) {
		this.transactionCurrency = transactionCurrency;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTransactionReceivedDate() {
		return transactionReceivedDate;
	}

	public void setTransactionReceivedDate(Date transactionReceivedDate) {
		this.transactionReceivedDate = transactionReceivedDate;
	}

	public Date getTransactionProcessedDate() {
		return transactionProcessedDate;
	}

	public void setTransactionProcessedDate(Date transactionProcessedDate) {
		this.transactionProcessedDate = transactionProcessedDate;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	@Override
	public String toString() {
		return "DirectDebitTransaction [id=" + id + ", batchNumber=" + batchNumber + ", paymentMethod=" + paymentMethod
				+ ", purpose=" + purpose + ", clientTransactionReference=" + clientTransactionReference
				+ ", payeeTitle=" + payeeTitle + ", payeeFirstName=" + payeeFirstName + ", payeeLastName="
				+ payeeLastName + ", payeeAddressLine1=" + payeeAddressLine1 + ", payeeAddressLine2="
				+ payeeAddressLine2 + ", payeeAddressLine3=" + payeeAddressLine3 + ", payeeAddressCity="
				+ payeeAddressCity + ", payeeAddressState=" + payeeAddressState + ", payeeAddressCountry="
				+ payeeAddressCountry + ", payeeAddressZipCode=" + payeeAddressZipCode + ", payeeBankAccountNumber="
				+ payeeBankAccountNumber + ", payeeBankCode=" + payeeBankCode + ", payeeBankBranchCode="
				+ payeeBankBranchCode + ", payeeBankAccountType=" + payeeBankAccountType + ", payeeBankName="
				+ payeeBankName + ", transactionDate=" + transactionDate + ", submittedDate=" + submittedDate
				+ ", transactionAmount=" + transactionAmount + ", transactionCurrency=" + transactionCurrency
				+ ", status=" + status + ", transactionReceivedDate=" + transactionReceivedDate
				+ ", transactionProcessedDate=" + transactionProcessedDate + ", errorCode=" + errorCode
				+ ", errorDescription=" + errorDescription + "]";
	}
	
	
}
