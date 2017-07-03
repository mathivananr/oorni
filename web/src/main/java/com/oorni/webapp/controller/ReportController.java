package com.oorni.webapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;

import com.oorni.common.OorniException;
import com.oorni.model.Report;
import com.oorni.model.User;
import com.oorni.service.ReportManager;
import com.oorni.service.UserManager;
import com.oorni.util.CommonUtil;
import com.oorni.util.StringUtil;

@Controller
public class ReportController extends BaseFormController {

	private ReportManager reportManager;

	private UserManager userManager;
	
	@Autowired
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	@Autowired
	public void setReportManager(ReportManager reportManager) {
		this.reportManager = reportManager;
	}
	
	@RequestMapping(value = "/user/report", method = RequestMethod.GET)
	public ModelAndView getMyReport(final HttpServletRequest request,
			final HttpServletResponse response){
		Model model = new ExtendedModelMap();
		model.addAttribute("activeMenu", "offer-link");
		try {
			model.addAttribute("report", reportManager.getMyReport());
			model.addAttribute("clicks", reportManager.getClicksCount(null, null, CommonUtil.getLoggedInUserId()));
			model.addAttribute("conversions", reportManager.getConversionCount(null, null, CommonUtil.getLoggedInUserId()));
			model.addAttribute("payout", reportManager.getTotalPayout(null, null, CommonUtil.getLoggedInUserId()));
			return new ModelAndView("/user/report", model.asMap());
		} catch (OorniException e) {
			log.error(e.getMessage(), e);
			saveError(request, "problem in getting report details");
			model.addAttribute("report", new ArrayList<Report>());
			model.addAttribute("clicks", 0);
			model.addAttribute("conversions", 0);
			model.addAttribute("payout", 0);
			return new ModelAndView("/user/report", model.asMap());
		}
	}
	
	@RequestMapping(value = "/admin/reportform", method = RequestMethod.GET)
	public ModelAndView showReportForm(final HttpServletRequest request,
			final HttpServletResponse response) throws OorniException {
		Model model = new ExtendedModelMap();
		String offerId = request.getParameter("id");
		model.addAttribute("activeMenu", "offer-link");
		model.addAttribute("report", new Report());
		if(!StringUtil.isEmptyString(offerId)) {
			model.addAttribute("report", reportManager.getReportById(Long.parseLong(offerId)));
		}
		return new ModelAndView("/admin/reportform", model.asMap());
	}
	
	@RequestMapping(value = "/admin/saveReport", method = RequestMethod.POST)
	@ModelAttribute
	public ModelAndView saveReport(@ModelAttribute("report") Report report,
			BindingResult errors, final HttpServletRequest request,
			final HttpServletResponse response) {
		Model model = new ExtendedModelMap();
		try {
			log.info("saving report");
			report.setStoreOwner(userManager.getUser(String.valueOf(report.getStoreOwner().getId())));
			report.setStore(report.getStoreOwner().getStore());
			report = reportManager.saveReport(report);
			log.info("report saved");
			model.addAttribute("activeMenu", "offer-link");
			model.addAttribute("report", reportManager.getAllReport());
			saveMessage(request, "report saved successfully");
			return new ModelAndView("redirect:/admin/report");
		} catch (OorniException e) {
			log.error(e.getMessage(), e);
			saveError(request, "problem in saving report");
			model.addAttribute("report", report);
			return new ModelAndView("/admin/reportform", model.asMap());
		}
		
	}
	
	@RequestMapping(value = "/admin/report", method = RequestMethod.GET)
	public ModelAndView getAllReport(final HttpServletRequest request,
			final HttpServletResponse response) throws OorniException {
		Model model = new ExtendedModelMap();
		model.addAttribute("activeMenu", "offer-link");
		model.addAttribute("report", reportManager.getAllReport());
		model.addAttribute("clicks", reportManager.getClicksCount(null, null, null));
		model.addAttribute("conversions", reportManager.getConversionCount(null, null, null));
		model.addAttribute("payout", reportManager.getTotalPayout(null, null, null));
		return new ModelAndView("/admin/salesReport", model.asMap());
	}

	@RequestMapping(value = "/report/users", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	List<Map<String, String>> merchantNames(final HttpServletRequest request,
			final HttpServletResponse response,
			@RequestParam("query") String name) throws OorniException {
		log.info("getting users for :: " + name);
		List<Map<String, String>> users = new ArrayList<Map<String, String>>();
		try {
			if(!StringUtil.isEmptyString(name)) {
				users =  userManager.getUserSuggestionByStoreName(name);
			}
		}catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return users;
	}
	
}
