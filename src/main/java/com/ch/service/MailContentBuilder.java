package com.ch.service;

import com.ch.domain.AssociationFeedback;
import com.ch.domain.EducationFeedback;
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

    @Autowired

    public MailContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String buildEducationAdmin(EducationFeedback educationFeedback) {
        Context context = new Context();
        context.setVariable("feedback", educationFeedback);
        return templateEngine.process("admin/mail/educationAdminMail", context);
    }

    public String buildAssociationAdmin(AssociationFeedback associationFeedback) {
        Context context = new Context();
        context.setVariable("feedback", associationFeedback);
        return templateEngine.process("admin/mail/associationAdminMail", context);
    }

}
