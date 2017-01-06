package com.oorni.dao.hibernate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.TransientObjectException;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oorni.Constants;
import com.oorni.common.OorniException;
import com.oorni.dao.ReportDao;
import com.oorni.model.Report;
import com.oorni.util.StringUtil;

@Repository
public class ReportDaoHibernate extends GenericDaoHibernate<Report, Long> implements ReportDao {

	public ReportDaoHibernate() {
		super(Report.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public Report saveReport(Report report) {
		try {
			report = (Report) getSession().merge(report);
		} catch (TransientObjectException e) {
			e.printStackTrace();
		}
		return report;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Report> getAllReport() {
		return getSession().createCriteria(Report.class).list();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws OorniException
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Report getReportById(Long reportId) throws OorniException {
		List<Report> reports = getSession().createCriteria(Report.class).add(Restrictions.eq("reportId", reportId))
				.list();
		if (reports != null && reports.size() > 0) {
			reports.get(0).getOwner().getStore();
			return reports.get(0);
		} else {
			throw new OorniException("No Report found for id " + reportId);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws OorniException
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Report> getReportByOwnerId(String ownerId) throws OorniException {
		try {
			List<Report> reports = getSession().createCriteria(Report.class)
					.add(Restrictions.eq("owner.id", Long.parseLong(ownerId))).list();
			if (reports != null && reports.size() > 0) {
				return reports;
			} else {
				return new ArrayList<>();
			}
		} catch (HibernateException e) {
			throw new OorniException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws OorniException
	 */
	@Transactional
	public Long getClicksCount(Calendar from, Calendar to, String ownerId) throws OorniException {
		Criteria criteria = getSession().createCriteria(Report.class);
		criteria.setProjection(Projections.rowCount());
		if (!StringUtil.isEmptyString(ownerId)) {
			criteria.add(Restrictions.eq("owner.id", Long.parseLong(ownerId)));
		}
		Long clicks = (Long) criteria.uniqueResult();
		return clicks;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws OorniException
	 */
	@Transactional
	public Long getConversionCount(Calendar from, Calendar to, String ownerId) throws OorniException {
		Criteria criteria = getSession().createCriteria(Report.class);
		criteria.setProjection(Projections.rowCount());
		criteria.add(Restrictions.eq("status", Constants.STATUS_CONVERSION)).uniqueResult();
		if (!StringUtil.isEmptyString(ownerId)) {
			criteria.add(Restrictions.eq("owner.id", Long.parseLong(ownerId)));
		}
		Long conversions = (Long) criteria.uniqueResult();
		return conversions;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws OorniException
	 */
	@Transactional
	public Double getTotalPayout(Calendar from, Calendar to, String ownerId) throws OorniException {
		Criteria criteria = getSession().createCriteria(Report.class);
		criteria.setProjection(Projections.sum("payout"));
		if (!StringUtil.isEmptyString(ownerId)) {
			criteria.add(Restrictions.eq("owner.id", Long.parseLong(ownerId)));
		}
		Double sum = (Double) criteria.uniqueResult();
		return sum;
	}
}