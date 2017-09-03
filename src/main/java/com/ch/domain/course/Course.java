package com.ch.domain.course;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 28/04/2017.
 */
@Entity
@Data
public class Course {
    @Id @GeneratedValue
    private long id;

    @NotEmpty
    @Column(unique = true)
    private String courseCode;

    @NotEmpty
    private String name;

    @NotEmpty
    @ManyToMany
    private List<Instructor> instructors = new ArrayList<>();

    @NotNull
    private ProgramEnum programEnum;

    public Course(String courseCode, String name, List<Instructor> instructors, ProgramEnum programEnum) {
        this.courseCode = courseCode;
        this.name = name;
        this.instructors = instructors;
        this.programEnum = programEnum;
    }

    public Course() {
    }

    /**
     * TODO: Look for better implementation.
     * @param program
     */
    public void setProgramEnum(String program) {
        switch (program) {
            case "BSc TI" :
                this.programEnum = ProgramEnum.BScTI;
                break;
            case "BSc TW" :
                this.programEnum = ProgramEnum.BScTW;
                break;
            case "MSc CS" :
                this.programEnum = ProgramEnum.MScCS;
                break;
            case "MSc AM" :
                this.programEnum = ProgramEnum.MScAM;
                break;
            case "MSc CE" :
                this.programEnum = ProgramEnum.MScCE;
                break;
            case "MSc ES" :
                this.programEnum = ProgramEnum.MScES;
                break;
        }
    }

    @Override
    public String toString() {
        return this.courseCode + ' ' + this.name;
    }
}
