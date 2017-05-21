package com.ch.domain.course.courseloader;

import com.ch.domain.course.Course;
import com.ch.domain.course.Instructor;
import com.ch.service.CourseService;
import com.ch.service.InstructorService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

/**
 * Build and save a course.
 * Not the cleanest code due to the far from ideal TU API.
 * <p>
 * Created by Tom on 20/05/2017.
 */
@Component
public class CourseBuilder {

    private ArrayList<Instructor> courseInstructors;
    private CourseService courseService;
    private InstructorService instructorService;

    /**
     * Constructor
     *
     * @param courseService     To save courses.
     * @param instructorService To save and access instructors.
     */
    @Autowired
    public CourseBuilder(CourseService courseService, InstructorService instructorService) {
        this.courseService = courseService;
        this.instructorService = instructorService;
    }

    /**
     * Build and save a course given the course code.
     *
     * @param courseCode The course code of the course that needs to be build.
     */
    protected void build(String courseCode) throws Exception {
        // Get and create the course.
        JSONObject courseJSON = new JSONObject(this.getCourseFromAPI(courseCode)).getJSONObject("vak");
        Course course = new Course();
        // Update if course already exists.
        if (courseService.exists(courseCode)) {
            course = courseService.get(courseCode);
        }
        // Set the course code, name and program.
        course.setCourseCode(courseJSON.getString("cursusid"));
        course.setName(courseJSON.getString("langenaamEN"));
        course.setProgramEnum(courseJSON.getJSONObject("opleiding").getString("code"));

        // Get and save the instructor(s).
        courseInstructors = new ArrayList<>();
        this.saveInstructor(courseJSON.getJSONObject("extraUnsupportedInfo")
                .getJSONArray("vakMedewerkers")
                .getJSONObject(0)
                .get("medewerker"));
        course.setInstructors(courseInstructors);

        // Save the course.
        courseService.save(course);
    }

    /**
     * Retrieve the course from the TU API.
     *
     * @param courseCode The course that is retrieved.
     * @return course       The course as a string.
     */
    private String getCourseFromAPI(String courseCode) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Accept", "application/json;charset=utf-8");

        HttpEntity<?> requestEntity = new HttpEntity(requestHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                "https://api.tudelft.nl/v0/vakken/" + courseCode, HttpMethod.GET, requestEntity,
                String.class);

        return response.toString().substring(8, response.toString().length() - 1);
    }

    /**
     * Get and save the course instructor(s).
     *
     * @param instructorsObject The instructor(s).
     * @throws Exception When the format is invalid.
     */
    private void saveInstructor(Object instructorsObject) throws Exception {
        // One instructor.
        if (instructorsObject instanceof JSONObject) {
            JSONObject instructorJSON = (JSONObject) instructorsObject;
            this.saveOneInstructor(instructorJSON);
            // Multiple instructors.
        } else if (instructorsObject instanceof JSONArray) {
            JSONArray instructorsJSON = (JSONArray) instructorsObject;
            this.saveMultipleInstructors(instructorsJSON);
        }
    }

    /**
     * Save one instructor.
     *
     * @param instructorJSON The instructor that is saved.
     * @throws Exception When the saving goes wrong.
     */
    private void saveOneInstructor(JSONObject instructorJSON) throws Exception {
        String instructorName = instructorJSON.getString("naam");
        String instructorMail = instructorJSON.getString("email");
        Instructor instructor = new Instructor();
        if (!instructorService.instructorExist(instructorMail)) {
            instructor.setName(instructorName);
            instructor.setMail(instructorMail);
            instructorService.save(instructor);
        } else {
            instructor = instructorService.getByMail(instructorMail);
        }
        courseInstructors.add(instructor);
    }

    /**
     * Save multiple instructors.
     *
     * @param instructorsJSON The instructors that are saved.
     * @throws Exception When the saving goes wrong.
     */
    private void saveMultipleInstructors(JSONArray instructorsJSON) throws Exception {
        for (int i = 0; i < instructorsJSON.length(); i++) {
            JSONObject instructorJSON = instructorsJSON.getJSONObject(i);
            this.saveOneInstructor(instructorJSON);
        }
    }
}
