package com.oorni.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oorni.common.OorniException;
import com.oorni.dao.MerchantDao;
import com.oorni.model.Merchant;
import com.oorni.model.MerchantType;
import com.oorni.model.Offer;
import com.oorni.model.OfferLabel;

@Repository("merchantDao")
public class MerchantDaoHibernate extends GenericDaoHibernate<Merchant, Long>
		implements MerchantDao {

	/**
	 * Constructor to create a Generics-based version using Merchant as the
	 * entity
	 */
	public MerchantDaoHibernate() {
		super(Merchant.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public Merchant saveMerchant(Merchant merchant) {
		return (Merchant) getSession().merge(merchant);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Merchant> getAllMerchant() {
		return getSession().createCriteria(Merchant.class).list();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws OorniException
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Merchant getMerchantById(Long merchantId) throws OorniException {
		List<Merchant> merchants = getSession().createCriteria(Merchant.class)
				.add(Restrictions.eq("merchantId", merchantId)).list();
		if (merchants != null && merchants.size() > 0) {
			return merchants.get(0);
		} else {
			throw new OorniException("No Merchant found for id " + merchantId);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws OorniException
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Merchant getMerchantByName(String merchantName) throws OorniException {
		List<Merchant> merchants = getSession().createCriteria(Merchant.class)
				.add(Restrictions.eq("merchantName", merchantName)).list();
		if (merchants != null && merchants.size() > 0) {
			return merchants.get(0);
		} else {
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public MerchantType saveMerchantType(MerchantType merchantType)  throws OorniException{
		return (MerchantType) getSession().merge(merchantType);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<MerchantType> getAllMerchantTypes() {
		return getSession().createCriteria(MerchantType.class)
				.addOrder(Order.asc("typeOrder")).list();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws OorniException
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public MerchantType getMerchantTypeById(Long merchantTypeId)
			throws OorniException {
		List<MerchantType> merchantTypes = getSession()
				.createCriteria(MerchantType.class)
				.add(Restrictions.eq("typeId", merchantTypeId)).list();
		if (merchantTypes != null && merchantTypes.size() > 0) {
			return merchantTypes.get(0);
		} else {
			throw new OorniException("No Merchant type found for id "
					+ merchantTypeId);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public MerchantType getMerchantTypeByName(String merchantTypeName)
			throws OorniException {
		List<MerchantType> merchantTypes = getSession()
				.createCriteria(MerchantType.class)
				.add(Restrictions.eq("typeName", merchantTypeName)).list();
		if (merchantTypes != null && merchantTypes.size() > 0) {
			return merchantTypes.get(0);
		} else {
			throw new OorniException("No Merchant type foud for name "
					+ merchantTypeName);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<MerchantType> getMerchantTypeLikeName(String merchantTypeName)
			throws OorniException {
		return getSession().createCriteria(MerchantType.class)
				.add(Restrictions.like("typeName", merchantTypeName + "%"))
				.list();

	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Merchant> getMerchantByType(String merchantType) {
		return getSession().createCriteria(Merchant.class)
				.createAlias("merchantType", "merchantType")
				.add(Restrictions.eq("merchantType.typeName", merchantType))
				.list();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Merchant> getMerchantByTypes(List<String> merchantTypes) {
		return getSession()
				.createQuery(
						"select merchant from Merchant as merchant where "
								+ "merchant.merchantType.typeName in :list order by merchant.merchantType.typeOrder")
				.setParameterList("list", merchantTypes).list();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<MerchantType> getMerchantTypesByIds(List<String> ids) {
		List<Long> idList = new ArrayList<Long>();
		for(String id : ids) {
			   idList.add(Long.parseLong(id)); 
			}
		return getSession()
				.createQuery(
						"select merchantType from MerchantType as merchantType where "
								+ "merchantType.typeId in :list order by merchantType.typeOrder")
				.setParameterList("list", idList).list();
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws OorniException
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public OfferLabel getOfferLabelByLabel(String label) throws OorniException {
		List<OfferLabel> offerLabels = getSession()
				.createCriteria(OfferLabel.class)
				.add(Restrictions.eq("label", label)).list();
		if (offerLabels != null && offerLabels.size() > 0) {
			return offerLabels.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public OfferLabel saveOfferLabel(OfferLabel offerLabel) throws OorniException {
		return (OfferLabel) getSession().merge(offerLabel);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<String> getMerchantNames() throws OorniException {
		return getSession().createQuery("select merchant.merchantName as merchantName from Merchant as merchant where "
				+ "merchant.enabled=true order by merchant.merchantName").list();
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws OorniException
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<String> getMerchantNames(String name)
			throws OorniException {
		List<String> names = getSession()
				.createCriteria(Merchant.class).setProjection(Projections.property("merchantName"))
				.add(Restrictions.like("merchantName", name, MatchMode.ANYWHERE))
				.add(Restrictions.eq("enabled", true)).list();
		if (names != null) {
			return names;
		} else {
			return new ArrayList<String>();
		}
	}
}
