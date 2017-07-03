package com.oorni.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FilenameUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.oorni.Constants;
import com.oorni.common.OorniException;
import com.oorni.dao.OfferDao;
import com.oorni.model.Carousel;
import com.oorni.model.Merchant;
import com.oorni.model.Offer;
import com.oorni.model.OfferLabel;
import com.oorni.service.MerchantManager;
import com.oorni.service.OfferManager;
import com.oorni.util.CommonUtil;
import com.oorni.util.StringUtil;

@Service("offerManager")
public class OfferManagerImpl extends GenericManagerImpl<Offer, Long> implements
		OfferManager {

	private OfferDao offerDao;
	private MerchantManager merchantManager;
	private VelocityEngine velocityEngine;
	
	@Autowired
	public OfferManagerImpl(OfferDao offerDao) {
		super(offerDao);
		this.offerDao = offerDao;
	}

	@Autowired
	public void setMerchantManager(MerchantManager merchantManager) {
		this.merchantManager = merchantManager;
	}

	@Autowired
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public Offer saveOffer(Offer offer) throws OorniException {
		
		if (!StringUtil.isEmptyString(offer.getLabelsString())) {
			List<String> labelsString = new ArrayList<String>();
			String[] labelsArray = offer.getLabelsString().split(",");
			for (int i = 0; i < labelsArray.length; i++) {
				String label = labelsArray[i].trim();
				labelsString.add(label);
			}
			List<OfferLabel> offerLabels = offerDao
					.getOfferLabels(labelsString);
			for (String label : labelsString) {
				boolean isNotExists = true;
				OfferLabel newLabelObj = null;
				for (OfferLabel labelObj : offerLabels) {
					if (label.equalsIgnoreCase(labelObj.getLabel())) {
						isNotExists = false;
						newLabelObj = labelObj;
						break;
					}
				}
				if (isNotExists) {
					if (!StringUtil.isEmptyString(label.trim())) {
						OfferLabel offerLabel = new OfferLabel(label.trim());
						Merchant merchant = merchantManager
								.getMerchantByName(label.trim());
						if (merchant != null) {
							offer.setMerchantName(merchant.getMerchantName());
							offer.setMerchantLogoPath(merchant.getLogoPath());
						}
						offerLabel = saveOfferLabel(offerLabel);
						offer.getLabels().add(offerLabel);
					}
				} else {
					offer.getLabels().add(newLabelObj);
				}
			}
		}
		Calendar now = new GregorianCalendar();
		if (StringUtil.isEmptyString(offer.getOfferId())) {
			offer.setCreatedOn(now);
			offer.setCreatedBy(CommonUtil.getLoggedInUserName());
		}
		offer.setUpdatedOn(now);
		offer.setUpdatedBy(CommonUtil.getLoggedInUserName());
		return offerDao.saveOffer(offer);
	}

	public List<Offer> getOffersAddedByMe() throws OorniException{
		List<Offer> newOffers = new ArrayList<Offer>();
		List<Offer> offers = offerDao.getOffersAddedByMe(CommonUtil.getLoggedInUserName());
		for(Offer offer: offers) {
			offer.setURL("/hook/offer/"+offer.getOfferId());
			newOffers.add(offer);
		}
		return newOffers;
	}
	
	public OfferLabel saveOfferLabel(OfferLabel offerLabel) throws OorniException {
		Merchant merchant = merchantManager.getMerchantByName(offerLabel
				.getLabel());
		if (merchant != null) {
			offerLabel.setIsMerchant(true);
		}
		return offerDao.saveOfferLabel(offerLabel);
	}

	public List<OfferLabel> saveOfferLabels(List<OfferLabel> offerLabels)
			throws OorniException {
		List<OfferLabel> updatedLabels = new ArrayList<OfferLabel>();
		for (OfferLabel offerLabel : offerLabels) {
			log.info("saving label " + offerLabel.getLabel());
			OfferLabel oldLabel = offerDao.getOfferLabelByLabel(offerLabel
					.getLabel());
			if (oldLabel == null) {
				updatedLabels.add(saveOfferLabel(offerLabel));
			} else {
				if (!StringUtil.isEmptyString(offerLabel.getLabel())) {
					oldLabel.setLabel(offerLabel.getLabel());
				}
				if (!StringUtil.isEmptyString(offerLabel.getMetaDescription())) {
					oldLabel.setMetaDescription(offerLabel.getMetaDescription());
				}
				if (!StringUtil.isEmptyString(offerLabel.getMetaKeyword())) {
					oldLabel.setMetaKeyword(offerLabel.getMetaKeyword());
				}

				if (!StringUtil.isEmptyString(offerLabel.getIsMerchant())) {
					oldLabel.setIsMerchant(offerLabel.getIsMerchant());
				}

				updatedLabels.add(saveOfferLabel(oldLabel));
			}

			log.info("saved label " + offerLabel.getLabel());
		}
		return updatedLabels;
	}

	public List<Offer> saveOffers(List<Offer> offers) throws OorniException {
		List<Offer> updatedOffers = new ArrayList<Offer>();
		for (Offer offer : offers) {
			try {
				Offer oldOffer = getOffer(offer.getOfferTitle(),
						offer.getMerchantName(), offer.getCouponCode(),
						offer.getOfferEnd(), offer.getTargetURL());
				if (oldOffer == null) {
					log.info("saving offer " + offer.getOfferTitle() + " for "
							+ offer.getMerchantName());
					updatedOffers.add(saveOffer(offer));
					log.info("saved offer " + offer.getOfferTitle() + " for "
							+ offer.getMerchantName());
				} else {
					log.info(offer.getOfferTitle() + " for "
							+ offer.getMerchantName() + " already exist ");
				}
			} catch (OorniException e) {
				log.error(e.getMessage(), e);
			}
		}
		return updatedOffers;
	}

	public List<OfferLabel> importOfferLabels(String filePath)
			throws OorniException {
		List<OfferLabel> offerLabels = new ArrayList<OfferLabel>();
		// CSV file header
		String[] FILE_HEADER_MAPPING = { "Label", "Meta Keyword",
				"Meta Description" };
		FileReader fileReader = null;
		CSVParser csvFileParser = null;
		// Create the CSVFormat object with the header mapping
		CSVFormat csvFileFormat = CSVFormat.DEFAULT
				.withHeader(FILE_HEADER_MAPPING);
		try {
			// initialize FileReader object
			fileReader = new FileReader(filePath);
			// initialize CSVParser object
			csvFileParser = new CSVParser(fileReader, csvFileFormat);
			// Get a list of CSV file records
			List<CSVRecord> csvRecords = csvFileParser.getRecords();
			// Read the CSV file records starting from the second record to skip
			// the header
			for (int i = 1; i < csvRecords.size(); i++) {
				CSVRecord record = (CSVRecord) csvRecords.get(i);
				// Create a new student object and fill his data
				OfferLabel offerLabel = new OfferLabel();
				offerLabel.setLabel(record.get("Label"));
				offerLabel.setMetaKeyword(record.get("Meta Keyword"));
				offerLabel.setMetaDescription(record.get("Meta Description"));
				offerLabels.add(offerLabel);
			}
			offerLabels = saveOfferLabels(offerLabels);
		} catch (Exception e) {
			System.out.println("Error in CsvFileReader !!!");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
				csvFileParser.close();
			} catch (IOException e) {
				System.out
						.println("Error while closing fileReader/csvFileParser !!!");
				e.printStackTrace();
			}
		}
		return offerLabels;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getOffersContent(List<String> labels, int start, int end)
			throws OorniException {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("offers", getOffersByLabels(labels, start, end));
		return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
				"offers.vm", "UTF-8", model);
	}
	
	public List<Offer> getAllOffers() throws OorniException {
		return offerDao.getAllOffers();
	}

	public Offer getOfferById(Long offerId) throws OorniException {
		return offerDao.getOfferById(offerId);
	}

	public List<OfferLabel> getAllOfferLabels() throws OorniException {
		return offerDao.getAllOfferLabels();
	}

	public List<Offer> getOffersByLabel(String label) throws OorniException {
		List<Offer> newOffers = new ArrayList<Offer>();
		List<Offer> offers = offerDao.getOffersByLabel(label);
		for(Offer offer: offers) {
			offer.setURL("/hook/offer/"+offer.getOfferId());
			newOffers.add(offer);
		}
		return newOffers;
	}

	public Offer getOfferByTitle(String offerTitle) throws OorniException {
		return offerDao.getOfferByTitle(offerTitle);
	}

	public Offer getOffer(String offerTitle, String merchantName,
			String couponCode, Calendar offerEnd, String targetURL) throws OorniException {
		return offerDao
				.getOffer(offerTitle, merchantName, couponCode, offerEnd,targetURL);
	}

	public OfferLabel getOfferLabelById(Long labelId) throws OorniException {
		return offerDao.getOfferLabelById(labelId);
	}

	public OfferLabel getOfferLabelByLabel(String label) throws OorniException {
		return offerDao.getOfferLabelByLabel(label);
	}
	
	public List<Offer> getOffersByLabels(List<String> labels, int start, int end)
			throws OorniException {
		List<Offer> newOffers = new ArrayList<Offer>();
		List<Offer> offers = offerDao.getOffersByLabels(labels, start, end);
		for(Offer offer: offers) {
			offer.setURL("/hook/offer/"+offer.getOfferId());
			newOffers.add(offer);
		}
		return newOffers;
	}
	
	public List<String> getSuggestLabels(String label)
			throws OorniException {
		return offerDao.getSuggestLabels(label);
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 */
	public Carousel saveCarousel(Carousel carousel, CommonsMultipartFile file,
			String uploadDir) throws OorniException {
		if (file != null && !file.isEmpty()) {
			// the directory to upload to
			uploadDir += Constants.FILE_SEP + "carousel" + Constants.FILE_SEP
					+ new Date() + Constants.FILE_SEP;
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
								+ carousel.getCarouselTitle().substring(0,100).toLowerCase().replaceAll(" ",
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
				String imagePath = Constants.FILE_SEP;
				if (uploadDir.contains("files")) {
					imagePath = imagePath + "files";
				} else if (uploadDir.contains("images")) {
					imagePath = imagePath + "images";
				}
				imagePath = imagePath
						+ Constants.FILE_SEP
						+ "carousel"
						+ Constants.FILE_SEP
						+ new Date()
						+ Constants.FILE_SEP
						+ carousel.getCarouselTitle().substring(0,100).toLowerCase().replaceAll(" ", "-")
						+ "."
						+ FilenameUtils
								.getExtension(file.getOriginalFilename());
				carousel.setImagePath(imagePath);
			} catch (IOException e) {
				throw new OorniException("problem in saving logo...");
			}
		}
		
		if (!StringUtil.isEmptyString(carousel.getLabelsString())) {
			List<String> labelsString = new ArrayList<String>();
			String[] labelsArray = carousel.getLabelsString().split(",");
			for (int i = 0; i < labelsArray.length; i++) {
				String label = labelsArray[i].trim();
				labelsString.add(label);
			}
			List<OfferLabel> carouselLabels = offerDao
					.getOfferLabels(labelsString);
			for (String label : labelsString) {
				boolean isNotExists = true;
				OfferLabel newLabelObj = null;
				for (OfferLabel labelObj : carouselLabels) {
					if (label.equalsIgnoreCase(labelObj.getLabel())) {
						isNotExists = false;
						newLabelObj = labelObj;
						break;
					}
				}
				if (isNotExists) {
					if (!StringUtil.isEmptyString(label.trim())) {
						OfferLabel carouselLabel = new OfferLabel(label.trim());
						Merchant merchant = merchantManager
								.getMerchantByName(label.trim());
						if (merchant != null) {
							carousel.setMerchantName(merchant.getMerchantName());
						}
						carouselLabel = saveOfferLabel(carouselLabel);
						carousel.getLabels().add(carouselLabel);
					}
				} else {
					carousel.getLabels().add(newLabelObj);
				}
			}
		}
		Calendar now = new GregorianCalendar();
		if (StringUtil.isEmptyString(carousel.getCarouselId())) {
			carousel.setCreatedOn(now);
			carousel.setCreatedBy(CommonUtil.getLoggedInUserName());
		}
		carousel.setUpdatedOn(now);
		carousel.setUpdatedBy(CommonUtil.getLoggedInUserName());
		return offerDao.saveCarousel(carousel);
	}
	
	public Carousel getCarouselById(Long carouselId) throws OorniException {
		return offerDao.getCarouselById(carouselId);
	}
	
	public List<Carousel> getAllCarousels() throws OorniException {
		return offerDao.getAllCarousels();
	}
	
    public List<Carousel> getActiveCarousels() throws OorniException {
    	return offerDao.getActiveCarousels();
    }
	
	public List<Carousel> getCarouselsByLabels(List<String> labels) 
			throws OorniException {
		return offerDao.getCarouselsByLabels(labels);
	}
}
