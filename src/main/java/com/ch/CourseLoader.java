package com.ch;

import com.ch.domain.Course;
import com.ch.domain.Program;
import com.ch.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Created by Tom on 29/04/2017.
 */
public class CourseLoader {
    private CourseService courseService;

    @Autowired
    public CourseLoader(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostConstruct
    private void loadCourses() {
//        Course course1 = new Course("TI3306", "Complexiteitstheorie", "Cees Witteveen", "C.Witteveen@tudelft.nl", Program.BScTI);
//        Course course2 = new Course("TI1206", "OOP", "Andy Zaidman", "A.Zaidman@tudelft.nl", Program.BScTI);
//        courseService.save(course1);
//        courseService.save(course2);
    }
}
