package com.ch.service;

import com.ch.repository.AssociationFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
