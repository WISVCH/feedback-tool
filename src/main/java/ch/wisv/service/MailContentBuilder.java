package ch.wisv.service;

import ch.wisv.domain.feedback.AssociationFeedback;
import ch.wisv.domain.feedback.EducationFeedback;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Created by Tom on 06/05/2017.
 */
@Service
public class MailContentBuilder {
    private TemplateEngine templateEngine;

    @Setter
    @Getter
    private String clientUri;

    @Autowired
    public MailContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String buildEducationAdmin(EducationFeedback educationFeedback) {
        Context context = new Context();
        context.setVariable("feedback", educationFeedback);
        context.setVariable("clientUri", this.clientUri);
        return templateEngine.process("admin/mail/educationAdminMail", context);
    }

    public String buildAssociationAdmin(AssociationFeedback associationFeedback) {
        Context context = new Context();
        context.setVariable("feedback", associationFeedback);
        context.setVariable("clientUri", this.clientUri);
        return templateEngine.process("admin/mail/associationAdminMail", context);
    }

    public String buildEducationSender(EducationFeedback educationFeedback) {
        Context context = new Context();
        context.setVariable("feedback", educationFeedback);
        return templateEngine.process("education/educationMail", context);
    }

    public String buildAssociationSender(AssociationFeedback associationFeedback) {
        Context context = new Context();
        context.setVariable("feedback", associationFeedback);
        return templateEngine.process("association/associationMail", context);
    }
}
