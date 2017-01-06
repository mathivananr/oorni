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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ku_merchant")
@Indexed
@XmlRootElement
public class Merchant extends BaseObject implements Serializable {

	private static final long serialVersionUID = 3832626162173359411L;

	private Long merchantId;
	private String merchantName;
	private String description;
	private MerchantType merchantType;
	private String logoPath;
	private String targetURL;
	private String URL;
	private String affliateSource;
	private String affliateParams;
	private boolean topMerchant;
	private boolean enabled;
	private boolean storeEnabled;
	private String createdBy;
	private String updatedBy;
	private Calendar createdOn = new GregorianCalendar();
	private Calendar updatedOn = new GregorianCalendar();

	public Merchant() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@DocumentId
	@Column(name = "merchant_id")
	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	@Column(name = "merchant_name")
	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "merchant_type_id")
	public MerchantType getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(MerchantType merchantType) {
		this.merchantType = merchantType;
	}

	@Column(name = "logo_path")
	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	@Column(name = "target_url", columnDefinition = "TEXT")
	@JsonIgnore
	public String getTargetURL() {
		return targetURL;
	}

	public void setTargetURL(String targetURL) {
		this.targetURL = targetURL;
	}

	@Column(name = "url", columnDefinition = "TEXT")
	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}
	
	@Column(name = "affliate_source")
	public String getAffliateSource() {
		return affliateSource;
	}

	public void setAffliateSource(String affliateSource) {
		this.affliateSource = affliateSource;
	}

	@Column(name = "affliate_params")
	public String getAffliateParams() {
		return affliateParams;
	}

	public void setAffliateParams(String affliateParams) {
		this.affliateParams = affliateParams;
	}

	@Column(name = "is_top_merchant")
	public boolean isTopMerchant() {
		return topMerchant;
	}

	public void setTopMerchant(boolean topMerchant) {
		this.topMerchant = topMerchant;
	}

	@Column(name = "is_enabled", columnDefinition = "boolean default true", nullable = false)
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Column(name = "is_store_enabled", columnDefinition = "boolean default false", nullable = false)
	public boolean isStoreEnabled() {
		return storeEnabled;
	}

	public void setStoreEnabled(boolean storeEnabled) {
		this.storeEnabled = storeEnabled;
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

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Merchant)) {
			return false;
		}

		final Merchant merchant = (Merchant) o;

		return !(merchantId != null ? !merchantId.equals(merchant.getMerchantId())
				: merchant.getMerchantId() != null);

	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return (merchantId != null ? merchantId.hashCode() : 0);
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this,
				ToStringStyle.DEFAULT_STYLE).append("id", this.merchantId);
		return sb.toString();
	}
}
