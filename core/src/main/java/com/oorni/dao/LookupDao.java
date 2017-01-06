package com.oorni.dao;

import java.util.List;

import com.oorni.model.AppConfig;
import com.oorni.model.MerchantType;
import com.oorni.model.Role;

/**
 * Lookup Data Access Object (GenericDao) interface.  This is used to lookup values in
 * the database (i.e. for drop-downs).
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public interface LookupDao {
    //~ Methods ================================================================

    /**
     * Returns all Roles ordered by name
     * @return populated list of roles
     */
    List<Role> getRoles();
    
    List<MerchantType> getMerchantTypes();
    
	List<AppConfig> getConfigs();
	
	List<String> getAppConfigTypes();
	
	List<AppConfig> getAppConfigsByType(String type);
}
