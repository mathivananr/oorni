package com.oorni.service;

import java.util.Map;

import javax.jws.WebService;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.oorni.common.OorniException;

/**
 * Web Service interface for manage schedules.
 * 
 * @author mathi
 */
@WebService
@Path("/scheduler")
public interface SchedulerService {

	/**
	 * schedule the job
	 * 
	 * @param schedulerDetails
	 * @throws OorniException
	 */
	@POST
	@Path("/scheduleJob")
	@Produces("application/json")
	Map<String, Object> scheduleJob(Map<String, Object> schedulerDetails)
			throws OorniException;

	
	/**
	 * start scheduler
	 * 
	 * @return
	 * 
	 * @throws OorniException
	 */
	@POST
	@Path("/startScheduler")
	@Produces("application/json")
	Map<String, Object> startScheduler() throws OorniException;
	
	/**
	 * shutdown scheduler
	 * 
	 * @return
	 * 
	 * @throws OorniException
	 */
	@POST
	@Path("/shutdownScheduler")
	@Produces("application/json")
	Map<String, Object> shutdownScheduler() throws OorniException;
	
	/**
	 * reschedule the job
	 * 
	 * @param schedulerDetails
	 * @throws OorniException
	 */
	@POST
	@Path("/rescheduleJob")
	@Produces("application/json")
	Map<String, Object> rescheduleJob(Map<String, Object> schedulerDetails)
			throws OorniException;

	/**
	 * unschedule job
	 * 
	 * @param schedulerDetails
	 * @throws OorniException
	 */
	@POST
	@Path("/unscheduleJob")
	@Produces("application/json")
	Map<String, Object> unscheduleJob(Map<String, Object> schedulerDetails)
			throws OorniException;

	/**
	 * delete job from schedule
	 * 
	 * @param schedulerDetails
	 * @throws OorniException
	 */
	@POST
	@Path("/deleteJob")
	@Produces("application/json")
	Map<String, Object> deleteJob(Map<String, Object> schedulerDetails)
			throws OorniException;
}
