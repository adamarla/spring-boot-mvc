package com.adamarla.spring.controller;

import com.adamarla.spring.repo.DemoRepo;

import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by adamarla on 8/14/17.
 */

@Controller
public class UserController {

    DemoRepo repository;

    @RequestMapping("/")
    String home(Model model) {
        model.addAttribute("message", "Chica boom!");
        return "hello_template";
    }

}
