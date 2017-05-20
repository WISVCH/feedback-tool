//package com.ch.domain.course.courseloader_old;
//
//import com.ch.domain.course.Course;
//import com.ch.domain.course.Instructor;
//import com.ch.service.CourseService;
//import com.ch.service.InstructorService;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
///**
// * Abstract class for CourseBuilders, needed because TU Delft API stores programs in slightly different ways.
// * Code is quite messy, due to the far from perfect API.
// * <p>
// * Created by Tom on 20/05/2017.
// */
//@Component
//public abstract class CourseBuilder {
//
//    private String currentProgram;
//
//    private HashMap<String, Instructor> savedInstructors = new HashMap<>();
//
//    @Autowired
//    private CourseService courseService;
//    @Autowired
//    private InstructorService instructorService;
//
//    public CourseBuilder(String currentProgram) {
//        this.currentProgram = currentProgram;
//    }
//
//    /**
//     * Check the API response and get the current program.
//     */
//    protected void loadCourses(String APIresponse) {
//        try {
//            JSONObject jsonObject = new JSONObject(APIresponse);
//            JSONArray programs = jsonObject.getJSONObject("getOpleidingenByFacultyAndYearResponse")
//                    .getJSONArray("opleiding");
//
//            for (int index = 0; index < programs.length(); index++) {
//                JSONObject program = (JSONObject) programs.get(index);
//                String programCode = program.getString("code");
//                if (currentProgram.equals(programCode)) {
//                    this.createCoursesFromProgram(program);
//                }
//
//            }
//        } catch (Exception e) {
//            System.out.println("Something went wrong loading the courses: " + e.getMessage());
//        }
//
//    }
//
//    /**
//     * Must be implemented by children to show how to get to the courses from the current program.
//     *
//     * @param program The JSONObject for the current program.
//     * @throws Exception If the structure is invalid.
//     */
//    protected abstract void createCoursesFromProgram(JSONObject program) throws Exception;
//
//    /**
//     * Save a course.
//     *
//     * @param course Course to be saved.
//     * @throws Exception If something goes wrong in saving.
//     */
//    protected void saveCourse(JSONObject course) throws Exception {
//        // Set the code, name and program.
//        Course currentCourse = new Course();
//        currentCourse.setCourseCode(course.getString("kortenaamNL"));
//        currentCourse.setName(course.getString("langenaamEN"));
//        currentCourse.setProgramEnum(currentProgram);
//
//        // Get the instructors.
//        JSONObject instructorList = course.getJSONObject("docenten");
//        Object instructorObject = instructorList.get("persoon");
//        ArrayList<Instructor> courseInstructors = new ArrayList<>();
//
//        // If there are multiple instructors
//        if (instructorObject instanceof JSONArray) {
//            JSONArray instructors = (JSONArray) instructorObject;
//            for (int index = 0; index < instructors.length(); index++) {
//                Instructor currentInstructor = new Instructor();
//                JSONObject instructor = instructors.getJSONObject(index);
//                currentInstructor.setMail(instructor.getString("emailAdresTU"));
//                currentInstructor.setName(instructor.getJSONObject("naam").getString("volledigeNaam"));
//                this.saveInstructor(currentInstructor, courseInstructors);
//            }
//            // If there is one instructor.
//        } else if (instructorObject instanceof JSONObject) {
//            JSONObject instructor = (JSONObject) instructorObject;
//            Instructor currentInstructor = new Instructor();
//            currentInstructor.setMail(instructor.getString("emailAdresTU"));
//            currentInstructor.setName(instructor.getJSONObject("naam").getString("volledigeNaam"));
//            this.saveInstructor(currentInstructor, courseInstructors);
//        }
//
//        // Set the instructor(s) and save the course.
//        currentCourse.setInstructors(courseInstructors);
//        System.out.println(currentCourse);
//        courseService.save(currentCourse);
//    }
//
//    /**
//     * Save the instructors that are not yet in the database.
//     *
//     * @param currentInstructor The current instructor.
//     * @param courseInstructors The instructors for the current course.
//     */
//    protected void saveInstructor(Instructor currentInstructor, ArrayList<Instructor> courseInstructors) {
//        if (!this.savedInstructors.containsKey(currentInstructor.getMail())) {
//            this.savedInstructors.put(currentInstructor.getMail(), currentInstructor);
//            this.instructorService.save(currentInstructor);
//        } else {
//            currentInstructor = savedInstructors.get(currentInstructor.getMail());
//        }
//        courseInstructors.add(currentInstructor);
//    }
//}
