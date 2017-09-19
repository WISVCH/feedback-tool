package com.ch.controller.admin;

import com.ch.domain.feedback.EducationFeedback;
import com.ch.service.EducationFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller for admin education feedback.
 *
 * Created by Tom on 29/04/2017.
 */
@Controller
@RequestMapping("/admin/education")
public class AdminEducationFeedbackController {
    /** JPA service for education feedback. */
    private EducationFeedbackService educationFeedbackService;

    /**
     * Autowired constructor.
     */
    @Autowired
    public AdminEducationFeedbackController(EducationFeedbackService educationFeedbackService) {
        this.educationFeedbackService = educationFeedbackService;
    }

    /**
     * List inbox education feedback items.
     */
    @RequestMapping("/inbox")
    public String listInbox(Model model) {
        model.addAttribute("posts", educationFeedbackService.listInbox());
        return "admin/education/listInbox";
    }

    /**
     * List archive education feedback items.
     */
    @RequestMapping("/archive")
    public String list(Model model) {
        model.addAttribute("posts", educationFeedbackService.list());
        return "admin/education/listArchive";
    }

    /**
     * View single education feedback item by it's ID.
     */
    @RequestMapping("/{id}")
    public String getEducationFeedback(@PathVariable long id, Model model) {
        model.addAttribute("feedback", educationFeedbackService.get(id));
        return "admin/education/view";
    }

    /**
     * Change handled state of an education feedback item by it's ID.
     */
    @RequestMapping("/handle/{id}")
    public String handle(@PathVariable long id, Model model, HttpServletRequest request) {
        EducationFeedback educationFeedback = educationFeedbackService.get(id);
        boolean handle = !educationFeedback.isHandled();
        educationFeedback.setHandled(handle);
        educationFeedbackService.save(educationFeedback);

        model.addAttribute("posts", educationFeedbackService.listInbox());
        return "redirect:" + request.getHeader("Referer");
    }
}
