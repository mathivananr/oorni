package com.oorni.service.impl;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oorni.Constants;
import com.oorni.common.OorniException;
import com.oorni.dao.StoreDao;
import com.oorni.model.Merchant;
import com.oorni.model.Offer;
import com.oorni.model.Report;
import com.oorni.model.Store;
import com.oorni.model.User;
import com.oorni.service.MerchantManager;
import com.oorni.service.OfferManager;
import com.oorni.service.ReportManager;
import com.oorni.service.StoreManager;
import com.oorni.util.CommonUtil;
import com.oorni.util.StringUtil;

@Service("storeManager")
public class StoreManagerImpl extends GenericManagerImpl<Store, Long> implements StoreManager {

	private StoreDao storeDao;
	private MerchantManager merchantManager;
	@Autowired
	private OfferManager offerManager;
	private ReportManager reportManager;

	@Autowired
	public StoreManagerImpl(StoreDao storeDao) {
		super(storeDao);
		this.storeDao = storeDao;
	}

	@Autowired
	public void setMerchantManager(MerchantManager merchantManager) {
		this.merchantManager = merchantManager;
	}

	@Autowired
	public void setReportManager(ReportManager reportManager) {
		this.reportManager = reportManager;
	}

	public Store saveStore(Store store) throws OorniException {
		Calendar now = new GregorianCalendar();
		if (StringUtil.isEmptyString(store.getStoreId())) {
			store.setCreatedOn(now);
		}
		store.setOwner(CommonUtil.getLoggedInUser());
		store.setUpdatedOn(now);
		//store.setStoreName(store.getStoreName().replaceAll(" ", "-"));
		return storeDao.saveStore(store);
	}

	public List<Store> getAllStore() throws OorniException {
		return storeDao.getAllStore();
	}

	public Store getStoreById(Long storeId) throws OorniException {
		return storeDao.getStoreById(storeId);
	}

	public Store getStoreByName(String storeName) throws OorniException {
		return storeDao.getStoreByName(storeName);
	}

	public Store getMyStore() throws OorniException {
		String userId = CommonUtil.getLoggedInUserId();
		if(!StringUtil.isEmptyString(userId) && !userId.equalsIgnoreCase("anonymousUser")) {
			return storeDao.getStoreByOwnerId(userId);
		} else {
			return null;
		}
	}

	public String getMerchantURL(String storeName, String merchantName) throws OorniException {
		Merchant merchant = merchantManager.getMerchantByName(merchantName);
		Report report = new Report();
		report.setMerchantId(String.valueOf(merchant.getMerchantId()));
		report.setMerchantName(merchant.getMerchantName());
		report.setAffliateSource(merchant.getAffliateSource());
		report.setCreatedOn(new GregorianCalendar());
		report.setUpdatedOn(new GregorianCalendar());
		report.setCreatedBy(CommonUtil.getLoggedInUserName());
		report.setUpdatedBy(CommonUtil.getLoggedInUserName());
		report.setUsername(CommonUtil.getLoggedInUserName());
		report.setStatus(Constants.STATUS_CLICK);
		report.setConversionIp(CommonUtil.getUserIP());
		if(!StringUtil.isEmptyString(storeName)){
			User owner = getOwnerByStoreName(storeName);
			report.setStore(owner.getStore());
			report.setStoreOwner(owner);
		}
		report.setEnabled(true);
		String targetLink = merchant.getTargetURL();
		if (!StringUtil.isEmptyString(targetLink) && !StringUtil.isEmptyString(merchant.getAffliateParams())) {
			if (targetLink.contains("?")) {
				targetLink = targetLink + "&" + merchant.getAffliateParams();
			} else {
				targetLink = targetLink + "?" + merchant.getAffliateParams();
			}
		}
		report.setTargetLink(targetLink);
		report = reportManager.saveReport(report);
		if (!StringUtil.isEmptyString(targetLink)) {
			if (targetLink.contains("?")) {
				targetLink = targetLink + "&" + "oniref=" + report.getReportId();
			} else {
				targetLink = targetLink + "?" + "oniref=" + report.getReportId();
			}
		}
		return targetLink;
	}

	public String getOfferURL(String storeId, String offerId) throws OorniException {
		Offer offer = offerManager.getOfferById(Long.parseLong(offerId));
		Report report = new Report();
		report.setOfferId(String.valueOf(offer.getOfferId()));
		report.setMerchantName(offer.getMerchantName());
		report.setCreatedOn(new GregorianCalendar());
		report.setUpdatedOn(new GregorianCalendar());
		report.setCreatedBy(CommonUtil.getLoggedInUserName());
		report.setUpdatedBy(CommonUtil.getLoggedInUserName());
		report.setUsername(CommonUtil.getLoggedInUserName());
		report.setStatus(Constants.STATUS_CLICK);
		report.setConversionIp(CommonUtil.getUserIP());
		if(!StringUtil.isEmptyString(storeId)){
			User owner = getOwnerByStoreId(storeId);
			report.setStore(owner.getStore());
			report.setStoreOwner(owner);
		}
		report.setEnabled(true);
		String targetLink = offer.getTargetURL();
		if(!StringUtil.isEmptyString(offer.getMerchantName())){
			Merchant merchant = merchantManager.getMerchantByName(offer.getMerchantName().toLowerCase());
			if(merchant != null) {
				targetLink = merchant.getTargetURL();
				report.setMerchantId(String.valueOf(merchant.getMerchantId()));
				report.setAffliateSource(merchant.getAffliateSource());
				if (!StringUtil.isEmptyString(targetLink) && !StringUtil.isEmptyString(merchant.getAffliateParams())) {
					if (targetLink.contains("?")) {
						targetLink = targetLink + "&" + merchant.getAffliateParams();
					} else {
						targetLink = targetLink + "?" + merchant.getAffliateParams();
					}
				}
			}
		}
		report.setTargetLink(targetLink);
		report = reportManager.saveReport(report);
		if (!StringUtil.isEmptyString(targetLink)) {
			if (targetLink.contains("?")) {
				targetLink = targetLink + "&" + "aff_sub=" + report.getReportId();
			} else {
				targetLink = targetLink + "?" + "aff_sub=" + report.getReportId();
			}
		}
		return targetLink;
	}
	
	public User getOwnerByStoreId(String storeId) throws OorniException {
		return storeDao.getOwnerByStoreId(storeId);
	}
	
	public User getOwnerByStoreName(String storeName) throws OorniException {
		return storeDao.getOwnerByStoreName(storeName);
	}
}
