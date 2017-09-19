package com.ch.controller.admin;

import com.ch.domain.course.Course;
import com.ch.domain.course.courseloader.CourseLoader;
import com.ch.domain.course.courseloader.CoursesString;
import com.ch.domain.feedback.EducationFeedback;
import com.ch.service.CourseService;
import com.ch.service.EducationFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller for courses.
 *
 * Created by Tom on 30/04/2017.
 */
@Controller
@RequestMapping("/admin/course")
public class AdminCourseController {
    /** JPA service for courses. */
    private CourseService courseService;
    /** JPA service for education feedback. */
    private EducationFeedbackService educationFeedbackService;
    /** Loader for courses. */
    private CourseLoader courseLoader;

    /**
     * Autowired constructor.
     */
    @Autowired
    public AdminCourseController(CourseService courseService, EducationFeedbackService educationFeedbackService, CourseLoader courseLoader) {
        this.courseService = courseService;
        this.educationFeedbackService = educationFeedbackService;
        this.courseLoader = courseLoader;
    }

    /**
     * View single course item by it's course code.
     */
    @RequestMapping("/{courseCode}")
    public String getCourse(@PathVariable String courseCode, Model model) {
        courseCode = courseCode.toUpperCase();
        Course course = courseService.get(courseCode);
        List<EducationFeedback> feedbackOnCourse = educationFeedbackService.getCourseFeedback(course.getCourseCode());
        model.addAttribute("course", course);
        model.addAttribute("feedbackOnCourse", feedbackOnCourse);
        model.addAttribute("link", "admin/education/");

        return "admin/course/view";
    }

    /**
     * Settings for courses.
     */
    @RequestMapping("/settings")
    public String settings(Model model) {
        model.addAttribute("courses", new CoursesString());
        return "admin/course/settings";
    }

    /**
     * Load courses into the system.
     */
    @RequestMapping("/settings/load")
    public String load(@Valid @ModelAttribute("courses") CoursesString courses, RedirectAttributes redirectAttributes) {
        try {
            courseLoader.load(courses.getCourseLoadList());
            redirectAttributes.addFlashAttribute("successMessage", "The course(s) are uploaded to the system!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Something went wrong uploading the course(s), please check if your input is valid. If you entered a list of codes, check the database for the last code that did not fail to see where the error occurred. Courses from other faculties or service courses can't be added.");
        }
        return "redirect:/admin/course/settings";
    }
}
