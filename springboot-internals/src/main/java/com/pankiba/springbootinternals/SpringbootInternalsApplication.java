package com.pankiba.springbootinternals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import com.pankiba.springbootinternals.util.ApplicationUtils;

@SpringBootApplication
public class SpringbootInternalsApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(SpringbootInternalsApplication.class);

		/*
		 * setting default profile as DEV if no other profile is configured. This needs
		 * to be done before calling run method on SpringApplication
		 */
		ApplicationUtils.setDefautlProfile(springApplication);

		/*
		 * Need to add/register custom application context initializers.
		 */
		springApplication.addInitializers(new MyApplicationContextInitializer());

		ConfigurableApplicationContext configurableApplicationContext = springApplication.run(args);

		Environment environment = configurableApplicationContext.getEnvironment();

		ApplicationUtils.logApplicationStartup(environment);
	}

}
