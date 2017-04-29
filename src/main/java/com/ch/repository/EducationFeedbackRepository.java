package com.ch.repository;

import com.ch.domain.EducationFeedback;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Tom on 29/04/2017.
 */
@Repository
public interface EducationFeedbackRepository extends CrudRepository<EducationFeedback, Long> {
}
