package com.ch.service;

import com.ch.domain.course.Course;
import com.ch.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Tom on 29/04/2017.
 */
@Service
public class CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> list() {
        return courseRepository.findAll();
    }

    public Course get(long id) {
        return courseRepository.findOne(id);
    }

    public Course get(String courseCode) {
        return courseRepository.findAllByCourseCodeIs(courseCode);
    }

    public void delete(String courseCode) {
        Course course = this.get(courseCode);
        courseRepository.delete(course);
    }

    public boolean exists(String courseCode) {
        return this.get(courseCode) != null;
    }
}
