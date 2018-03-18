package com.rexam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rexam.security.service.SecurityService;
import com.rexam.security.service.UserService;
import com.rexam.security.validator.UserValidator;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
    

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/student-home"}, method = RequestMethod.GET)
    public String student_home(Model model) {
    	
        return "student-home";
    }

    @RequestMapping(value = {"/admin-home"}, method = RequestMethod.GET)
    public String admin_home(Model model) {
    	
        return "admin-home";
    }
}
