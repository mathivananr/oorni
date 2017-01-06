package com.oorni.webapp.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.oorni.Constants;
import com.oorni.common.OorniException;
import com.oorni.model.Merchant;
import com.oorni.model.MerchantType;
import com.oorni.service.MerchantManager;
import com.oorni.util.StringUtil;

import javassist.bytecode.stackmap.BasicBlock.Catch;

@Controller
public class MerchantController extends BaseFormController {

	private MerchantManager merchantManager;

	@Autowired
	public void setMerchantManager(MerchantManager merchantManager) {
		this.merchantManager = merchantManager;
	}

	@RequestMapping(value = "/shopping", method = RequestMethod.GET)
	public ModelAndView showShoppingStore(final HttpServletRequest request,
			final HttpServletResponse response) throws OorniException {
		Model model = new ExtendedModelMap();
		Set<MerchantType> merchantTypes = new HashSet<MerchantType>(
				merchantManager.getAllMerchantTypes());
		model.addAttribute(Constants.MERCHANT_TYPE_LIST, merchantTypes);
		model.addAttribute("activeMenu", "shopping-link");
		return new ModelAndView("/oorni/shopping", model.asMap());
	}
	
	@RequestMapping(value = "/contribute", method = RequestMethod.GET)
	public ModelAndView showContributeStore(final HttpServletRequest request,
			final HttpServletResponse response) throws OorniException {
		Model model = new ExtendedModelMap();
		Set<MerchantType> merchantTypes = new HashSet<MerchantType>(
				merchantManager.getAllMerchantTypes());
		model.addAttribute(Constants.MERCHANT_TYPE_LIST, merchantTypes);
		model.addAttribute("activeMenu", "shopping-link");
		return new ModelAndView("/oorni/shopping", model.asMap());
	}
	
	@RequestMapping(value = "/booking", method = RequestMethod.GET)
	public ModelAndView showBookingStore(final HttpServletRequest request,
			final HttpServletResponse response) throws OorniException {
		Model model = new ExtendedModelMap();
		Set<MerchantType> merchantTypes = new HashSet<MerchantType>(
				merchantManager.getMerchantTypeLikeName(Constants.MERCHANT_TYPE_BOOKING));
		model.addAttribute(Constants.MERCHANT_TYPE_LIST, merchantTypes);
		model.addAttribute("activeMenu", "booking-link");
		return new ModelAndView("/oorni/booking", model.asMap());
	}

	/*@RequestMapping(value = "/recharge", method = RequestMethod.GET)
	public ModelAndView showRechargeStore(final HttpServletRequest request,
			final HttpServletResponse response) throws MUException {
		Model model = new ExtendedModelMap();
		try {
			model.addAttribute("merchantType", merchantManager
					.getMerchantTypeByName(Constants.MERCHANT_TYPE_RECHARGE));
		} catch (MUException e) {
			model.addAttribute("merchantType", new MerchantType());
		}
		model.addAttribute("activeMenu", "recharge-link");
		return new ModelAndView("/oorni/rechargeForm", model.asMap());
	}*/
	
	@ModelAttribute
	@RequestMapping(value = "/admin/merchant", method = RequestMethod.GET)
	public ModelAndView showMerchantStore(final HttpServletRequest request,
			final HttpServletResponse response) throws OorniException {
		Model model = new ExtendedModelMap();
		String merchantId = request.getParameter("id");
		Set<MerchantType> merchantTypes = new HashSet<MerchantType>(
				merchantManager.getAllMerchantTypes());
		model.addAttribute(Constants.MERCHANT_TYPE_LIST, merchantTypes);
		if (!StringUtil.isEmptyString(merchantId)) {
			model.addAttribute("merchant",
					merchantManager.getMerchantById(Long.parseLong(merchantId)));
		} else {
			model.addAttribute(Constants.MERCHANT, new Merchant());
		}
		return new ModelAndView("/admin/merchant", model.asMap());
	}

