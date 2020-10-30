
package com.demo.springbootweblogic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
*	Example Reference
*	https://dzone.com/articles/using-the-spring-requestmapping-annotation
*/

@RestController
@RequestMapping(value = "/hello")
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

	@RequestMapping(value = "/anotherhelloworld", method = RequestMethod.GET)
	ArrayList<String> anotherReadResource() {
		logger.info("anotherReadResource");
		ArrayList<String> examples = new ArrayList<>();
		examples.add("This");
		examples.add("is");
		examples.add("anotherReadResource");
		return examples;
	}

	@RequestMapping(value = "/multirequestmethods", method = { RequestMethod.GET, RequestMethod.POST })
	ArrayList<String> multiRequestMethods() {

		logger.info("multiRequestMethods");

		ArrayList<String> examples = new ArrayList<>();
		examples.add("This");
		examples.add("is");
		examples.add("multi");
		examples.add("request");
		examples.add("methods");
		return examples;
	}

	/**
	 * If Request content type is not application/json or application/xml, request
	 * will be rejected
	 * 
	 */
	@RequestMapping(value = "/cons", consumes = { "application/JSON", "application/XML" })
	String getConsumes() {
		return "Consumes attribute";
	}

	@RequestMapping(value = "/fetch", params = { "personId=10" })
	String getParams(@RequestParam("personId") String id) {
		logger.info("getParams, id=>" + id);
		return "Work onlt personID=10, Fetched parameter using params attribute = " + id;
	}

	@RequestMapping(value = "/fetch/{id}", method = RequestMethod.GET)
	String getDynamicUriValue(@PathVariable String id) {
		logger.info("ID is " + id);
		return "Dynamic URI parameter fetched";
	}

	@RequestMapping(value = "/fetch/{id:[a-z]+}/{name}", method = RequestMethod.GET)
	String getDynamicUriValueRegex(@PathVariable("name") String name) {
		logger.info("Name is " + name);
		return "Dynamic URI parameter fetched using regex";
	}

	@RequestMapping(value = "/getById")
	String getIdByValue(@RequestParam("id") String personId) {
		logger.info("ID is " + personId);
		return "Get ID from query string of URL with value element";
	}

	@RequestMapping(value = "/getByPersonId")
	String getId(@RequestParam String personId) {
		logger.info("ID is " + personId);
		return "Get ID from query string of URL without value element";
	}

	@PostMapping("/manualservletrequest")
	Map<String, Object> uploadFile(HttpServletRequest request) {
		Map<String, Object> resp = new HashMap<>();
		try {
			resp.put("myvalue", request.getParameter("myValue"));
		} catch (Exception e) {
			logger.error("", e);
		}
		resp.put("success", 1);
		return resp;
	}

	@GetMapping("/testspringsecurity")
	Map<String, Object> testSpringSecurity(HttpServletRequest request) {
		Map<String, Object> resp = new HashMap<>();
		try {
			resp.put("pass", 1);
		} catch (Exception e) {
			logger.error("", e);
		}
		resp.put("success", 1);
		return resp;
	}
}