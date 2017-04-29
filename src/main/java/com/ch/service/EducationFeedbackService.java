package com.ch.service;

import com.ch.domain.EducationFeedback;
import com.ch.repository.EducationFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Tom on 29/04/2017.
 */
@Service
public class EducationFeedbackService {

    private EducationFeedbackRepository educationFeedbackRepository;

    @Autowired
    public EducationFeedbackService(EducationFeedbackRepository educationFeedbackRepository) {
        this.educationFeedbackRepository = educationFeedbackRepository;
    }

    public EducationFeedback save(EducationFeedback educationFeedback) {
        return educationFeedbackRepository.save(educationFeedback);
    }

    public List<EducationFeedback> list() {
        return educationFeedbackRepository.findAllByOrderByPostedOnDesc();
    }

    public EducationFeedback get(long id) {
        return educationFeedbackRepository.findOne(id);
    }

    public List<EducationFeedback> listInbox() {
        return educationFeedbackRepository.findAllByHandledIsFalseOrderByPostedOnDesc();
    }
}
