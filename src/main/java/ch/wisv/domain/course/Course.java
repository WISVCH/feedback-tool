package ch.wisv.domain.course;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Course.
 */
@Entity
@Data
public class Course {
    @Id
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
            case "EWI keuzevakken  service-onderwijs" :
                this.programEnum = ProgramEnum.ServiceEducation;
                break;
        }
    }

    @Override
    public String toString() {
        return this.courseCode + ' ' + this.name;
    }
}
