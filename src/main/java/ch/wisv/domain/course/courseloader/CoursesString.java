package ch.wisv.domain.course.courseloader;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * Created by Tom on 21/05/2017.
 */
@Component
@Data
public class CoursesString {
    @NotNull
    private String courseLoadList;
    private String courseDeleteList;
}
