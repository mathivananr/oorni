package com.oorni.service;

import java.util.Calendar;
import java.util.List;

import com.oorni.common.OorniException;
import com.oorni.model.Report;

public interface ReportManager extends GenericManager<Report, Long> {

	Report saveReport(Report report) throws OorniException;

	List<Report> getAllReport() throws OorniException;

	Report getReportById(Long reportId) throws OorniException;

	List<Report> getMyReport() throws OorniException;

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
