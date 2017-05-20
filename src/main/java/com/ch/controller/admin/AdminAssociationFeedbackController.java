package com.ch.controller.admin;

import com.ch.domain.feedback.AssociationFeedback;
import com.ch.service.AssociationFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Tom on 30/04/2017.
 */
@Controller
@RequestMapping("/admin/association")
public class AdminAssociationFeedbackController {
    private AssociationFeedbackService associationFeedbackService;

    @Autowired
    public AdminAssociationFeedbackController(AssociationFeedbackService associationFeedbackService) {
        this.associationFeedbackService = associationFeedbackService;
    }

    @RequestMapping("/inbox")
    public String listInbox(Model model) {
        model.addAttribute("posts", associationFeedbackService.listInbox());
        return "admin/association/listInbox";
    }

    @RequestMapping("/archive")
    public String listArchive(Model model) {
        model.addAttribute("posts", associationFeedbackService.listArchive());
        return "admin/association/listArchive";
    }

    @RequestMapping("/{id}")
    public String getAssociationFeedback(@PathVariable long id, Model model) {
        model.addAttribute("feedback", associationFeedbackService.get(id));
        return "admin/association/view";
    }

    @RequestMapping("handle/{id}")
    public String handle(@PathVariable long id, Model model, HttpServletRequest request) {
        AssociationFeedback associationFeedback = associationFeedbackService.get(id);
        boolean handle = !associationFeedback.isHandled();
        associationFeedback.setHandled(handle);
        associationFeedbackService.save(associationFeedback);

        model.addAttribute("posts", associationFeedbackService.listInbox());
        return "redirect:" + request.getHeader("Referer");
    }
}
