package com.pankiba.springbootinternals.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApplicationUtils {

	private static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";
	public static final String DEVELOPMENT_PROFILE = "dev";

	private ApplicationUtils() {

	}

	public static void logApplicationStartup(Environment environment) {

		String protocol = "http";

		if (environment.getProperty("server.ssl.key-store") != null) {
			protocol = "https";
		}

		String serverPort = environment.getProperty("server.port");
		String contextPath = environment.getProperty("server.servlet.context-path");

		if (StringUtils.isBlank(contextPath)) {
			contextPath = "/";
		}

		String hostAddress = "localhost";

		try {
			hostAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException unknownHostException) {
			log.warn("The host name could not be determined, using 'localhost' as fallback");
		}

		log.info(
				"\n--------------------------------------------------------------------\n\t"
						+ "Application '{}' is running! Access URLs:\n\t" + "Local: \t\t{}://localhost:{}{}\n\t"
						+ "External: \t{}://{}:{}{}\n\t"
						+ "Profile(s): \t{}\n--------------------------------------------------------------------",
				environment.getProperty("spring.application.name"), protocol, serverPort, contextPath, protocol,
				hostAddress, serverPort, contextPath, ApplicationUtils.getActiveProfiles(environment));
	}

	/**
	 * Set default profile to use when no other profile is configured
	 * 
	 * @param springApplication
	 */
	public static void setDefautlProfile(SpringApplication springApplication) {
		Map<String, Object> defaultProperties = new HashMap<>();
		defaultProperties.put(SPRING_PROFILE_DEFAULT, DEVELOPMENT_PROFILE);
		springApplication.setDefaultProperties(defaultProperties);
	}

	/**
	 * Get the profiles that are applied else get default profiles.
	 *
	 * @param envionment spring environment
	 * @return profiles
	 */
	public static String[] getActiveProfiles(Environment envionment) {
		String[] profiles = envionment.getActiveProfiles();
		if (profiles.length == 0) {
			log.info("No active profile set, so using 'DEV' as fallback");
			return envionment.getDefaultProfiles();
		}
		return profiles;
	}

}
