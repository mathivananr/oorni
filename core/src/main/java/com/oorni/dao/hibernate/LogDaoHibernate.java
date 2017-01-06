package com.oorni.dao.hibernate;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oorni.common.OorniException;
import com.oorni.dao.LogDao;
import com.oorni.model.AppLog;

@Repository("logDao")
public class LogDaoHibernate extends GenericDaoHibernate<AppLog, Long>
		implements LogDao {

	/**
	 * Constructor to create a Generics-based version using AppLog as the entity
	 */
	public LogDaoHibernate() {
		super(AppLog.class);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws OorniException
	 */
	@Transactional
	public AppLog log(AppLog appLog) throws OorniException {
		try {
			return (AppLog) getSession().merge(appLog);
		} catch (HibernateException e) {
			throw new OorniException(e.getMessage(), e);
		}
	}
}
