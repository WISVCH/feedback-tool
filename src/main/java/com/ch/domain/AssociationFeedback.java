package com.ch.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Tom on 29/04/2017.
 */
@Entity
@Data
public class AssociationFeedback extends Feedback {

    @Id
    @GeneratedValue
    long id;
}
