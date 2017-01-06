package com.oorni.service;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.oorni.common.OorniException;
import com.oorni.model.Offer;
import com.oorni.model.OfferLabel;

public interface OfferManager extends GenericManager<Offer, Long> {

	Offer saveOffer(Offer offer) throws OorniException;

	List<Offer> getAllOffers() throws OorniException;

	List<Offer> getOffersAddedByMe() throws OorniException;
	
	Offer getOfferById(Long offerId) throws OorniException;

	Offer getOfferByTitle(String offerTitle) throws OorniException;

	Offer getOffer(String offerTitle, String merchantName,
			String couponCode, Calendar offerEnd, String targetURL) throws OorniException;
			
	List<Offer> getOffersByLabel(String label) throws OorniException;

	List<Offer> saveOffers(List<Offer> offers) throws OorniException;

	List<OfferLabel> getAllOfferLabels() throws OorniException;

	OfferLabel getOfferLabelById(Long labelId) throws OorniException;

	OfferLabel getOfferLabelByLabel(String label) throws OorniException;

	OfferLabel saveOfferLabel(OfferLabel offerLabel) throws OorniException;
	
	List<OfferLabel> saveOfferLabels(List<OfferLabel> offerLabels)
			throws OorniException;
	
	List<OfferLabel> importOfferLabels(String filePath)
			throws OorniException;
	
	List<Offer> getOffersByLabels(List<String> labels, int start, int end)
			throws OorniException;
	
	String getOffersContent(List<String> labels, int start, int end)
			throws OorniException;
	
	List<String> getSuggestLabels(String label)
			throws OorniException;
}
