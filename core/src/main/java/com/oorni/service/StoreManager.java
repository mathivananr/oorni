package com.oorni.service;

import java.util.List;

import com.oorni.common.OorniException;
import com.oorni.model.Store;
import com.oorni.model.User;

public interface StoreManager extends GenericManager<Store, Long> {

	Store saveStore(Store store) throws OorniException;

	List<Store> getAllStore() throws OorniException;

	Store getStoreById(Long storeId) throws OorniException;

	Store getStoreByName(String storeName) throws OorniException;
	
	Store getMyStore() throws OorniException;
	
	String getMerchantURL(String storeId, String merchantId) throws OorniException;
	
	String getOfferURL(String storeId, String offerId) throws OorniException;
	
	User getOwnerByStoreId(String storeId) throws OorniException;
	
	User getOwnerByStoreName(String storeName) throws OorniException;
}
