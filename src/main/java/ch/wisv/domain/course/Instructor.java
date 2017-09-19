package ch.wisv.domain.course;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Tom on 14/05/2017.
 */
@Data
@Entity
public class Instructor {

    @Id @GeneratedValue
    private long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String mail;

    @ManyToMany(mappedBy = "instructors")
    private List<Course> courses;

    public Instructor(String name, String mail) {
        this.name = name;
        this.mail = mail;
    }

    public Instructor() {
    }
}
