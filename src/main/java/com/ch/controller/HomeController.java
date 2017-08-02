package com.ch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for the home page.
 *
 * Created by Tom on 29/04/2017.
 */
@Controller
public class HomeController {

    /**
     * Home page.
     */
    @RequestMapping("/")
    public String home() {
        return "index";
    }
}
