package com.ch.service;

import com.ch.domain.AssociationFeedback;
import com.ch.domain.EducationFeedback;
import com.ch.domain.Feedback;
import com.ch.domain.Program;
import org.springframework.beans.factory.annotation.Autowired;
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

	public void sendNotifications(Feedback feedback) {
		this.sendSenderNotification(feedback);
		this.sendAdminNotification(feedback);
	}
	
	public void sendSenderNotification(Feedback feedback) {
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
		return "INSERT ASSOCIATION COPY HERE";
	}

	public void  sendAdminNotification(Feedback feedback) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("chfeedbacktool@gmail.com");
		mail.setSubject("[CH FeedbackTool] New feedback available");
		if (feedback instanceof EducationFeedback) {
			Program program = ((EducationFeedback) feedback).getCourse().getProgram();
			if (program.equals(Program.BScTW) || program.equals(Program.MScAM)) {
				//TODO: Change to COW
				mail.setTo("tomharting@hotmail.com");
			} else {
				//TODO: Change to COI
				mail.setTo("tomharting@hotmail.com");
			}
			mail.setText(adminEducationFeedback((EducationFeedback) feedback));
		}
		else if (feedback instanceof AssociationFeedback) {
			//TODO: Change to bestuur
			mail.setTo("tomharting@hotmail.com");
			mail.setText(adminAssociationFeedback((AssociationFeedback) feedback));
		}
		javaMailSender.send(mail);
	}

	public String adminEducationFeedback(EducationFeedback educationFeedback) {
		return "INSERT EDUCATION ADMIN MESSAGE HERE";
	}

	public String adminAssociationFeedback(AssociationFeedback associationFeedback) {
		return "INSERT ASSOCIATION ADMIN MESSAGE HERE";
	}
	
}
