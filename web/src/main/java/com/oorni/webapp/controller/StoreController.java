package com.oorni.webapp.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.oorni.Constants;
import com.oorni.common.OorniException;
import com.oorni.model.MerchantType;
import com.oorni.model.Store;
import com.oorni.service.MerchantManager;
import com.oorni.service.ReportManager;
import com.oorni.service.StoreManager;
import com.oorni.util.CommonUtil;

@Controller
public class StoreController extends BaseFormController {

	private StoreManager storeManager;
	private MerchantManager merchantManager;
	private ReportManager reportManager;

	@Autowired
	public void setStoreManager(StoreManager storeManager) {
		this.storeManager = storeManager;
	}

	@Autowired
	public void setMerchantManager(MerchantManager merchantManager) {
		this.merchantManager = merchantManager;
	}

	@Autowired
	public void setReportManager(ReportManager reportManager) {
		this.reportManager = reportManager;
	}

	@ModelAttribute
	@RequestMapping(value = "/user/createStore", method = RequestMethod.GET)
	public ModelAndView showCreateStore(final HttpServletRequest request,
			final HttpServletResponse response) throws OorniException {
		Model model = new ExtendedModelMap();
		model.addAttribute(Constants.PAGE, new Store());
		return new ModelAndView("/user/createStore", model.asMap());
	}

	@ModelAttribute
	@RequestMapping(value = "/user/editStore", method = RequestMethod.GET)
	public ModelAndView showEditStore(final HttpServletRequest request,
			final HttpServletResponse response) throws OorniException {
		Model model = new ExtendedModelMap();
		try {
			model.addAttribute(Constants.PAGE, storeManager.getMyStore());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			saveError(request, "you dont own any store yet! just create one");
			model.addAttribute(Constants.PAGE, new Store());
		}
		return new ModelAndView("/user/createStore", model.asMap());
	}

	@ModelAttribute
	@RequestMapping(value = "/admin/stores", method = RequestMethod.GET)
	public ModelAndView showStores(final HttpServletRequest request,
			final HttpServletResponse response) throws OorniException {
		Model model = new ExtendedModelMap();
		model.addAttribute(Constants.PAGE_LIST, storeManager.getAllStore());
		return new ModelAndView("/admin/storeList", model.asMap());
	}

	@ModelAttribute
	@RequestMapping(value = "/user/saveStore", method = RequestMethod.POST)
	public ModelAndView saveStore(Store store, BindingResult errors,
			HttpServletRequest request) {
		Model model = new ExtendedModelMap();
		try {
			Store updatedStore = storeManager.saveStore(store);
			CommonUtil.getLoggedInUser().setStore(updatedStore);
			saveMessage(request, "Store saved successfully.");
			model.addAttribute(Constants.PAGE, updatedStore);
			return new ModelAndView("redirect:/store/"+store.getStoreName());
		} catch (OorniException e) {
			model.addAttribute(Constants.PAGE, store);
			return new ModelAndView("/user/createStore", model.asMap());
		}
	}

	@RequestMapping(value = "/store/{storeName}", method = RequestMethod.GET)
	public ModelAndView showShoppingStore(final HttpServletRequest request,
			final HttpServletResponse response,
			@PathVariable("storeName") String storeName) throws OorniException {
		Model model = new ExtendedModelMap();
		try {
			Set<MerchantType> merchantTypes = new HashSet<MerchantType>(
					merchantManager.getAllMerchantTypes());
			Store store = storeManager.getStoreByName(storeName);
			if (store != null) {
				model.addAttribute(Constants.MERCHANT_TYPE_LIST, merchantTypes);
				model.addAttribute(Constants.PAGE, store);
			} else {
				saveError(request, "oops! the store not found");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			saveError(request, "oops! problem in this store contact store owner");
		}
		return new ModelAndView("/oorni/store", model.asMap());
	}
	
	@RequestMapping(value = "/hook/merchant/{merchantName}", method = RequestMethod.GET)
	public ModelAndView gotoMerchant(final HttpServletRequest request,
			final HttpServletResponse response,
			@PathVariable("merchantName") String merchantName) throws OorniException {
		String url = "/";
		try {
			url = storeManager.getMerchantURL(null, merchantName);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			saveError(request,
					"oops! problem in this store merchant, contact store owner");
		}
		return new ModelAndView("redirect:" + url);
	}
	
	@RequestMapping(value = "/hook/merchant/{storeName}/{merchantName}", method = RequestMethod.GET)
	public ModelAndView gotoMerchantURL(final HttpServletRequest request,
			final HttpServletResponse response,
			@PathVariable("storeName") String storeName,
			@PathVariable("merchantName") String merchantName) throws OorniException {
		String url = "/";
		try {
			url = storeManager.getMerchantURL(storeName, merchantName);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			saveError(request,
					"oops! problem in this store merchant, contact store owner");
		}
		return new ModelAndView("redirect:" + url);
	}
	
	@RequestMapping(value = "/hook/offer/{offerId}", method = RequestMethod.GET)
	public ModelAndView gotoOffer(final HttpServletRequest request,
			final HttpServletResponse response,
			@PathVariable("offerId") String offerId) throws OorniException {
		String url = "/";
		try {
			url = storeManager.getOfferURL(null, offerId);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			saveError(request,
					"oops! problem in this store merchant, contact store owner");
		}
		return new ModelAndView("redirect:" + url);
	}
	
	@RequestMapping(value = "/hook/offer/{storeId}/{offerId}", method = RequestMethod.GET)
	public ModelAndView gotoOfferURL(final HttpServletRequest request,
			final HttpServletResponse response,
			@PathVariable("storeId") String storeId,
			@PathVariable("offerId") String offerId) throws OorniException {
		String url = "/";
		try {
			url = storeManager.getOfferURL(storeId, offerId);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			saveError(request,
					"oops! problem in this store merchant, contact store owner");
		}
		return new ModelAndView("redirect:" + url);
	}
}
