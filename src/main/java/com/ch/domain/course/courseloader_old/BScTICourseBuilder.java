//package com.ch.domain.course.courseloader_old;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.springframework.stereotype.Component;
//
///**
// * Build the BSc TI courses.
// *
// * Created by Tom on 29/04/2017.
// */
//@Component
//public class BScTICourseBuilder extends CourseBuilder {
//
//    /**
//     * Constructor which sets the current program.
//     */
//    protected BScTICourseBuilder() {
//        super("BSc TI");
//    }
//
//    /**
//     * Creates the courses for the program structure.
//     *
//     * @param program       The JSONObject for the current program.
//     * @throws Exception    If parsing of JSON goes wrong.
//     */
//    protected void createCoursesFromProgram(JSONObject program) throws Exception {
//        JSONArray programYears = program.getJSONObject("studieprogrammaboom")
//                .getJSONObject("studieprogramma")
//                .getJSONObject("studieprogrammaboom")
//                .getJSONArray("studieprogramma");
//
//        for (int index = 0; index < programYears.length(); index++) {
//            JSONObject programYear = (JSONObject) programYears.get(index);
//            JSONArray courses = programYear.getJSONArray("vak");
//
//            for (int j = 0; j < courses.length(); j++) {
//                JSONObject course = (JSONObject) courses.get(j);
//                this.saveCourse(course);
//            }
//        }
//    }
//}
