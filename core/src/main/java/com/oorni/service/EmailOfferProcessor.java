package com.oorni.service;

import java.text.ParseException;

import com.oorni.common.OorniException;

public interface EmailOfferProcessor {

	boolean processPayoomOffers() throws OorniException, ParseException;

	boolean pullFlipkartOffers() throws OorniException;
}
