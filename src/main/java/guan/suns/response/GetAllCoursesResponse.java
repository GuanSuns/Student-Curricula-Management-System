package guan.suns.response;

import guan.suns.response.responseItem.CourseDetailsItem;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/6/7.
 */
public class GetAllCoursesResponse {

    private ArrayList<CourseDetailsItem> courses;

    public GetAllCoursesResponse() {
    }

    public GetAllCoursesResponse(ArrayList<CourseDetailsItem> courses) {
        this.courses = courses;
    }

    public ArrayList<CourseDetailsItem> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<CourseDetailsItem> courses) {
        this.courses = courses;
    }
}
