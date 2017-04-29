package com.ch.repository;

import com.ch.domain.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Tom on 29/04/2017.
 */
@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
}
