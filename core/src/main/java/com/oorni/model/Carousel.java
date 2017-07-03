package com.oorni.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.DocumentId;

import com.oorni.util.StringUtil;

@Entity
@Table(name = "ku_carousel")
public class Carousel extends BaseObject implements Serializable {

	private static final long serialVersionUID = 3832626162173359411L;

	private Long carouselId;
	private String carouselTitle;
	private String description;
	private String merchantName;
	private String contentType;
	private String content;
	private String imagePath;
	private String targetURL;
	private List<OfferLabel> labels = new ArrayList<OfferLabel>();
	private String labelsString;
	private String createdBy;
	private String updatedBy;
	private Calendar createdOn;
	private Calendar updatedOn = new GregorianCalendar();
	private boolean enabled;

	public Carousel() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@DocumentId
	@Column(name = "carousel_id")
	public Long getCarouselId() {
		return carouselId;
	}

	public void setCarouselId(Long carouselId) {
		this.carouselId = carouselId;
	}

	@Column(name = "title")
	public String getCarouselTitle() {
		return carouselTitle;
	}

	public void setCarouselTitle(String carouselTitle) {
		this.carouselTitle = carouselTitle;
	}

	@Column(name = "description", columnDefinition = "TEXT")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "merchant_name")
	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	@Column(name = "content_type")
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Column(name = "content", columnDefinition = "TEXT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "image_path")
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Column(name = "target_url", columnDefinition = "TEXT")
	public String getTargetURL() {
		return targetURL;
	}

	public void setTargetURL(String targetURL) {
		this.targetURL = targetURL;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SELECT)
	@JoinTable(name = "ku_rel_carousel_label", joinColumns = {
			@JoinColumn(name = "carousel_id") }, inverseJoinColumns = { @JoinColumn(name = "label_id") })
	public List<OfferLabel> getLabels() {
		return labels;
	}

	public void setLabels(List<OfferLabel> labels) {
		this.labels = labels;
	}

	@Transient
	public String getLabelsString() {
		if(StringUtil.isEmptyString(labelsString)) {
			StringBuffer stringBuffer = new StringBuffer();
			if(getLabels() != null){
				for (OfferLabel label : getLabels()) {
						if(label != null){
							if (stringBuffer.length() > 0) {
								stringBuffer.append(", ");
							}
							stringBuffer.append(label.getLabel());
						}
				}
			}
			return stringBuffer.toString();
		} else {
			return labelsString;
		}
	}

	public void setLabelsString(String labelsString) {
		this.labelsString = labelsString;
	}
	
	@Column(name = "is_enabled", columnDefinition = "boolean default true", nullable = false)
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Carousel)) {
			return false;
		}

		final Carousel carousel = (Carousel) o;

		return !(carouselId != null ? !carouselId.equals(carousel.getCarouselId()) : carousel.getCarouselId() != null);

	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return (carouselId != null ? carouselId.hashCode() : 0);
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append("carouselId",
				this.carouselId);
		return sb.toString();
	}

}
