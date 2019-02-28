package ch.wisv.controller;

import ch.wisv.domain.feedback.AssociationFeedback;
import ch.wisv.service.AssociationFeedbackService;
import ch.wisv.service.CaptchaService;
import ch.wisv.service.NotificationService;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller for association feedback.
 *
 * Created by Tom on 30/04/2017.
 */
@Controller
@RequestMapping("/association")
public class AssociationFeedbackController {
    /** JPA service for association feedback. */
    private AssociationFeedbackService associationFeedbackService;
    /** Service for mail notifications. */
    private NotificationService notificationService;

    /**
     * Autowired constructor.
     */
    @Autowired
    public AssociationFeedbackController(AssociationFeedbackService associationFeedbackService, NotificationService notificationService) {
        this.associationFeedbackService = associationFeedbackService;
        this.notificationService = notificationService;
    }

    /**
     * Create new association feedback.
     */
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("feedback", new AssociationFeedback());

        return "association/associationForm";
    }

    /**
     * Save new association feedback.
     */
    @Transactional
    @PostMapping(value = "/create")
    public String save(
            @Valid @ModelAttribute("feedback") AssociationFeedback associationFeedback,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("feedback", associationFeedback);

            return "association/associationForm";
        } else {
            CaptchaService captchaService = new CaptchaService();
            if (!captchaService.validateCaptcha(associationFeedback.getCaptchaResponse())) {
                // TODO Add error message.
                return "association/associationForm";
            }

            associationFeedbackService.save(associationFeedback);
            notificationService.sendNotifications(associationFeedback);
            redirectAttributes.addFlashAttribute("message", "Thanks! Your feedback has been submitted." +
                    " If you filled in your email, you will find a copy of your feedback in your mail.");

            return "redirect:/association/create";
        }
    }
}
