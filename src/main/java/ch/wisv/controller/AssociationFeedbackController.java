package ch.wisv.controller;

import ch.wisv.domain.feedback.AssociationFeedback;
import ch.wisv.domain.feedback.EducationFeedback;
import ch.wisv.service.AssociationFeedbackService;
import ch.wisv.service.CaptchaService;
import ch.wisv.service.NotificationService;
import ch.wisv.util.BindingResultBuilder;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
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
    /** Service to handle captcha validation */
    private final CaptchaService captchaService;

    /**
     * Autowired constructor.
     */
    @Autowired
    public AssociationFeedbackController(AssociationFeedbackService associationFeedbackService, NotificationService notificationService, CaptchaService captchaService) {
        this.associationFeedbackService = associationFeedbackService;
        this.notificationService = notificationService;
        this.captchaService = captchaService;
    }

    /**
     * Create new association feedback.
     */
    @GetMapping("/create")
    public String create(Model model) {
        if (!model.containsAttribute("feedback")) {
            model.addAttribute("feedback", new EducationFeedback());
        }

        if (!model.containsAttribute("errors")) {
            model.addAttribute("errors", new HashMap<String, String>());
        }

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
            @RequestParam(value="g-recaptcha-response") String clientResponse
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", BindingResultBuilder.createErrorMap(bindingResult));
        } else if (!captchaService.validateCaptcha(clientResponse)) {
            redirectAttributes.addFlashAttribute("captchaError", true);
        } else {
            associationFeedbackService.save(associationFeedback);
            notificationService.sendNotifications(associationFeedback);
            redirectAttributes.addFlashAttribute("message", "Thanks! Your feedback has been submitted." +
                    " If you filled in your email, you will find a copy of your feedback in your mail.");

            associationFeedback = new AssociationFeedback();
        }

        redirectAttributes.addFlashAttribute("feedback", associationFeedback);

        return "redirect:/association/create";
    }
}
