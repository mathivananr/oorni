package com.oorni.job;

import java.text.ParseException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.oorni.common.OorniException;
import com.oorni.service.EmailOfferProcessor;
import com.oorni.service.UserManager;
import com.oorni.util.CommonUtil;

/**
 * job to schedule the payoom offers processing 
 * 
 * @author mathi
 * 
 */
@DisallowConcurrentExecution
public class ProcessPayoomOffers implements Job {
	private Log log = LogFactory.getLog(com.oorni.job.ProcessPayoomOffers.class);

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		log.info("payoom email offer processor");
		ApplicationContext springContext = WebApplicationContextUtils
				.getWebApplicationContext(ContextLoaderListener
						.getCurrentWebApplicationContext().getServletContext());
		EmailOfferProcessor emailOfferProcessor = (EmailOfferProcessor) springContext
				.getBean("emailOfferProcessor");
		UserManager usermanager = (UserManager) springContext
				.getBean("userManager");
		try {
			CommonUtil.login(usermanager.getUserByUsername("backgroundUser"), "bgu123$");
			emailOfferProcessor.processPayoomOffers();
			CommonUtil.logout();
		} catch (OorniException | ParseException e) {
			log.error(e.getMessage(), e);
		}

	}

}
