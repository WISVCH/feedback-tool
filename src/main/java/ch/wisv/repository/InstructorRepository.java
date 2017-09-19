package ch.wisv.repository;

import ch.wisv.domain.course.Instructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Tom on 29/04/2017.
 */
@Repository
public interface InstructorRepository extends CrudRepository<Instructor, Long> {

    public Instructor getFirstByMailIs(String mail);

}
