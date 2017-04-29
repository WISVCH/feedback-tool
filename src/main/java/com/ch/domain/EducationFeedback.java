package com.ch.domain;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by Tom on 28/04/2017.
 */
@Entity
@Data
public class EducationFeedback extends Feedback{
    @Id
    @GeneratedValue
    long id;

    @NotNull
    @ManyToOne
    private Course course;

    public EducationFeedback() {
        super();
    }

}
