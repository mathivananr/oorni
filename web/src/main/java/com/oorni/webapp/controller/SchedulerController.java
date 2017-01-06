package com.oorni.webapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.oorni.common.OorniException;
import com.oorni.service.SchedulerManager;
import com.oorni.util.StringUtil;

@Controller
public class SchedulerController extends BaseFormController {

	@Autowired
	private SchedulerManager scheduler;

	@RequestMapping(value = "/admin/job-list", method = RequestMethod.GET)
	public ModelAndView getOffers(final HttpServletRequest request, final HttpServletResponse response) {
		Model model = new ExtendedModelMap();
		try {
			model.addAttribute("jobList", scheduler.getAllJobs());
			return new ModelAndView("/admin/jobList", model.asMap());
		} catch (OorniException e) {
			saveError(request, e.getMessage());
			model.addAttribute("jobList", new ArrayList<Map<String, Object>>());
			return new ModelAndView("/admin/jobList", model.asMap());
		}
	}

	@RequestMapping(value = "/admin/saveJob", method = RequestMethod.POST)
	public ModelAndView saveJob(final HttpServletRequest request, final HttpServletResponse response,
			@RequestParam("jobName") String jobName, @RequestParam("jobClass") String jobClass,
			@RequestParam("interval") String interval) {
		Model model = new ExtendedModelMap();
		Map<String, Object> schedulerDetails = new HashMap<String, Object>();
		try {
			schedulerDetails.put("jobName", jobName);
			schedulerDetails.put("jobClass", jobClass);
			schedulerDetails.put("triggerName", jobName);
			schedulerDetails.put("interval", (Integer.parseInt(interval) * 60000));
			if(scheduler.checkJobExists(jobName)){
				scheduler.rescheduleJob(schedulerDetails);
				saveMessage(request, "job rescheduled");
			} else {
				saveMessage(request, "job scheduled");
				scheduler.scheduleJob(schedulerDetails);
			}
			return new ModelAndView("redirect:/admin/job-list");
		} catch (OorniException e) {
			saveError(request, e.getMessage());
			model.addAllAttributes(schedulerDetails);
			return new ModelAndView("/admin/jobform", model.asMap());
		}
	}

	@RequestMapping(value = "/admin/jobform", method = RequestMethod.GET)
	public ModelAndView showJobPage(final HttpServletRequest request, final HttpServletResponse response) {
		Model model = new ExtendedModelMap();
		try {
			String jobName = request.getParameter("jobName");
			if (!StringUtil.isEmptyString(jobName)) {
				model.addAllAttributes(scheduler.getJob(jobName));
			}
			return new ModelAndView("/admin/jobform", model.asMap());
		} catch (OorniException e) {
			saveError(request, e.getMessage());
			return new ModelAndView("/admin/jobform", model.asMap());
		}
	}

	@RequestMapping(value = "/admin/stopJob", method = RequestMethod.GET)
	public ModelAndView stopJob(final HttpServletRequest request, final HttpServletResponse response) {
		try {
			String jobName = request.getParameter("jobName");
			if (!StringUtil.isEmptyString(jobName)) {
				scheduler.stopJob(jobName);
				saveMessage(request, "job stoped");
				return new ModelAndView("redirect:/admin/job-list");
			} else {
				saveError(request, "jobName should not be empty");
				return new ModelAndView("redirect:/admin/job-list");
			}
		} catch (OorniException e) {
			saveError(request, e.getMessage());
			return new ModelAndView("redirect:/admin/job-list");
		}
	}

	@RequestMapping(value = "/admin/startJob", method = RequestMethod.GET)
	public ModelAndView startJob(final HttpServletRequest request, final HttpServletResponse response)
			throws OorniException {
		try {
			String jobName = request.getParameter("jobName");
			if (!StringUtil.isEmptyString(jobName)) {
				scheduler.startJob(jobName);
				saveMessage(request, "job started");
				return new ModelAndView("redirect:/admin/job-list");
			} else {
				saveError(request, "jobName should not be empty");
				return new ModelAndView("redirect:/admin/job-list");
			}
		} catch (OorniException e) {
			saveError(request, e.getMessage());
			return new ModelAndView("redirect:/admin/job-list");
		}
	}
}
