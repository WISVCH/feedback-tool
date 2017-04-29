package com.ch.domain;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by Tom on 28/04/2017.
 */
@Entity
@Data
public class Course {
    @Id @GeneratedValue
    private long id;

    @NotEmpty
    private String courseCode;

    @NotEmpty
    private String name;

    @NotEmpty
    private String instructorName;

    @NotEmpty
    private String instructorMail;

    @NotNull
    private Program program;

    public Course() {
        super();
    }

    public Course(String courseCode, String name, String instructorName, String instructorMail, Program program) {
        this.courseCode = courseCode;
        this.name = name;
        this.instructorName = instructorName;
        this.instructorMail = instructorMail;
        this.program = program;
    }
}
