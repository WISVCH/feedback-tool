package com.ch.controller;

import com.ch.domain.AssociationFeedback;
import com.ch.service.AssociationFeedbackService;
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
 * Created by Tom on 30/04/2017.
 */
@Controller
@RequestMapping("/association")
public class AssociationFeedbackController {
    private AssociationFeedbackService associationFeedbackService;

    @Autowired
    public AssociationFeedbackController(AssociationFeedbackService associationFeedbackService) {
        this.associationFeedbackService = associationFeedbackService;
    }

    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("feedback", new AssociationFeedback());
        return "association/associationForm";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("feedback") AssociationFeedback associationFeedback,
                       BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "association/associationForm";
        } else {
            associationFeedbackService.save(associationFeedback);
            redirectAttributes.addFlashAttribute("message", "Thanks! Your feedback has been submitted." +
                    " If you filled in your email, you will find a copy of your feedback in your mail.");
            return "redirect:/association/create";
        }
    }
}
