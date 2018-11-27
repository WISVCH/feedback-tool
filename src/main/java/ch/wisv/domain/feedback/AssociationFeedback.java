package ch.wisv.domain.feedback;

import javax.persistence.Entity;
import lombok.Data;

/**
 * Created by Tom on 29/04/2017.
 */
@Entity
@Data
public class AssociationFeedback extends Feedback {

    public AssociationFeedback() {
        super();
    }

    public AssociationFeedback(
            FeedbackType feedbackType,
            String subject,
            String body,
            String senderName,
            String senderMail
    ) {
        super(feedbackType, subject, body, senderName, senderMail);
    }
}
