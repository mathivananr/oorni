package com.oorni.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.oorni.common.OorniException;
import com.oorni.model.Carousel;
import com.oorni.model.Offer;
import com.oorni.model.OfferLabel;

public interface OfferDao extends GenericDao<Offer, Long> {

	Offer saveOffer(Offer offer);

	List<Offer> getAllOffers() throws OorniException;

	List<Offer> getOffersAddedByMe(String username) throws OorniException;
	
	Offer getOfferById(Long offerId) throws OorniException;

	Offer getOfferByTitle(String offerTitle) throws OorniException;

	Offer getOffer(String offerTitle, String merchantName,
			String couponCode, Calendar offerEnd, String targetURL) throws OorniException;
			
	List<Offer> getOffersByLabel(String label) throws OorniException;

	List<OfferLabel> getOfferLabels(List<String> labelsString)
			throws OorniException;

	List<OfferLabel> getAllOfferLabels() throws OorniException;

	OfferLabel getOfferLabelById(Long labelId) throws OorniException;

	OfferLabel getOfferLabelByLabel(String label) throws OorniException;

	OfferLabel saveOfferLabel(OfferLabel offerLabel) throws OorniException;
	
	List<Offer> getOffersByLabels(List<String> labels, int start, int end)
			throws OorniException;

	List<String> getSuggestLabels(String label)
			throws OorniException;
	
	Carousel saveCarousel(Carousel carousel) throws OorniException;
	
	Carousel getCarouselById(Long carouselId) throws OorniException;
	
	List<Carousel> getAllCarousels() throws OorniException;
	
    List<Carousel> getActiveCarousels() throws OorniException;
	
	List<Carousel> getCarouselsByLabels(List<String> labels)
			throws OorniException;
}
