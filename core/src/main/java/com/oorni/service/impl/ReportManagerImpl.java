package com.oorni.service.impl;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oorni.common.OorniException;
import com.oorni.dao.ReportDao;
import com.oorni.model.Report;
import com.oorni.model.Report;
import com.oorni.service.ReportManager;
import com.oorni.util.CommonUtil;
import com.oorni.util.StringUtil;

@Service("reportManager")
public class ReportManagerImpl extends GenericManagerImpl<Report, Long>
		implements ReportManager {

	private ReportDao reportDao;

	@Autowired
	public ReportManagerImpl(ReportDao reportDao) {
		super(reportDao);
		this.reportDao = reportDao;
	}

	public Report saveReport(Report report) throws OorniException {
		if(StringUtil.isEmptyString(report.getReportId())){
			report.setCreatedOn(new GregorianCalendar());
			report.setCreatedBy(CommonUtil.getLoggedInUserName());
			report.setUpdatedBy(CommonUtil.getLoggedInUserName());
			report.setUpdatedOn(new GregorianCalendar());
		} else {
			report.setUpdatedOn(new GregorianCalendar());
			report.setUpdatedBy(CommonUtil.getLoggedInUserName());
		}
		return reportDao.saveReport(report);
	}

	public List<Report> getAllReport() throws OorniException {
		return reportDao.getAllReport();
	}

	public Report getReportById(Long reportId) throws OorniException {
		return reportDao.getReportById(reportId);
	}

	public List<Report> getMyReport() throws OorniException {
		return reportDao.getReportByOwnerId(CommonUtil.getLoggedInUserId());
	}
	
	public Long getClicksCount(Calendar from, Calendar to, String ownerId) throws OorniException{
		return reportDao.getClicksCount(from, to, ownerId);
	}

	public Long getConversionCount(Calendar from, Calendar to, String ownerId) throws OorniException{
		return reportDao.getConversionCount(from, to, ownerId);
	}

	public Double getTotalPayout(Calendar from, Calendar to, String ownerId) throws OorniException{
		return reportDao.getTotalPayout(from, to, ownerId);
	}
}
