package com.demo.springbootweblogic.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/uploadfile")
class UploadFileController {

    private static Logger logger = LogManager.getLogger(UploadFileController.class);

    @GetMapping(value = "/helloworld")
    String test() {
        logger.info("hello world, UploadFileController");
        return "This is test in UploadFileController";
    }
}