package ch.wisv.controller.admin;

import ch.wisv.domain.course.Course;
import ch.wisv.domain.feedback.EducationFeedback;
import ch.wisv.domain.feedback.Feedback;
import ch.wisv.service.CourseService;
import ch.wisv.service.EducationFeedbackService;
import ch.wisv.service.FeedbackService;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * AdminFeedbackController.
 */
@Controller
@RequestMapping("/admin")
public class AdminFeedbackController {

    /**
     * Autowired services.
     */
    private final CourseService courseService;
    private final EducationFeedbackService educationFeedbackService;
    private final FeedbackService feedbackService;

    @Autowired
    public AdminFeedbackController(CourseService courseService, EducationFeedbackService educationFeedbackService, FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
        this.courseService = courseService;
        this.educationFeedbackService = educationFeedbackService;
    }

    @GetMapping
    public String index() {
        return "redirect:/admin/inbox";
    }

    @GetMapping("/inbox")
    public String inbox(Model model) {
        model.addAttribute("posts", feedbackService.listInbox());

        return "admin/feedback/listInbox";
    }

    @GetMapping("/course/{courseCode}")
    public String course(@PathVariable String courseCode, Model model) {
        courseCode = courseCode.toUpperCase();
        Course course = courseService.get(courseCode);
        List<EducationFeedback> feedbackOnCourse = educationFeedbackService.getCourseFeedback(courseCode);

        model.addAttribute("course", course);
        model.addAttribute("feedbackOnCourse", feedbackOnCourse);

        return "admin/feedback/listCourse";
    }

    @GetMapping("/archive")
    public String archive(Model model) {
        model.addAttribute("posts", feedbackService.listArchive());

        return "admin/feedback/listArchive";
    }

    @GetMapping("/view/{key}")
    public String view(Model model, HttpServletRequest request, @PathVariable String key) {
        model.addAttribute("feedback", feedbackService.getByKey(key));
        model.addAttribute("redirect", request.getHeader("Referer"));

        return "admin/feedback/view";
    }

    @GetMapping("/handle/{key}")
    public String handle(HttpServletRequest request, @PathVariable String key) {
        Feedback feedback = feedbackService.getByKey(key);
        boolean handled = !feedback.isHandled();

        if (handled) {
            feedback.setHandledOn(new Date());
        }
        feedback.setHandled(handled);
        feedbackService.save(feedback);

        return "redirect:" + request.getHeader("Referer");
    }
}
