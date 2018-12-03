package com.demo.springbootweblogic.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@RestController
@RequestMapping(value = "/uploadfile")
class UploadFileController {

    private static Logger logger = LogManager.getLogger(UploadFileController.class);

    @PostMapping("/upload")
    Map<String, Object> uploadFile(@RequestParam("file") MultipartFile file) {
        Map<String, Object> resp = new HashMap<>();
        try {
            resp.put("parameterName", file.getName());
            resp.put("originalFileName", file.getOriginalFilename());
            resp.put("fileSize", file.getSize());
            // Get the file and save it somewhere
            // byte[] bytes = file.getBytes();
            // Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            // Files.write(path, bytes);
        } catch (Exception e) {
            logger.error("", e);
        }
        resp.put("success", 1);
        return resp;
    }

    @GetMapping(value = "/helloworld")
    String test() {
        logger.info("hello world, UploadFileController");
        return "This is test in UploadFileController";
    }
}