package com.oorni.dao;

import com.oorni.common.OorniException;
import com.oorni.model.AppLog;

public interface LogDao extends GenericDao<AppLog, Long> {
	AppLog log(AppLog log) throws OorniException;
}
