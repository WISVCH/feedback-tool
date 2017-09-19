package ch.wisv.repository;

import ch.wisv.domain.course.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Tom on 29/04/2017.
 */
@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    List<Course> findAll();

    Course findAllByCourseCodeIs(String courseCode);
}
