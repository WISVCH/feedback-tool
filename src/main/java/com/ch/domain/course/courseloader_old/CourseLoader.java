//package com.ch.domain.course.courseloader_old;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//import javax.annotation.PostConstruct;
//
///**
// * Load the courses from the TU Delft API.
// *
// * Created by Tom on 20/05/2017.
// */
//@Component
//@ConfigurationProperties(prefix = "courses")
//public class CourseLoader {
//
//    @Getter
//    @Setter
//    private String loadPrograms;
//
//    private BScTICourseBuilder BScTI;
//
//    @Autowired
//    public CourseLoader(BScTICourseBuilder BScTI) {
//        this.BScTI = BScTI;
//    }
//
//    @PostConstruct
//    public void loadCourses() {
//        try {
//            String APIresponse = this.getStringFromTUAPI();
//            if (loadPrograms.contains("BSc TI")) {
//                BScTI.loadCourses(APIresponse);
//            }
//        } catch (Exception e) {
//            System.out.println("Something went wrong loading the courses: " + e.getMessage());
//        }
//
//    }
//
//    private String getStringFromTUAPI() throws Exception {
//        HttpHeaders requestHeaders = new HttpHeaders();
//        requestHeaders.set("Accept", "application/json;charset=utf-8");
//
//        HttpEntity<?> requestEntity = new HttpEntity(requestHeaders);
//
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> response = restTemplate.exchange(
//                "https://api.tudelft.nl/v0/opleidingen/EWI", HttpMethod.GET, requestEntity,
//                String.class);
//
//        return response.toString().substring(8, response.toString().length() - 1);
//    }
//}
