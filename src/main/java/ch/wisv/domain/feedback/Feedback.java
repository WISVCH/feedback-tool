package ch.wisv.domain.feedback;

import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Feedback Entity.
 */
@Entity
@Data
public abstract class Feedback {

    /**
     * ID.
     */
    @Id @GeneratedValue
    @Setter(AccessLevel.NONE)
    long id;

    /**
     * Unique public identifier.
     */
    @NotEmpty
    @Column(unique = true)
    String key;

    /**
     * Type of feedback: positive, negative and suggestion.
     */
    @NotNull(message = "Please select a feedback type")
    FeedbackType feedbackType;

    /**
     * Subject of the feedback.
     */
    @NotEmpty(message = "Subject cannot be empty")
    String subject;

    /**
     * Description of the feedback.
     */
    @NotEmpty(message = "Feedback description cannot be empty")
    @Column(columnDefinition = "TEXT")
    String body;

    /**
     * Creation timestamp.
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat( pattern="dd/MM/yyyy hh:mm:ss")
    Date postedOn;

    /**
     * Timestamp on which feedback is handled.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    Date handledOn;

    /**
     * Flag is feedback is handled.
     */
    boolean handled;

    /**
     * (Optional) Name of the creator.
     */
    String senderName;

    /**
     * (Optional) Email of the creator.
     */
    @Email(message = "This is not a valid email address")
    String senderMail;

    /**
     * Default constructor.
     */
    public Feedback() {
        this.key = UUID.randomUUID().toString();
        this.postedOn = new Date();
        this.handled = false;
    }

    /**
     * Default constructor.
     */
    public Feedback(
            FeedbackType feedbackType,
            String subject,
            String body,
            String senderName,
            String senderMail
    ) {
        this();
        this.feedbackType = feedbackType;
        this.subject = subject;
        this.body = body;
        this.senderName = senderName;
        this.senderMail = senderMail;
    }
}
