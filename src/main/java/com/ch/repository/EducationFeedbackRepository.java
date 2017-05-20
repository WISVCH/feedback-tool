package com.ch.repository;

import com.ch.domain.feedback.EducationFeedback;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Tom on 29/04/2017.
 */
@Repository
public interface EducationFeedbackRepository extends CrudRepository<EducationFeedback, Long> {
    List<EducationFeedback> findAllByOrderByPostedOnDesc();

    List<EducationFeedback> findAllByHandledIsFalseOrderByPostedOnDesc();

    List<EducationFeedback> findAllByCourseIdOrderByPostedOnDesc(long id);
}
