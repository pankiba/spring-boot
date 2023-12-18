package com.pankiba.springbootinternals;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.pankiba.springbootinternals.util.ApplicationUtils;

/**
 * This is a helper Java class that provides an alternative to creating a
 * {@code web.xml}. This will be invoked only when the application is deployed
 * to a Servlet container like Tomcat, JBoss etc.
 */
public class ApplicationWebXml extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder) {

		/*
		 * setting default profile as DEV if no other profile is configured. This needs
		 * to be done before calling run method on SpringApplication
		 */
		ApplicationUtils.setDefautlProfile(springApplicationBuilder.application());
		return springApplicationBuilder.sources(SpringbootInternalsApplication.class);
	}
}