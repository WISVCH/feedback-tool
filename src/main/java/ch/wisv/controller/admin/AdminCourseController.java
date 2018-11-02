package ch.wisv.controller.admin;

import ch.wisv.domain.course.courseloader.CourseLoader;
import ch.wisv.domain.course.courseloader.CoursesString;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller for courses.
 *
 * Created by Tom on 30/04/2017.
 */
@Controller
@RequestMapping("/admin")
public class AdminCourseController {

    /** Loader for courses. */
    private CourseLoader courseLoader;

    /**
     * Autowired constructor.
     */
    @Autowired
    public AdminCourseController(CourseLoader courseLoader) {
        this.courseLoader = courseLoader;
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
