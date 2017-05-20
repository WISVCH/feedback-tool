package com.ch.domain.course.courseloader;

import com.ch.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Tom on 20/05/2017.
 */
@Component
public class CourseLoader {

    private CourseBuilder courseBuilder;
    private CourseService courseService;

    @Autowired
    public CourseLoader(CourseBuilder courseBuilder, CourseService courseService) {
        this.courseBuilder = courseBuilder;
        this.courseService = courseService;
    }

    public void load(String courses) {
        List<String> courseList = Arrays.asList(courses.split("\\s*,\\s*"));
        for (String course : courseList) {
            this.courseBuilder.build(course);
        }
    }

    public void delete(String courses) {
        List<String> courseList = Arrays.asList(courses.split("\\s*,\\s*"));
        for (String course : courseList) {
            this.courseService.delete(course);
        }
    }
}
