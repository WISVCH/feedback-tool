package com.ch.service;

import com.ch.repository.EducationFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
