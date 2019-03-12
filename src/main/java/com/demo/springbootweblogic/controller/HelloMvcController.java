package com.demo.springbootweblogic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/mvc")
class HelloMvcController {

    @ExceptionHandler(Exception.class)
    public final void handleAllExceptions(Exception ex, WebRequest request) {
        ex.printStackTrace();
    }

    @GetMapping(value = "/hello1")
    private ModelAndView helloworldView() {
        ModelAndView view = new ModelAndView("/hello.jsp");
        return view;
    }

    @GetMapping(value = "/hello2")
    private String helloworld(Model model,
            @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        model.addAttribute("name", name);
        return "/hello.jsp";
    }
}