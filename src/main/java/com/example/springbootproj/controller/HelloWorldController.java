package com.example.springbootproj.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HelloWorldController {

    @RequestMapping(method = RequestMethod.GET)
    public String helloWorld(ModelMap model, HttpServletRequest request) {
        String message = "Hello World, Spring 3.0!";
        System.out.println(message);
        model.addAttribute("message", message);
        return "hello";
    }
}
