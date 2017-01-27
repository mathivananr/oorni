package com.oorni.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.oorni.Constants;
import com.oorni.common.OorniException;
import com.oorni.dao.MerchantDao;
import com.oorni.model.Merchant;
import com.oorni.model.MerchantType;
import com.oorni.model.OfferLabel;
import com.oorni.service.MerchantManager;
import com.oorni.util.StringUtil;

@Service("merchantManager")
public class MerchantManagerImpl extends GenericManagerImpl<Merchant, Long>
		implements MerchantManager {

	private MerchantDao merchantDao;

	@Autowired
	public MerchantManagerImpl(MerchantDao merchantDao) {
		super(merchantDao);
		this.merchantDao = merchantDao;
	}

	public Merchant saveMerchant(Merchant merchant) {
		return merchantDao.saveMerchant(merchant);
	}

	public Merchant saveMerchant(Merchant merchant, CommonsMultipartFile file,
			String uploadDir) throws OorniException {
		if (file != null && !file.isEmpty()) {
			// the directory to upload to
			uploadDir += Constants.FILE_SEP + "merchant" + Constants.FILE_SEP
					+ "logo" + Constants.FILE_SEP;
			// Create the directory if it doesn't exist
			File dirPath = new File(uploadDir);
			if (!dirPath.exists()) {
				dirPath.mkdirs();
			}
			// retrieve the file data
			InputStream stream;
			try {
				stream = file.getInputStream();
				// write the file to the file specified
				OutputStream bos = new FileOutputStream(
						uploadDir
								+ merchant.getMerchantName().toLowerCase().replaceAll(" ",
										"-")
								+ "."
								+ FilenameUtils.getExtension(file
										.getOriginalFilename()));
				int bytesRead;
				byte[] buffer = new byte[8192];
				while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
					bos.write(buffer, 0, bytesRead);
				}
				bos.close();
				// close the stream
				stream.close();
				String logoPath = Constants.FILE_SEP;
				if (uploadDir.contains("files")) {
					logoPath = logoPath + "files";
				} else if (uploadDir.contains("images")) {
					logoPath = logoPath + "images";
				}
				logoPath = logoPath
						+ Constants.FILE_SEP
						+ "merchant"
						+ Constants.FILE_SEP
						+ "logo"
						+ Constants.FILE_SEP
						+ merchant.getMerchantName().toLowerCase().replaceAll(" ", "-")
						+ "."
						+ FilenameUtils
								.getExtension(file.getOriginalFilename());
				merchant.setLogoPath(logoPath);
			} catch (IOException e) {
				throw new OorniException("problem in saving logo...");
			}
		}

		OfferLabel label = getOfferLabelByLabel(merchant.getMerchantName()
				.toLowerCase());
		if (StringUtil.isEmptyString(label) || !label.getIsMerchant()) {
			label = new OfferLabel(merchant.getMerchantName().toLowerCase());
			label.setIsMerchant(true);
			saveOfferLabel(label);
		}
		merchant.setMerchantName(merchant.getMerchantName().toLowerCase());
		return merchantDao.saveMerchant(merchant);
	}

	public List<Merchant> getAllMerchant() {
		return merchantDao.getAllMerchant();
	}

	public Merchant getMerchantById(Long merchantId) throws OorniException {
		return merchantDao.getMerchantById(merchantId);
	}

	public Merchant getMerchantByName(String merchantName) throws OorniException {
		return merchantDao.getMerchantByName(merchantName);
	}

	public MerchantType saveMerchantType(MerchantType merchantType) throws OorniException{
		return merchantDao.saveMerchantType(merchantType);
	}

	public List<MerchantType> getAllMerchantTypes() {
		return merchantDao.getAllMerchantTypes();
	}

	public MerchantType getMerchantTypeById(Long merchantTypeId)
			throws OorniException {
		return merchantDao.getMerchantTypeById(merchantTypeId);
	}

	public List<Merchant> getMerchantByType(String merchantType) {
		return merchantDao.getMerchantByType(merchantType);
	}

	public List<Merchant> getMerchantByTypes(List<String> merchantTypes) {
		return merchantDao.getMerchantByTypes(merchantTypes);
	}
	
	public List<MerchantType> getMerchantTypesByIds(List<String> ids) {
		return merchantDao.getMerchantTypesByIds(ids);
	}

	public MerchantType getMerchantTypeByName(String merchantTypeName)
			throws OorniException {
		return merchantDao.getMerchantTypeByName(merchantTypeName);
	}

	public List<MerchantType> getMerchantTypeLikeName(String merchantTypeName)
			throws OorniException {
		return merchantDao.getMerchantTypeLikeName(merchantTypeName);
	}

	public OfferLabel getOfferLabelByLabel(String label) throws OorniException {
		return merchantDao.getOfferLabelByLabel(label);
	}

	public OfferLabel saveOfferLabel(OfferLabel offerLabel) throws OorniException {
		return merchantDao.saveOfferLabel(offerLabel);
	}
	
	public List<String> getMerchantNames() throws OorniException {
		return merchantDao.getMerchantNames();
	}
	
	public List<String> getMerchantNames(String name)
			throws OorniException {
		return merchantDao.getMerchantNames(name);
	}
}
