package ch.wisv;

import ch.wisv.domain.course.Course;
import ch.wisv.domain.course.Instructor;
import ch.wisv.domain.course.ProgramEnum;
import ch.wisv.domain.feedback.AssociationFeedback;
import ch.wisv.domain.feedback.EducationFeedback;
import ch.wisv.domain.feedback.FeedbackType;
import ch.wisv.repository.AssociationFeedbackRepository;
import ch.wisv.repository.CourseRepository;
import ch.wisv.repository.EducationFeedbackRepository;
import ch.wisv.repository.InstructorRepository;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class TestDataRunner implements CommandLineRunner {

    private AssociationFeedbackRepository associationFeedbackRepository;

    private CourseRepository courseRepository;

    private EducationFeedbackRepository educationFeedbackRepository;

    private InstructorRepository instructorRepository;

    @Autowired
    public TestDataRunner(
            AssociationFeedbackRepository associationFeedbackRepository,
            CourseRepository courseRepository,
            EducationFeedbackRepository educationFeedbackRepository,
            InstructorRepository instructorRepository
    ) {

        this.associationFeedbackRepository = associationFeedbackRepository;
        this.courseRepository = courseRepository;
        this.educationFeedbackRepository = educationFeedbackRepository;
        this.instructorRepository = instructorRepository;
    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     *
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        Instructor instructor = new Instructor("Prof. T Esting", "tom@testing.tudelft.nl");
        instructorRepository.save(instructor);

        Course course = new Course("TI1206", "Test course", ImmutableList.of(instructor), ProgramEnum.BScTI);
        courseRepository.save(course);

        EducationFeedback educationFeedback = new EducationFeedback(
                course,
                FeedbackType.Positive,
                "Test feedback subject",
                "Test feedback body",
                "Marc von Ramstein",
                "marc@marc.marc"
        );
        educationFeedbackRepository.save(educationFeedback);

        AssociationFeedback associationFeedback = new AssociationFeedback(
                FeedbackType.Positive,
                "Test feedback subject",
                "Test feedback body",
                "",
                ""
        );
        associationFeedbackRepository.save(associationFeedback);
    }
}
