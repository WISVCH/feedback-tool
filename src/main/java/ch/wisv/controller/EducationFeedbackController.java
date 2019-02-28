package ch.wisv.controller;

import ch.wisv.domain.course.Course;
import ch.wisv.domain.feedback.EducationFeedback;
import ch.wisv.service.CaptchaService;
import ch.wisv.service.CourseService;
import ch.wisv.service.EducationFeedbackService;
import ch.wisv.service.NotificationService;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller for education feedback.
 *
 * Created by Tom on 29/04/2017.
 */
@Controller
@RequestMapping("/education")
public class EducationFeedbackController {
    /** JPA service for education feedback. */
    private EducationFeedbackService educationFeedbackService;
    /** JPA service for courses. */
    private CourseService courseService;
    /** Service for mail notifications. */
    private NotificationService notificationService;
    /** Service to handle captcha validation */
    private final CaptchaService captchaService;

    /**
     * Autowired constructor.
     */
    @Autowired
    public EducationFeedbackController(EducationFeedbackService educationFeedbackService, CourseService courseService, NotificationService notificationService, CaptchaService captchaService) {
        this.educationFeedbackService = educationFeedbackService;
        this.courseService = courseService;
        this.notificationService = notificationService;
        this.captchaService = captchaService;
    }

    /**
     * Create new education feedback.
     */
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("feedback", new EducationFeedback());
        model.addAttribute("courses", courseService.list());

        return "education/educationForm";
    }

    /**
     * Save new education feedback.
     */
    @Transactional
    @PostMapping(value = "/create")
    public String save(
            @Valid @ModelAttribute("feedback") EducationFeedback educationFeedback,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model,
            @RequestParam(value="g-recaptcha-response") String clientResponse
    ) {
        Course course = courseService.get(educationFeedback.getCourseCode().toUpperCase());
        if (course == null) {
            model.addAttribute("courseCodeError", "");
            model.addAttribute("courses", courseService.list());
            model.addAttribute("feedback", educationFeedback);

            return "education/educationForm";
        }
        if(bindingResult.hasErrors()) {
            model.addAttribute("courses", courseService.list());
            model.addAttribute("feedback", educationFeedback);

            return "education/educationForm";
        }

        if (!captchaService.validateCaptcha(clientResponse)) {
            model.addAttribute("feedback", educationFeedback);
            model.addAttribute("captchaError", true);
            return "association/associationForm";
        }

        educationFeedback.setCourse(course);
        educationFeedbackService.save(educationFeedback);
        notificationService.sendNotifications(educationFeedback);
        redirectAttributes.addFlashAttribute("message", "Thanks! Your feedback has been submitted." +
                " If you filled in your email, you will find a copy of your feedback in your mail.");

        return "redirect:/education/create";
    }
}
