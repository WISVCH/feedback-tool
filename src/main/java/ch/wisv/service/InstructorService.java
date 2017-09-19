package ch.wisv.service;

import ch.wisv.domain.course.Instructor;
import ch.wisv.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Tom on 29/04/2017.
 */
@Service
public class InstructorService {

    private InstructorRepository instructorRepository;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public Instructor save(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    public boolean instructorExist(String mail) {
        return instructorRepository.getFirstByMailIs(mail) != null;
    }

    public Instructor getByMail(String mail) {
        return instructorRepository.getFirstByMailIs(mail);
    }
}
