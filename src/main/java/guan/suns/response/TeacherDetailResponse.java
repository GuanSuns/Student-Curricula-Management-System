package guan.suns.response;

import guan.suns.response.responseItem.CourseDetailItem;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/5/29.
 */
public class TeacherDetailResponse {

    private Integer status;
    private String info;

    private String teacherID;
    private String teacherName;
    private Integer department;

    private ArrayList<CourseDetailItem> openCourses;

    public TeacherDetailResponse() {
    }

    public TeacherDetailResponse(Integer status, String info, String teacherID, String teacherName, Integer department, ArrayList<CourseDetailItem> openCourses) {
        this.status = status;
        this.info = info;
        this.teacherID = teacherID;
        this.teacherName = teacherName;
        this.department = department;
        this.openCourses = openCourses;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public ArrayList<CourseDetailItem> getOpenCourses() {
        return openCourses;
    }

    public void setOpenCourses(ArrayList<CourseDetailItem> openCourses) {
        this.openCourses = openCourses;
    }
}
