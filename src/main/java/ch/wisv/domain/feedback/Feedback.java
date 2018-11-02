package ch.wisv.domain.feedback;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Tom on 29/04/2017.
 */
@Entity
@Data
public abstract class Feedback {
    @Id @GeneratedValue
    long id;

    @NotNull(message = "Please select a feedback type")
    FeedbackType feedbackType;

    @NotEmpty(message = "Subject cannot be empty")
    String subject;

    @NotEmpty(message = "Feedback description cannot be empty")
    @Column(columnDefinition = "TEXT")
    String body;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat( pattern="dd/MM/yyyy hh:mm:ss")
    Date postedOn;

    boolean handled;

    String senderName;

    @Email(message = "This is not a valid email address")
    String senderMail;

    Feedback() {
        this.postedOn = new Date();
        this.handled = false;
    }
}
