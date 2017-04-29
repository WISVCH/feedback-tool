package com.ch.controller.admin;

import com.ch.service.EducationFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Tom on 29/04/2017.
 */
@RestController
@RequestMapping("/admin/education")
public class AdminEducationFeedbackController {
    private EducationFeedbackService educationFeedbackService;

    @Autowired
    public AdminEducationFeedbackController(EducationFeedbackService educationFeedbackService) {
        this.educationFeedbackService = educationFeedbackService;
    }

    @RequestMapping("/list")
    public String list() {
        return educationFeedbackService.list().toString();
    }

    @RequestMapping("/{id}")
    public String getEducationFeedback(@PathVariable long id, Model model) {
        return educationFeedbackService.get(id).toString();
    }
}
