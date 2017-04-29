package com.ch.controller;

import com.ch.domain.EducationFeedback;
import com.ch.service.CourseService;
import com.ch.service.EducationFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Tom on 29/04/2017.
 */
@Controller
@RequestMapping("/education")
public class EducationFeedbackController {
    private EducationFeedbackService educationFeedbackService;
    private CourseService courseService;

    @Autowired
    public EducationFeedbackController(EducationFeedbackService educationFeedbackService, CourseService courseService) {
        this.educationFeedbackService = educationFeedbackService;
        this.courseService = courseService;
    }

    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("feedback", new EducationFeedback());
        model.addAttribute("courses", courseService.list());
        return "education/educationForm";
    }
}
