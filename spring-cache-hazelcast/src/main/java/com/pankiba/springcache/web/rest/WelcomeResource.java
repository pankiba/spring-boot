package com.pankiba.springcache.web.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeResource {

	@GetMapping(path = "/")
	public String helloWorld() {
		return "Welcome to Spring Cache, world of new possibilities !!";
	}

}