	@ModelAttribute
	@RequestMapping(value = "/admin/merchants", method = RequestMethod.GET)
	public ModelAndView showMerchants(final HttpServletRequest request,
			final HttpServletResponse response) throws OorniException {
		Model model = new ExtendedModelMap();
		model.addAttribute(Constants.MERCHANT_LIST,
				merchantManager.getAllMerchant());
		return new ModelAndView("/admin/merchantList", model.asMap());
	}

	@ModelAttribute
	@RequestMapping(value = "/admin/merchantType", method = RequestMethod.GET)
	public ModelAndView showMerchantTypeStore(final HttpServletRequest request,
			final HttpServletResponse response) throws OorniException {
		Model model = new ExtendedModelMap();
		String merchantTypeId = request.getParameter("id");
		if (!StringUtil.isEmptyString(merchantTypeId)) {
			model.addAttribute("merchantType", merchantManager
					.getMerchantTypeById(Long.parseLong(merchantTypeId)));
		} else {
			model.addAttribute("merchantType", new MerchantType());
		}
		return new ModelAndView("/admin/merchantType", model.asMap());
	}

	@ModelAttribute
	@RequestMapping(value = "/admin/merchantTypes", method = RequestMethod.GET)
	public ModelAndView showMerchantTypes(final HttpServletRequest request,
			final HttpServletResponse response) throws OorniException {
		Model model = new ExtendedModelMap();
		Set<MerchantType> merchantTypes = new HashSet<MerchantType>(
				merchantManager.getAllMerchantTypes());
		model.addAttribute(Constants.MERCHANT_TYPE_LIST, merchantTypes);
		return new ModelAndView("/admin/merchantTypeList", model.asMap());
	}

	@ModelAttribute
	@RequestMapping(value = "/admin/saveMerchantType", method = RequestMethod.POST)
	public ModelAndView saveMerchantType(MerchantType merchantType,
			BindingResult errors, HttpServletRequest request) {
		Model model = new ExtendedModelMap();
		try {
			Calendar now = new GregorianCalendar();
			if (StringUtil.isEmptyString(merchantType.getTypeId())) {
				merchantType.setCreatedOn(now);
			}
			merchantType.setUpdatedOn(now);
			merchantManager.saveMerchantType(merchantType);
			saveMessage(request, "Merchant type saved successfully.");
			return new ModelAndView("redirect:/admin/merchantTypes");
		} catch (OorniException e) {
			log.error(e.getMessage(), e);
			return new ModelAndView("/admin/merchantType", model.asMap());
		}
	}

	@ModelAttribute
	@RequestMapping(value = "/admin/saveMerchantDetails", method = RequestMethod.POST)
	public ModelAndView saveMerchant(Merchant merchant, BindingResult errors,
			HttpServletRequest request) {
		Model model = new ExtendedModelMap();
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest
					.getFile("file");
			Calendar now = new GregorianCalendar();
			if (StringUtil.isEmptyString(merchant.getMerchantId())) {
				merchant.setCreatedOn(now);
			}
			merchant.setUpdatedOn(now);
			String uploadDir = getServletContext().getRealPath("/files");
			if(StringUtil.isEmptyString(uploadDir)) {
				uploadDir = getServletContext().getRealPath("/images");
			}
			merchantManager.saveMerchant(merchant, file, uploadDir);
			saveMessage(request, "Merchant saved successfully.");
			return new ModelAndView("redirect:/admin/merchants");
		} catch (OorniException e) {
			Set<MerchantType> merchantTypes = new HashSet<MerchantType>(
					merchantManager.getAllMerchantTypes());
			model.addAttribute(Constants.MERCHANT_TYPE_LIST, merchantTypes);
			model.addAttribute(Constants.MERCHANT, merchant);
			return new ModelAndView("/admin/merchant", model.asMap());
		}
	}
	
	@RequestMapping(value = "/merchant/names", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	List<String> merchantNames(final HttpServletRequest request,
			final HttpServletResponse response,
			@RequestParam("query") String name) throws OorniException {
		log.info("getting labels for :: " + name);
		List<String> names = new ArrayList<String>();
		try {
			if(!StringUtil.isEmptyString(name)) {
				names =  merchantManager.getMerchantNames(name);
			} else {
				names = merchantManager.getMerchantNames();
			}
		}catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return names;
	}
}
