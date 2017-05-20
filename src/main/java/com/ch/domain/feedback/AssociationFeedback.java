package com.ch.domain.feedback;

import lombok.Data;

import javax.persistence.Entity;

/**
 * Created by Tom on 29/04/2017.
 */
@Entity
@Data
public class AssociationFeedback extends Feedback {

    public AssociationFeedback() {
        super();
    }
}
