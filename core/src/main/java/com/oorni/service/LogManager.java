package com.oorni.service;

import com.oorni.common.OorniException;
import com.oorni.model.AppLog;

public interface LogManager extends GenericManager<AppLog, Long> {
	
	AppLog log(AppLog log) throws OorniException;
	
}
