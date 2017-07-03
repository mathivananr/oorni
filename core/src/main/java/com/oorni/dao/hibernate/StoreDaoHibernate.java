package com.oorni.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oorni.common.OorniException;
import com.oorni.dao.StoreDao;
import com.oorni.model.Store;
import com.oorni.model.User;

@Repository
public class StoreDaoHibernate extends GenericDaoHibernate<Store, Long> implements
		StoreDao {

	public StoreDaoHibernate() {
		super(Store.class);
	}

	/**
	 * {@inheritDoc}
	 * @throws OorniException 
	 */
	@Transactional
	public Store saveStore(Store store) throws OorniException {
		try {
		store = (Store) getSession().merge(store);
		} catch (HibernateException e) {
			throw new OorniException(e.getMessage(), e);
		}
		return store;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Store> getAllStore() {
		return getSession().createCriteria(Store.class).list();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws OorniException
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Store getStoreById(Long storeId) throws OorniException {
		List<Store> stores = getSession().createCriteria(Store.class)
				.add(Restrictions.eq("storeId", storeId)).list();
		if (stores != null && stores.size() > 0) {
			return stores.get(0);
		} else {
			throw new OorniException("No Store found for id " + storeId);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws OorniException
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Store getStoreByName(String storeName) throws OorniException {
		List<Store> stores = getSession().createCriteria(Store.class)
				.add(Restrictions.eq("storeName", storeName)).list();
		if (stores != null && stores.size() > 0) {
			return stores.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws OorniException
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Store getStoreByOwnerId(String ownerId) throws OorniException {
		List<Store> stores = getSession().createCriteria(Store.class)
				.add(Restrictions.eq("owner.id", Long.parseLong(ownerId))).list();
		if (stores != null && stores.size() > 0) {
			return stores.get(0);
		} else {
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws OorniException
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public User getOwnerByStoreId(String storeId) throws OorniException {
		List<Store> stores = getSession().createCriteria(Store.class)
				.add(Restrictions.eq("storeId", Long.parseLong(storeId))).list();
		if (stores != null && stores.size() > 0) {
			return stores.get(0).getOwner();
		} else {
			return null;
		}
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws OorniException
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public User getOwnerByStoreName(String storeName) throws OorniException {
		List<Store> stores = getSession().createCriteria(Store.class)
				.add(Restrictions.eq("storeName", storeName)).list();
		if (stores != null && stores.size() > 0) {
			return stores.get(0).getOwner();
		} else {
			return null;
		}
	}
}
