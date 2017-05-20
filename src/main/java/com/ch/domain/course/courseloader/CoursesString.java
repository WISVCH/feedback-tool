package com.ch.domain.course.courseloader;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Created by Tom on 21/05/2017.
 */
@Component
@Data
public class CoursesString {
    private String courseLoadList;
    private String courseDeleteList;
}
