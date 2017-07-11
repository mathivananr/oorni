package com.oorni.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.search.annotations.DocumentId;

@Entity
@Table(name = "ku_payment")
public class Payment extends BaseObject implements Serializable {
	private static final long serialVersionUID = 3832626162173359411L;
	
	private Long paymentId;
	private Double paymentAmount;
	private Calendar paymentDate;
	private String paymentMode;
	private String paymentDescription;
	private String transactionNmber;
	private BankAccount bankAccount = new BankAccount();
	private OnlineWallet onlineWallet = new OnlineWallet();
	private String status;
	private Calendar createdOn;
	private Calendar updatedOn;
	private String createdBy;
	private String updatedBy;
	
	public Payment() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@DocumentId
	@Column(name = "payment_id")
	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	@Column(name = "payment_amount")
	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	@Column(name = "payment_date")
	public Calendar getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Calendar paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Column(name = "payment_mode")
	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	@Embedded
	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	@Embedded
	public OnlineWallet getOnlineWallet() {
		return onlineWallet;
	}

	public void setOnlineWallet(OnlineWallet onlineWallet) {
		this.onlineWallet = onlineWallet;
	}

	@Column(name = "payment_description")
	public String getPaymentDescription() {
		return paymentDescription;
	}

	public void setPaymentDescription(String paymentDescription) {
		this.paymentDescription = paymentDescription;
	}

	@Column(name = "tansaction_no")
	public String getTransactionNmber() {
		return transactionNmber;
	}

	public void setTransactionNmber(String transactionNmber) {
		this.transactionNmber = transactionNmber;
	}

	@Column(name = "status" )
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Calendar getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}

	@Column(name = "created_by")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "updated_by")
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	public Calendar getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Calendar updatedOn) {
		this.updatedOn = updatedOn;
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return (paymentId != null ? paymentId.hashCode() : 0);
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this,
				ToStringStyle.DEFAULT_STYLE).append("paymentId", this.paymentId);
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

}
