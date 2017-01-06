package com.oorni.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.oorni.common.OorniException;
import com.oorni.model.Merchant;
import com.oorni.model.MerchantType;
import com.oorni.model.Offer;
import com.oorni.model.OfferLabel;

public interface MerchantDao extends GenericDao<Merchant, Long> {

	Merchant saveMerchant(Merchant merchant);

	List<Merchant> getAllMerchant();

	Merchant getMerchantById(Long merchantId) throws OorniException;

	Merchant getMerchantByName(String merchantName) throws OorniException;

	MerchantType saveMerchantType(MerchantType merchantType) throws OorniException;

	List<MerchantType> getAllMerchantTypes();

	MerchantType getMerchantTypeById(Long merchantTypeId) throws OorniException;

	MerchantType getMerchantTypeByName(String merchantTypeName)
			throws OorniException;

	List<MerchantType> getMerchantTypeLikeName(String merchantTypeName)
			throws OorniException;
			
	List<Merchant> getMerchantByType(String merchantType);

	List<Merchant> getMerchantByTypes(List<String> merchantTypes);
	
	OfferLabel getOfferLabelByLabel(String label) throws OorniException;
	
	OfferLabel saveOfferLabel(OfferLabel offerLabel) throws OorniException;
	
	List<String> getMerchantNames() throws OorniException;
	
	List<String> getMerchantNames(String name)
			throws OorniException;
}
