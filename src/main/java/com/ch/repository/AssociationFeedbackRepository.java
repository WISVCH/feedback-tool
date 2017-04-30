package com.ch.repository;

import com.ch.domain.AssociationFeedback;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Tom on 29/04/2017.
 */
@Repository
public interface AssociationFeedbackRepository extends CrudRepository<AssociationFeedback, Long> {
    List<AssociationFeedback> findAllByOrderByPostedOnDesc();

    List<AssociationFeedback> findAllByHandledIsFalseOrderByPostedOnDesc();
}
