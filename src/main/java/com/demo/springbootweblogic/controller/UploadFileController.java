package com.demo.springbootweblogic.controller;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/uploadfile")
class UploadFileController {

    private static Logger logger = LogManager.getLogger(UploadFileController.class);

    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    Map<String, Object> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam(name = "fileName") String filenameEnc) {
        
        String filenameDec = new String(filenameEnc.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        Map<String, Object> resp = new HashMap<>();
        try {
            resp.put("parameterName", file.getName());
            resp.put("originalFileName", file.getOriginalFilename());
            resp.put("fileSize", file.getSize());
            resp.put("filename_param_original", filenameEnc);
            resp.put("filename_param_decode", filenameDec);
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