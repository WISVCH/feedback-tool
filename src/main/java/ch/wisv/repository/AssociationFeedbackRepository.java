package ch.wisv.repository;

import ch.wisv.domain.feedback.AssociationFeedback;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Tom on 29/04/2017.
 */
@Repository
public interface AssociationFeedbackRepository extends CrudRepository<AssociationFeedback, Long> {
    List<AssociationFeedback> findAllByHandledIsTrueOrderByPostedOnDesc();

    List<AssociationFeedback> findAllByHandledIsFalseOrderByPostedOnDesc();
}
