package com.ch;

import com.ch.domain.*;
import com.ch.service.AssociationFeedbackService;
import com.ch.service.CourseService;
import com.ch.service.EducationFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Tom on 29/04/2017.
 */
@Component
public class DataLoader {
    private CourseService courseService;
    private AssociationFeedbackService associationFeedbackService;
    private EducationFeedbackService educationFeedbackService;

    @Autowired
    public DataLoader(CourseService courseService, AssociationFeedbackService associationFeedbackService, EducationFeedbackService educationFeedbackService) {
        this.courseService = courseService;
        this.associationFeedbackService = associationFeedbackService;
        this.educationFeedbackService = educationFeedbackService;
    }

    @PostConstruct
    private void loadData() {
        Course course1 = new Course("TI3306", "Complexiteitstheorie", "Cees Witteveen", "C.Witteveen@tudelft.nl", Program.BScTI);
        Course course2 = new Course("TI1206", "OOP", "Andy Zaidman", "A.Zaidman@tudelft.nl", Program.BScTI);
        Course course3 = new Course("TI2206", "Software Engineering Methods", "Alberto Bachelli", "A.Bachelli@tudelft.nl", Program.BScTI);
        courseService.save(course1);
        courseService.save(course2);
        courseService.save(course3);

        AssociationFeedback associationFeedback1 = new AssociationFeedback();
        associationFeedback1.setFeedbackType(FeedbackType.Positive);
        associationFeedback1.setSubject("Uilen zijn episch!");
        associationFeedback1.setBody(this.getBody());
        associationFeedback1.setSenderName("Tom Harting");
        associationFeedback1.setSenderMail("fbt@ch.tudelft.nl");
        associationFeedbackService.save(associationFeedback1);

        EducationFeedback educationFeedback1 = new EducationFeedback();
        educationFeedback1.setFeedbackType(FeedbackType.Positive);
        educationFeedback1.setSubject("Top vak");
        educationFeedback1.setBody(this.getBody());
        educationFeedback1.setSenderName("Anka Mulder");
        educationFeedback1.setSenderMail("ankamulder@ch.tudelft.nl");
        educationFeedback1.setCourse(course1);
        educationFeedbackService.save(educationFeedback1);

        EducationFeedback educationFeedback2 = new EducationFeedback();
        educationFeedback2.setFeedbackType(FeedbackType.Negative);
        educationFeedback2.setSubject("Vo Scala");
        educationFeedback2.setBody(this.getBody());
        educationFeedback2.setSenderName("E. Visser");
        educationFeedback2.setSenderMail("eelcovisser@ch.tudelft.nl");
        educationFeedback2.setCourse(course2);
        educationFeedbackService.save(educationFeedback2);
    }

    private String getBody(){
        String body = "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum tincidunt et turpis non lobortis. Nullam pellentesque magna eros, et ultricies elit aliquet ut. Nunc et eros ligula. In purus nunc, iaculis quis aliquet ut, rutrum sed dolor. Proin ante turpis, euismod nec varius id, dapibus vel nisi. In bibendum mi in metus ultricies, fringilla gravida mi cursus. Integer nec sapien libero. Ut arcu ex, volutpat sed ex finibus, sodales efficitur eros. Sed sapien justo, tincidunt nec pharetra non, ultricies ac nunc. Mauris ac semper dui. Sed eleifend sit amet erat accumsan suscipit. Aliquam sit amet ipsum mauris. Maecenas sollicitudin, nibh sagittis vulputate tincidunt, neque neque vehicula augue, et lacinia dui nisi vulputate velit. Maecenas sit amet augue interdum, suscipit velit malesuada, fermentum orci.</p>";
        body += "<p>In sit amet mi sollicitudin, iaculis erat venenatis, fringilla turpis. Suspendisse potenti. Curabitur sed molestie eros. Phasellus ultrices, nisi a egestas commodo, ante nisl lobortis diam, at molestie quam lorem eget tellus. Maecenas venenatis sapien ut euismod tempus. Nunc gravida vitae ante faucibus iaculis. Donec nulla dolor, placerat sit amet iaculis at, ornare quis turpis. Proin pharetra vel massa at vulputate. Phasellus efficitur pretium auctor. Mauris tincidunt velit metus, ac varius ante porta vel.</p>";
        body += "<p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Donec quis diam elementum, feugiat elit a, vestibulum neque. Proin in augue fringilla, sollicitudin dolor vel, fringilla libero. Aenean iaculis ante ligula, sit amet maximus ante viverra at. Quisque auctor arcu et ante consectetur vulputate. Suspendisse iaculis libero eu enim eleifend, non mattis leo aliquet. Fusce id nibh euismod, convallis leo ac, rutrum arcu. Nullam at urna commodo diam pharetra vulputate sed vitae mi. Aenean molestie ante nec finibus aliquet. Suspendisse ac turpis vehicula, semper tortor sit amet, sollicitudin ante. Morbi vehicula sem non elit tempor molestie. In convallis, quam ut porta sagittis, tortor nibh euismod dolor, eget sollicitudin massa mauris nec mi. Proin consectetur lacinia commodo. Quisque at consectetur velit. Pellentesque vel sagittis orci, sed scelerisque diam.</p>";
        return body;
    }
}
