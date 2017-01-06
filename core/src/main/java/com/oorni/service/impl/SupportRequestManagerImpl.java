package com.oorni.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oorni.Constants;
import com.oorni.common.OorniException;
import com.oorni.dao.SupportRequestDao;
import com.oorni.model.SupportRequest;
import com.oorni.service.SupportRequestManager;

@Service("supportRequest")
public class SupportRequestManagerImpl extends
		GenericManagerImpl<SupportRequest, Long> implements
		SupportRequestManager {

	private SupportRequestDao supportRequestDao;

	@Autowired
	public SupportRequestManagerImpl(SupportRequestDao supportRequestDao) {
		super(supportRequestDao);
		this.supportRequestDao = supportRequestDao;
	}

	public SupportRequest closeSupportRequest(String id) throws OorniException {
		SupportRequest supportRequest = supportRequestDao.get(Long
				.parseLong(id));
		supportRequest.setStatus(Constants.SR_CLOSE);
		supportRequestDao.save(supportRequest);
		return supportRequest;
	}
}
