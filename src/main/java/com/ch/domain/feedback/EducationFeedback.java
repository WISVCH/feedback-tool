package com.ch.domain.feedback;

import com.ch.domain.course.Course;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by Tom on 28/04/2017.
 */
@Entity
@Data
public class EducationFeedback extends Feedback{

    @NotNull
    @ManyToOne
    private Course course;

    public EducationFeedback() {
        super();
    }

    @Override
    public String toString() {
        return "EducationFeedback{" +
                "id=" + id +
                ", course=" + course +
                ", feedbackType=" + feedbackType +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", postedOn=" + postedOn +
                ", handled=" + handled +
                ", senderName='" + senderName + '\'' +
                ", senderMail='" + senderMail + '\'' +
                '}';
    }
}
