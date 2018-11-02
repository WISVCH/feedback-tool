package ch.wisv.service;

import ch.wisv.domain.feedback.Feedback;
import ch.wisv.repository.FeedbackRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public List<Feedback> listInbox() {
        return feedbackRepository.findAllByHandledIsFalseOrderByPostedOnDesc();
    }

    public List<Feedback> listArchive() {
        return feedbackRepository.findAllByHandledIsTrueOrderByPostedOnDesc();
    }

    public Feedback getByKey(String key) {
        return feedbackRepository.findByKey(key).orElseThrow(RuntimeException::new);
    }

    public void save(@Valid Feedback feedback) {
        feedbackRepository.save(feedback);
    }
}
