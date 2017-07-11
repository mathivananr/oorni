package com.oorni.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oorni.dao.PaymentDao;
import com.oorni.model.Payment;
import com.oorni.service.PaymentManager;

@Service("paymentManager")
public class PaymentManagerImpl extends GenericManagerImpl<Payment, Long> implements PaymentManager {
	
	private PaymentDao paymentDao;

	@Autowired
	public void setPaymentDao(PaymentDao paymentDao) {
		this.paymentDao = paymentDao;
	}
	
}
