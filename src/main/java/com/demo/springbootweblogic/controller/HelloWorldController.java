
package com.demo.springbootweblogic.controller;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	private static Logger logger = LogManager.getLogger(HelloWorldController.class);

	@GetMapping("/helloworld")
	ArrayList<String> readResource() {

		logger.info("hello world log4j");

		ArrayList<String> examples = new ArrayList<>();
		examples.add("Hello");
		examples.add("World");
		examples.add("This");
		examples.add("is");
		examples.add("demo");
		examples.add("project");
		return examples;
	}
}