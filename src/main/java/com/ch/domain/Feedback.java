package com.ch.domain;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Tom on 29/04/2017.
 */
@Data
abstract class Feedback {
    @Id @GeneratedValue
    long id;

    @NotNull
    FeedbackType feedbackType;

    @NotEmpty
    String subject;

    @NotEmpty
    @Column(columnDefinition = "TEXT")
    String body;

    @NotEmpty
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat( pattern="M/dd/yyyy")
    Date postedOn;

    boolean handled;

    String senderName;

    String senderMail;

    Feedback() {
        this.postedOn = new Date();
        this.handled = false;
    }
}
