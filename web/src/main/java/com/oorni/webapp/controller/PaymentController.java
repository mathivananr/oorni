package com.oorni.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.oorni.service.PaymentManager;

@Controller
public class PaymentController extends BaseFormController {

	PaymentManager paymentManager;

	@Autowired
	public void setPaymentManager(PaymentManager paymentManager) {
		this.paymentManager = paymentManager;
	}
}
