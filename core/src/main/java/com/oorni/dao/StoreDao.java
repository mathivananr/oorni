package com.oorni.dao;

import java.util.List;

import com.oorni.common.OorniException;
import com.oorni.model.Store;
import com.oorni.model.User;

public interface StoreDao extends GenericDao<Store, Long> {

	Store saveStore(Store store);

	List<Store> getAllStore();

	Store getStoreById(Long storeId) throws OorniException;

	Store getStoreByName(String storeName) throws OorniException;
	
	Store getStoreByOwnerId(String userId) throws OorniException;
	
	User getOwnerByStoreId(String storeId) throws OorniException;
	
	User getOwnerByStoreName(String storeName) throws OorniException;
}
