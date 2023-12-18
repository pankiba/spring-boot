package com.pankiba.restfulwebservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.pankiba.restfulwebservices.util.ApplicationUtils;

/**
 * 
 * @author pankiba
 *
 */
@SpringBootApplication
public class RestfulWebServicesApplication {

	/**
	 * Main method, entry point for SpringBoot Application
	 * 
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {

		SpringApplication springApplication = new SpringApplication(RestfulWebServicesApplication.class);

		/*
		 * setting default profile as DEV if no other profile is configured. This needs
		 * to be done before calling run method on SpringApplication
		 */
		ApplicationUtils.setDefautlProfile(springApplication);
		Environment environment = springApplication.run(args).getEnvironment();
		ApplicationUtils.logApplicationStartup(environment);

	}
}
