package com.ch.controller.admin;

import com.ch.domain.Course;
import com.ch.domain.EducationFeedback;
import com.ch.service.CourseService;
import com.ch.service.EducationFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Tom on 30/04/2017.
 */
@Controller
@RequestMapping("/admin/course")
public class AdminCourseController {
    private CourseService courseService;
    private EducationFeedbackService educationFeedbackService;

    @Autowired
    public AdminCourseController(CourseService courseService, EducationFeedbackService educationFeedbackService) {
        this.courseService = courseService;
        this.educationFeedbackService = educationFeedbackService;
    }

    @RequestMapping("/{courseCode}")
    public String getCourse(@PathVariable String courseCode, Model model) {
        courseCode = courseCode.toUpperCase();
        Course course = courseService.get(courseCode);
        List<EducationFeedback> feedbackOnCourse = educationFeedbackService.getCourseFeedback(course.getId());
        model.addAttribute("course", course);
        model.addAttribute("feedbackOnCourse", feedbackOnCourse);

        return "admin/course/view";
    }
}
