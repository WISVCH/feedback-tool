package ch.wisv.domain.feedback;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

/**
 * Created by Tom on 29/04/2017.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class AssociationFeedback extends Feedback {

    public AssociationFeedback() {
        super();
    }

    public AssociationFeedback(
            FeedbackType feedbackType,
            String subject,
            String body,
            String senderName,
            String senderMail,
            String captchaResponse
    ) {
        super(feedbackType, subject, body, senderName, senderMail, captchaResponse);
    }
}
