package com.ch.service;

import com.ch.domain.AssociationFeedback;
import com.ch.domain.EducationFeedback;
import com.ch.domain.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

	private JavaMailSender javaMailSender;
	
	@Autowired
	public NotificationService(JavaMailSender javaMailSender){
		this.javaMailSender = javaMailSender;
	}
	
	public void sendSenderNotification(Feedback feedback) throws MailException {
		if (!feedback.getSenderMail().equals("")) {
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo(feedback.getSenderMail());
			mail.setFrom("chfeedbacktool@gmail.com");
			mail.setSubject("[CH FeedbackTool] Copy of your feedback: " + feedback.getSubject());
			if (feedback instanceof EducationFeedback)
				mail.setText(copyEducationFeedback((EducationFeedback) feedback));
			else if (feedback instanceof AssociationFeedback)
				mail.setText(copyAssociationFeedback((AssociationFeedback) feedback));
			javaMailSender.send(mail);
		}
	}

	public String copyEducationFeedback(EducationFeedback educationFeedback) {
		return "INSERT EDUCATION COPY HERE";
	}

	public String copyAssociationFeedback(AssociationFeedback associationFeedback) {
		return "INSERT EDUCATION COPY HERE";
	}
	
}
