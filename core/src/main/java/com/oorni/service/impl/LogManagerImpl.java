package com.oorni.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oorni.common.OorniException;
import com.oorni.dao.LogDao;
import com.oorni.model.AppLog;
import com.oorni.service.LogManager;

@Service("logManager")
public class LogManagerImpl extends GenericManagerImpl<AppLog, Long> implements
		LogManager {

	private LogDao logDao;

	@Autowired
	public LogManagerImpl(LogDao logDao) {
		super(logDao);
		this.logDao = logDao;
	}

	public AppLog log(AppLog log) throws OorniException {
		return logDao.log(log);
	}
}
