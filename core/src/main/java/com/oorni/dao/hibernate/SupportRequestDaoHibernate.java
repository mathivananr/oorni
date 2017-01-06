package com.oorni.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.oorni.dao.SupportRequestDao;
import com.oorni.model.SupportRequest;

@Repository("supportRequestDao")
public class SupportRequestDaoHibernate extends
		GenericDaoHibernate<SupportRequest, Long> implements SupportRequestDao {

	public SupportRequestDaoHibernate() {
		super(SupportRequest.class);
		// TODO Auto-generated constructor stub
	}

}
