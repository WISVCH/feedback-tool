package com.ch.service;

import com.ch.domain.feedback.AssociationFeedback;
import com.ch.repository.AssociationFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Tom on 29/04/2017.
 */
@Service
public class AssociationFeedbackService {

    private AssociationFeedbackRepository associationFeedbackRepository;

    @Autowired
    public AssociationFeedbackService(AssociationFeedbackRepository associationFeedbackRepository) {
        this.associationFeedbackRepository = associationFeedbackRepository;
    }

    public AssociationFeedback save(AssociationFeedback associationFeedback) {
        return associationFeedbackRepository.save(associationFeedback);
    }

    public List<AssociationFeedback> listInbox() {
        return associationFeedbackRepository.findAllByHandledIsFalseOrderByPostedOnDesc();
    }

    public List<AssociationFeedback> listArchive() {
        return associationFeedbackRepository.findAllByOrderByPostedOnDesc();
    }

    public AssociationFeedback get(long id) {
        return associationFeedbackRepository.findOne(id);
    }
}
