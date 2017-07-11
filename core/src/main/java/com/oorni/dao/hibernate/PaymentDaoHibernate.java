package com.oorni.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oorni.common.OorniException;
import com.oorni.dao.PaymentDao;
import com.oorni.model.Payment;

@Repository
public class PaymentDaoHibernate extends GenericDaoHibernate<Payment, Long> implements PaymentDao {

	public PaymentDaoHibernate() {
		super(Payment.class);
	}

	/**
	 * {@inheritDoc}
	 * @throws OorniException 
	 */
	@Transactional
	public Payment savePayment(Payment payment) throws OorniException {
		try {
		payment = (Payment) getSession().merge(payment);
		} catch (HibernateException e) {
			throw new OorniException(e.getMessage(), e);
		}
		return payment;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Payment> getAllPayment() {
		return getSession().createCriteria(Payment.class).list();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws OorniException
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Payment getPaymentById(Long paymentId) throws OorniException {
		List<Payment> payments = getSession().createCriteria(Payment.class)
				.add(Restrictions.eq("paymentId", paymentId)).list();
		if (payments != null && payments.size() > 0) {
			return payments.get(0);
		} else {
			throw new OorniException("No Payment found for id " + paymentId);
		}
	}
	
}
