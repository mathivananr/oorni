package com.oorni.service;

import java.util.List;
import java.util.Map;

import com.oorni.common.OorniException;

/**
 * Web Service interface for manage schedules.
 * 
 * @author mathi
 */

public interface SchedulerManager {

    /**
     * schedule the job
     * 
     * @param schedulerDetails
     * @throws OorniException
     */
    Map<String, Object> scheduleJob(Map<String, Object> schedulerDetails)
            throws OorniException;

    /**
	 * start scheduler
	 * 
	 * @return
	 * 
	 * @throws OorniException
	 */
	Map<String, Object> startScheduler() throws OorniException;
	
	/**
	 * shutdown scheduler
	 * 
	 * @return
	 * 
	 * @throws OorniException
	 */
	Map<String, Object> shutdownScheduler() throws OorniException;

    /**
     * reschedule the job
     * 
     * @param schedulerDetails
     * @throws OorniException
     */
    Map<String, Object> rescheduleJob(Map<String, Object> schedulerDetails)
            throws OorniException;

    /**
     * unschedule job
     * 
     * @param schedulerDetails
     * @throws OorniException
     */
    Map<String, Object> unscheduleJob(Map<String, Object> schedulerDetails)
            throws OorniException;

    /**
     * delete job from schedule
     * 
     * @param schedulerDetails
     * @throws OorniException
     */
    Map<String, Object> deleteJob(Map<String, Object> schedulerDetails)
            throws OorniException;
    
    Map<String, Object> getJob(String jobName) throws OorniException;
    
    List<Map<String, Object>> getAllJobs() throws OorniException;
    
    boolean stopJob(String jobName) throws OorniException; 
    
    boolean startJob(String jobName) throws OorniException;
    
    boolean checkJobExists(String jobName) throws OorniException;
}
