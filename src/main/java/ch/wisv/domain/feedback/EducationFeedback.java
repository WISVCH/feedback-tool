package ch.wisv.domain.feedback;

import ch.wisv.domain.course.Course;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * EducationFeedback.
 */
@Entity
@Data
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
            String senderMail
    ) {
        super(feedbackType, subject, body, senderName, senderMail);
        this.course = course;
        this.courseCode = course.getCourseCode();
    }
}
