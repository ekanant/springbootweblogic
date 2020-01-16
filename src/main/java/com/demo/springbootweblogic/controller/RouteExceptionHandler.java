package com.demo.springbootweblogic.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RouteExceptionHandler extends ResponseEntityExceptionHandler {

    private static Logger logger = LogManager.getLogger(RouteExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        HttpServletRequest servletRequest = ((ServletWebRequest)request).getRequest();
        String requestUri = servletRequest.getRequestURI().toString();

        logger.error("request_uri=>"+requestUri, ex);

        JsonObject json = new JsonObject();
        json.addProperty("request_uri", requestUri);
        json.addProperty("timestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(new Date()));
        json.addProperty("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(json.toString());
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
                HttpServletRequest servletRequest = ((ServletWebRequest)request).getRequest();
                String requestUri = servletRequest.getRequestURI().toString();

                logger.error("request_uri=>"+requestUri, ex);

                JsonObject json = new JsonObject();
                json.addProperty("request_uri", requestUri);
                json.addProperty("timestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(new Date()));
                json.addProperty("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON_UTF8).body(json.toString());
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        HttpServletRequest servletRequest = ((ServletWebRequest)request).getRequest();
        String requestUri = servletRequest.getRequestURI().toString();

        logger.error("request_uri=>"+requestUri, ex);

        JsonObject json = new JsonObject();
        json.addProperty("request_uri", requestUri);
        json.addProperty("timestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(new Date()));
        json.addProperty("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(json.toString());
    }
}