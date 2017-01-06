package com.oorni.dao.hibernate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oorni.common.OorniException;
import com.oorni.dao.OfferDao;
import com.oorni.model.Offer;
import com.oorni.model.OfferLabel;
import com.oorni.util.StringUtil;

@Repository("offerDao")
public class OfferDaoHibernate extends GenericDaoHibernate<Offer, Long>
		implements OfferDao {

	/**
	 * Constructor to create a Generics-based version using Offer as the entity
	 */
	public OfferDaoHibernate() {
		super(Offer.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public Offer saveOffer(Offer offer) {
		return (Offer) getSession().merge(offer);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Offer> getAllOffers() throws OorniException {
		try {
			return getSession().createCriteria(Offer.class).list();
		} catch (HibernateException e) {
			throw new OorniException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<OfferLabel> getAllOfferLabels() throws OorniException {
		try {
			return getSession().createCriteria(OfferLabel.class).list();
		} catch (HibernateException e) {
			throw new OorniException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws OorniException
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Offer getOfferById(Long offerId) throws OorniException {
		List<Offer> offers = getSession().createCriteria(Offer.class)
				.add(Restrictions.eq("offerId", offerId)).list();
		if (offers != null && offers.size() > 0) {
			return offers.get(0);
		} else {
			throw new OorniException("No Offer found for id " + offerId);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws OorniException
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Offer> getOffersAddedByMe(String username) throws OorniException {
		List<Offer> offers = getSession().createCriteria(Offer.class)
				.add(Restrictions.eq("createdBy", username)).list();
		if (offers != null && offers.size() > 0) {
			return offers;
		} else {
			return new ArrayList<Offer>();
		}
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws OorniException
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Offer getOffer(String offerTitle, String merchantName,
			String couponCode, Calendar offerEnd, String targetURL) throws OorniException {
		Criteria criteria = getSession().createCriteria(Offer.class);
		log.info("getting offer "+offerTitle+" for merchant "+merchantName);
		if (!StringUtil.isEmptyString(offerTitle)) {
			criteria.add(Restrictions.eq("offerTitle", offerTitle));
		}
		if (!StringUtil.isEmptyString(merchantName)) {
			criteria.add(Restrictions.eq("merchantName", merchantName));
		}
		if (!StringUtil.isEmptyString(couponCode)) {
			criteria.add(Restrictions.eq("couponCode", couponCode));
		}
		if (!StringUtil.isEmptyString(offerEnd)) {
			criteria.add(Restrictions.eq("offerEnd", offerEnd));
		}
		if (!StringUtil.isEmptyString(targetURL)) {
			criteria.add(Restrictions.eq("targetURL", targetURL));
		}
		List<Offer> offers = criteria.list();
		log.info("offers size"+offers.size());
		if (offers != null && offers.size() > 0) {
			return offers.get(0);
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
	public Offer getOfferByTitle(String offerTitle) throws OorniException {
		List<Offer> offers = getSession().createCriteria(Offer.class)
				.add(Restrictions.eq("offerTitle", offerTitle)).list();
		if (offers != null && offers.size() > 0) {
			return offers.get(0);
		} else {
			throw new OorniException("No Offer foud for title" + offerTitle);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Offer> getOffersByLabel(String label) throws OorniException {
		try {
			Criteria c = getSession().createCriteria(Offer.class, "offer");
			c.createAlias("offer.labels", "offerLabel");
			c.add(Restrictions.eq("offerLabel.label", label));
			return (List<Offer>) c.list();
		} catch (HibernateException e) {
			throw new OorniException(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Offer> getOffersByLabels(List<String> labels, int start, int end)
			throws OorniException {
		Calendar now = new GregorianCalendar();
		try {
			String queryString = "select offer from Offer as offer";
			String queryAlies = "";
			String queryCondition = "";
			String queryLimit = "";
			for (int i = 1; i <= labels.size(); i++) {
				queryAlies = queryAlies + " join offer.labels as offerLabel"
						+ i;
			}
			int count = 1;
			for (String label : labels) {
				if (queryCondition.length() == 0 ) {
					queryCondition = " where offerLabel" + count + ".label='"
							+ label + "'";
				} else {
					queryCondition = queryCondition + " and offerLabel" + count
							+ ".label='" + label + "'";
				}
				count++;
			}
			/*if (queryCondition.length() == 0 ) {
				queryCondition = " where (offer.offerEnd > :now or offer.expired = false)";
			} else {
				queryCondition = queryCondition + " and (offer.offerEnd > :now or offer.expired = false)";
			}*/
			if (queryCondition.length() == 0 ) {
				queryCondition = " where offer.expired = false";
			} else {
				queryCondition = queryCondition + " and offer.expired = false";
			}
			queryLimit = " order by offer.offerId desc";
			Query query = getSession().createQuery(
					queryString + queryAlies + queryCondition + queryLimit);
			//query.setCalendar("now", now);
			query.setFirstResult(start);
			query.setMaxResults(end);
			return (List<Offer>) query.list();
		} catch (HibernateException e) {
			throw new OorniException(e.getMessage(), e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws OorniException
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<OfferLabel> getOfferLabels(List<String> labelsString)
			throws OorniException {

		List<OfferLabel> offerLabels = getSession()
				.createCriteria(OfferLabel.class)
				.add(Restrictions.in("label", labelsString)).list();
		if (offerLabels != null) {
			return offerLabels;
		} else {
			return new ArrayList<OfferLabel>();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws OorniException
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<String> getSuggestLabels(String label)
			throws OorniException {

		List<String> offerLabels = getSession()
				.createCriteria(OfferLabel.class).setProjection(Projections.property("label"))
				.add(Restrictions.like("label", label, MatchMode.ANYWHERE)).list();
		if (offerLabels != null) {
			return offerLabels;
		} else {
			return new ArrayList<String>();
		}
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws OorniException
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public OfferLabel getOfferLabelById(Long labelId) throws OorniException {
		List<OfferLabel> offerLabels = getSession()
				.createCriteria(OfferLabel.class)
				.add(Restrictions.eq("labelId", labelId)).list();
		if (offerLabels != null && offerLabels.size() > 0) {
			return offerLabels.get(0);
		} else {
			throw new OorniException("No Offer found for id " + labelId);
		}
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
}
