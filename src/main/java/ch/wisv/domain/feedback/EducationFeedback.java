package ch.wisv.domain.feedback;

import ch.wisv.domain.course.Course;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * EducationFeedback.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class EducationFeedback extends Feedback {

    @ManyToOne
    private Course course;

    @NotNull
    private String courseCode;

    public EducationFeedback() {
        super();
    }

    public EducationFeedback(
            Course course,
            FeedbackType feedbackType,
            String subject,
            String body,
            String senderName,
            String senderMail,
            String captchaResponse
    ) {
        super(feedbackType, subject, body, senderName, senderMail, captchaResponse);
        this.course = course;
        this.courseCode = course.getCourseCode();
    }
}
