package com.oorni.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.oorni.Constants;
import com.oorni.common.OorniException;
import com.oorni.model.Report;

public interface ReportDao extends GenericDao<Report, Long> {

	/**
	 * 
	 */
	Report saveReport(Report report);

	/**
	 * 
	 */
	List<Report> getAllReport();

	/**
	 * 
	 */
	Report getReportById(Long reportId) throws OorniException;

	/**
	 * 
	 */
	List<Report> getReportByOwnerId(String userId) throws OorniException;
	
	/**
	 * 
	 */
	Long getClicksCount(Calendar from, Calendar to, String ownerId) throws OorniException;
	
	/**
	 * 
	 */
	Long getConversionCount(Calendar from, Calendar to, String ownerId) throws OorniException;
	
	/**
	 * 
	 */
	Double getTotalPayout(Calendar from, Calendar to, String ownerId) throws OorniException;
}