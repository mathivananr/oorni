package com.oorni.service;

import java.util.List;
import java.util.Map;

import com.oorni.model.AppConfig;
import com.oorni.model.LabelValue;

/**
 * Business Service Interface to talk to persistence layer and retrieve values
 * for drop-down choice lists.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public interface LookupManager {
	/**
	 * Retrieves all possible roles from persistence layer
	 * 
	 * @return List of LabelValue objects
	 */
	List<LabelValue> getAllRoles();

	List<LabelValue> getMerchantTypes();

	Map<String, String> getConfigs();

	List<String> getAppConfigTypes();

	List<AppConfig> getAppConfigsByType(String type);
}
