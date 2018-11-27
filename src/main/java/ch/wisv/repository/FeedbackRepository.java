package ch.wisv.repository;

import ch.wisv.domain.feedback.Feedback;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * FeedbackRepository.
 */
@Repository
public interface FeedbackRepository extends CrudRepository<Feedback, Long> {

    List<Feedback> findAllByHandledIsFalseOrderByPostedOnDesc();

    List<Feedback> findAllByHandledIsTrueOrderByPostedOnDesc();

    Optional<Feedback> findByKey(String key);

}
