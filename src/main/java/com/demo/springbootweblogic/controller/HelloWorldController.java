
package com.demo.springbootweblogic.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping("/helloworld")
	ArrayList<String> readResource() {

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