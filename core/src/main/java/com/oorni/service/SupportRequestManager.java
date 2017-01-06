package com.oorni.service;

import com.oorni.common.OorniException;
import com.oorni.model.SupportRequest;

public interface SupportRequestManager extends
		GenericManager<SupportRequest, Long> {
	SupportRequest closeSupportRequest(String id) throws OorniException;
}
