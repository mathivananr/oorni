package com.oorni.webapp.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.oorni.Constants;
import com.oorni.model.AppConfig;
import com.oorni.model.LabelValue;
import com.oorni.service.GenericManager;
import com.oorni.service.LookupManager;
import com.oorni.util.PropertyReader;
import com.oorni.util.StringUtil;

/**
 * <p>
 * StartupListener class used to initialize and database settings and populate
 * any application-wide drop-downs.
 * <p/>
 * <p>
 * Keep in mind that this listener is executed outside of
 * OpenSessionInViewFilter, so if you're using Hibernate you'll have to
 * explicitly initialize all loaded data at the GenericDao or service level to
 * avoid LazyInitializationException. Hibernate.initialize() works well for
 * doing this.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class StartupListener implements ServletContextListener {
	private static final Log log = LogFactory.getLog(StartupListener.class);

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void contextInitialized(ServletContextEvent event) {
		log.debug("Initializing context...");

		ServletContext context = event.getServletContext();

		// Orion starts Servlets before Listeners, so check if the config
		// object already exists
		Map<String, Object> config = (HashMap<String, Object>) context
				.getAttribute(Constants.CONFIG);

		if (config == null) {
			config = new HashMap<>();
		}

		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(context);

		PasswordEncoder passwordEncoder = null;
		try {
			ProviderManager provider = (ProviderManager) ctx
					.getBean("org.springframework.security.authentication.ProviderManager#0");
			for (Object o : provider.getProviders()) {
				AuthenticationProvider p = (AuthenticationProvider) o;
				if (p instanceof RememberMeAuthenticationProvider) {
					config.put("rememberMeEnabled", Boolean.TRUE);
				} else if (ctx.getBean("passwordEncoder") != null) {
					passwordEncoder = (PasswordEncoder) ctx
							.getBean("passwordEncoder");
				}
			}
		} catch (NoSuchBeanDefinitionException n) {
			log.debug("authenticationManager bean not found, assuming test and ignoring...");
			// ignore, should only happen when testing
		}

		context.setAttribute(Constants.CONFIG, config);

		// output the retrieved values for the Init and Context Parameters
		if (log.isDebugEnabled()) {
			log.debug("Remember Me Enabled? " + config.get("rememberMeEnabled"));
			if (passwordEncoder != null) {
				log.debug("Password Encoder: "
						+ passwordEncoder.getClass().getSimpleName());
			}
			log.debug("Populating drop-downs...");
		}

		setupContext(context);

		// Determine version number for CSS and JS Assets
		String appVersion = null;
		try {
			InputStream is = context
					.getResourceAsStream("/META-INF/MANIFEST.MF");
			if (is == null) {
				log.warn("META-INF/MANIFEST.MF not found.");
			} else {
				Manifest mf = new Manifest();
				mf.read(is);
				Attributes atts = mf.getMainAttributes();
				appVersion = atts.getValue("Implementation-Version");
			}
		} catch (IOException e) {
			log.error("I/O Exception reading manifest: " + e.getMessage());
		}

		// If there was a build number defined in the war, then use it for
		// the cache buster. Otherwise, assume we are in development mode
		// and use a random cache buster so developers don't have to clear
		// their browser cache.
		if (appVersion == null || appVersion.contains("SNAPSHOT")) {
			appVersion = "" + new Random().nextInt(100000);
		}

		log.info("Application version set to: " + appVersion);
		context.setAttribute(Constants.ASSETS_VERSION, appVersion);
	}

	/**
	 * This method uses the LookupManager to lookup available roles from the
	 * data layer.
	 * 
	 * @param context
	 *            The servlet context
	 */
	public static void setupContext(ServletContext context) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(context);
		LookupManager mgr = (LookupManager) ctx.getBean("lookupManager");

		// get list of possible roles
		context.setAttribute(Constants.AVAILABLE_ROLES, mgr.getAllRoles());
		log.debug("Drop-down initialization complete [OK]");

		context.setAttribute(Constants.MERCHANT_TYPES, new HashSet<LabelValue>(
				mgr.getMerchantTypes()));
		log.debug("Drop-down merchant type initialization complete [OK]");

		loadAppConfigs(mgr);

		PropertyReader reader = PropertyReader.getInstance();
		if(!StringUtil.isEmptyString(reader.getPropertyFromFile(Constants.DATA_TYPE_STRING,
							Constants.APP_URL_CONFIG_KEY))) {
			context.setAttribute(
					Constants.APP_URL,
					reader.getPropertyFromFile(Constants.DATA_TYPE_STRING,
							Constants.APP_URL_CONFIG_KEY).toString());
			log.debug("application url loaded");
		}
		
		if (!StringUtil.isEmptyString(reader.getPropertyFromFile(
				Constants.DATA_TYPE_STRING, Constants.APP_NAME_CONFIG_KEY))) {
			context.setAttribute(
					Constants.APP_NAME,
					reader.getPropertyFromFile(Constants.DATA_TYPE_STRING,
							Constants.APP_NAME_CONFIG_KEY).toString());
			log.debug("application name loaded");
		}

		if (!StringUtil.isEmptyString(reader.getPropertyFromFile(
				Constants.DATA_TYPE_STRING, Constants.APP_TITLE_CONFIG_KEY))) {
			context.setAttribute(
					Constants.APP_TITLE,
					reader.getPropertyFromFile(Constants.DATA_TYPE_STRING,
							Constants.APP_TITLE_CONFIG_KEY).toString());
			log.debug("application title loaded");
		}

		if (!StringUtil.isEmptyString(reader.getPropertyFromFile(
				Constants.DATA_TYPE_STRING, Constants.APP_META_KEYWORD_CONFIG_KEY))) {
			context.setAttribute(
					Constants.APP_META_KEYWORD,
					reader.getPropertyFromFile(Constants.DATA_TYPE_STRING,
							Constants.APP_META_KEYWORD_CONFIG_KEY).toString());
			log.debug("application meta keywords loaded");
		}
		if (!StringUtil.isEmptyString(reader.getPropertyFromFile(
				Constants.DATA_TYPE_STRING, Constants.APP_META_DESCRIPTION_CONFIG_KEY))) {
			context.setAttribute(
					Constants.APP_META_DESCRIPTION,
					reader.getPropertyFromFile(Constants.DATA_TYPE_STRING,
							Constants.APP_META_DESCRIPTION_CONFIG_KEY).toString());
			log.debug("application meta description loaded");
		}
		
		// Any manager extending GenericManager will do:
		GenericManager manager = (GenericManager) ctx.getBean("userManager");
		doReindexing(manager);
		log.debug("Full text search reindexing complete [OK]");
	}

	private static void doReindexing(GenericManager manager) {
		manager.reindexAll(false);
	}

	/**
	 * Shutdown servlet context (currently a no-op method).
	 * 
	 * @param servletContextEvent
	 *            The servlet context event
	 */
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		// LogFactory.release(Thread.currentThread().getContextClassLoader());
		// Commented out the above call to avoid warning when SLF4J in
		// classpath.
		// WARN: The method class
		// org.apache.commons.logging.impl.SLF4JLogFactory#release() was
		// invoked.
		// WARN: Please see http://www.slf4j.org/codes.html for an explanation.
	}

	public static void loadAppConfigs(LookupManager mgr) {
		Properties properties;
		PropertyReader reader = PropertyReader.getInstance();
		List<String> configTypeList = mgr.getAppConfigTypes();

		if (configTypeList != null && configTypeList.size() > 0) {
			for (String configType : configTypeList) {
				log.info("================App Config================="
						+ configType);
				properties = new Properties();
				if (configType != null) {
					List<AppConfig> configs = mgr.getAppConfigsByType(configType);
					for (AppConfig config : configs) {
						properties.put(config.getKeyName(),
								config.getKeyValue());
					}
				}
				reader.setProperties(configType, properties);
			}
		}
	}
}
