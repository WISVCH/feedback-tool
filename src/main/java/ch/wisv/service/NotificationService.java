package ch.wisv.service;

import ch.wisv.domain.course.ProgramEnum;
import ch.wisv.domain.feedback.AssociationFeedback;
import ch.wisv.domain.feedback.EducationFeedback;
import ch.wisv.domain.feedback.Feedback;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@ConfigurationProperties(prefix = "mailNotifications")
public class NotificationService {
	@Getter @Setter
	private String from;
	@Getter @Setter
	private String toEducationCS;
	@Getter @Setter
	private String toEducationAM;
	@Getter @Setter
	private String toAssociation;

	private JavaMailSender javaMailSender;
	private MailContentBuilder mailContentBuilder;
	
	@Autowired
	public NotificationService(JavaMailSender javaMailSender, MailContentBuilder mailContentBuilder){
		this.javaMailSender = javaMailSender;
		this.mailContentBuilder = mailContentBuilder;
	}

	public void sendNotifications(Feedback feedback) {
		this.sendAdminNotification(feedback);
		this.sendSenderNotification(feedback);
	}
	
	private void sendSenderNotification(Feedback feedback) {
		if (!feedback.getSenderMail().equals("")) {
			try {
				MimeMessage mail = javaMailSender.createMimeMessage();
				mail.addRecipients(Message.RecipientType.TO, feedback.getSenderMail());
				mail.setFrom(from);
				mail.setSubject("[CH FeedbackTool] Copy of your feedback: " + feedback.getSubject());
				if (feedback instanceof EducationFeedback)
					mail.setContent(mailContentBuilder.buildEducationSender((EducationFeedback) feedback), "text/html");
				else if (feedback instanceof AssociationFeedback)
					mail.setContent(mailContentBuilder.buildAssociationSender((AssociationFeedback) feedback),  "text/html");
				javaMailSender.send(mail);
			}
			catch (MessagingException e) {

			}
		}
	}

	private void  sendAdminNotification(Feedback feedback) {
		try {
			MimeMessage mail = javaMailSender.createMimeMessage();
			mail.setFrom(from);
			mail.setSubject("[CH FeedbackTool] New feedback available");
			if (feedback instanceof EducationFeedback) {
				ProgramEnum programEnum = ((EducationFeedback) feedback).getCourse().getProgramEnum();
				if (programEnum.equals(ProgramEnum.BScTW) || programEnum.equals(ProgramEnum.MScAM)) {
					mail.addRecipients(Message.RecipientType.TO, toEducationAM);
				} else {
					mail.addRecipients(Message.RecipientType.TO, toEducationCS);
				}
				mail.setContent(mailContentBuilder.buildEducationAdmin((EducationFeedback) feedback), "text/html");
			} else if (feedback instanceof AssociationFeedback) {
				mail.addRecipients(Message.RecipientType.TO, toAssociation);
				mail.setContent(mailContentBuilder.buildAssociationAdmin((AssociationFeedback) feedback),"text/html");
			}
			javaMailSender.send(mail);
		}
		catch (MessagingException e) {

		}
	}
	
}
