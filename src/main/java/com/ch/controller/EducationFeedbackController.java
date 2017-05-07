package com.ch.controller;

import com.ch.domain.EducationFeedback;
import com.ch.service.CourseService;
import com.ch.service.EducationFeedbackService;
import com.ch.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by Tom on 29/04/2017.
 */
@Controller
@RequestMapping("/education")
public class EducationFeedbackController {
    private EducationFeedbackService educationFeedbackService;
    private CourseService courseService;
    private NotificationService notificationService;

    @Autowired
    public EducationFeedbackController(EducationFeedbackService educationFeedbackService, CourseService courseService, NotificationService notificationService) {
        this.educationFeedbackService = educationFeedbackService;
        this.courseService = courseService;
        this.notificationService = notificationService;
    }

    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("feedback", new EducationFeedback());
        model.addAttribute("courses", courseService.list());
        return "education/educationForm";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("feedback") EducationFeedback educationFeedback,
                       BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("courses", courseService.list());
            return "education/educationForm";
        } else {
            educationFeedbackService.save(educationFeedback);
            notificationService.sendNotifications(educationFeedback);
            redirectAttributes.addFlashAttribute("message", "Thanks! Your feedback has been submitted." +
                    " If you filled in your email, you will find a copy of your feedback in your mail.");
            return "redirect:/education/create";
        }
    }
}
