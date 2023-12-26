package com.pankiba.springcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.env.Environment;

import com.pankiba.springcache.util.ApplicationUtils;

@SpringBootApplication
@EnableCaching
public class SpringCacheShowcaseApplication {

	public static void main(String[] args) {
		
		SpringApplication springApplication = new SpringApplication(SpringCacheShowcaseApplication.class);

		/*
		 * setting default profile as DEV if no other profile is configured. This needs
		 * to be done before calling run method on SpringApplication
		 */
		ApplicationUtils.setDefautlProfile(springApplication);
		Environment environment = springApplication.run(args).getEnvironment();
		ApplicationUtils.logApplicationStartup(environment);
	}

}
