package com.oorni.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.search.annotations.DocumentId;

@Entity
@Table(name = "ku_store_report")
public class Report extends BaseObject implements Serializable {
	private static final long serialVersionUID = 3832626162173359411L;

	private Long reportId;
	private String merchantId;
	private String merchantName;
	private String merchantType;
	private String affliateSource;
	private String targetLink;
	private String offerId;
	private String product;
	private Double salesAmount;
	private Double merchantPayout;
	private Double payout;
	private String conversionIp;
	private String username;
	private String status;
	private String createdBy;
	private String updatedBy;
	private Calendar createdOn;
	private Calendar updatedOn = new GregorianCalendar();
	private User storeOwner;
	private Store store;
	private boolean enabled;
	
	public Report() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@DocumentId
	@Column(name = "report_id")
	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	@Column(name = "merchant_id")
	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	@Column(name = "merchant_name")
	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	@Column(name = "merchant_type")
	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}

	@Column(name = "affliate_source")
	public String getAffliateSource() {
		return affliateSource;
	}

	public void setAffliateSource(String affliateSource) {
		this.affliateSource = affliateSource;
	}
	
	@Column(name = "target_link")
	public String getTargetLink() {
		return targetLink;
	}

	public void setTargetLink(String targetLink) {
		this.targetLink = targetLink;
	}

	@Column(name = "username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "offer_id")
	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	@Column(name = "product")
	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	@Column(name = "sales_amount")
	public Double getSalesAmount() {
		return salesAmount;
	}

	public void setSalesAmount(Double salesAmount) {
		this.salesAmount = salesAmount;
	}

	@Column(name = "merchant_payout")
	public Double getMerchantPayout() {
		return merchantPayout;
	}

	public void setMerchantPayout(Double merchantPayout) {
		this.merchantPayout = merchantPayout;
	}

	@Column(name = "payout")
	public Double getPayout() {
		return payout;
	}

	public void setPayout(Double payout) {
		this.payout = payout;
	}

	@Column(name = "conversion_ip")
	public String getConversionIp() {
		return conversionIp;
	}

	public void setConversionIp(String conversionIp) {
		this.conversionIp = conversionIp;
	}

	@Column(name = "conversion_status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_owner_id")
	public User getStoreOwner() {
		return storeOwner;
	}

	public void setStoreOwner(User owner) {
		this.storeOwner = owner;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
	
	@Column(name = "created_on")
	public Calendar getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}

	@Column(name = "updated_on")
	public Calendar getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Calendar updatedOn) {
		this.updatedOn = updatedOn;
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
	
	@Column(name = "is_enabled", columnDefinition = "boolean default true", nullable = false)
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Report o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Report)) {
			return false;
		}

		final Report report = (Report) o;

		return !(reportId != null ? !reportId.equals(report.getReportId())
				: report.getReportId() != null);

	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return (reportId != null ? reportId.hashCode() : 0);
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this,
				ToStringStyle.DEFAULT_STYLE).append("reportId", this.reportId);
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

}
