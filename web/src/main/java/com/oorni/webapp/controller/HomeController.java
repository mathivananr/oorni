package com.oorni.webapp.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.oorni.Constants;
import com.oorni.common.OorniException;
import com.oorni.model.Offer;
import com.oorni.service.OfferManager;

@Controller
@RequestMapping(value = { "", "/" })
public class HomeController extends BaseFormController {
	private OfferManager offerManager;

	@Autowired
	public void setOfferManager(OfferManager offerManager) {
		this.offerManager = offerManager;
	}

	/*@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showHome(final HttpServletRequest request,
			final HttpServletResponse response) throws OorniException {
		Model model = new ExtendedModelMap();
		model.addAttribute("activeMenu", "offer-link");
		model.addAttribute("storeTitle", "");
		model.addAttribute("metaKeywords", "");
		model.addAttribute("metaDescription", "");
		model.addAttribute("label", "");
		model.addAttribute("storeNo", 1);
		try {
			model.addAttribute("offers", offerManager.getOffersByLabels(
					new ArrayList<String>(), 0, Constants.OFFER_TO_LOAD));
		} catch (OorniException e) {
			log.error(e.getMessage(), e);
			model.addAttribute("offers", new ArrayList<Offer>());
		}
		return new ModelAndView("/oorni/offers", model.asMap());
	}*/
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showHome(final HttpServletRequest request,
			final HttpServletResponse response) throws OorniException {
		Model model = new ExtendedModelMap();
		return new ModelAndView("/cashback", model.asMap());
	}
}
