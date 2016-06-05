package guan.suns.request.StudentRequest;

import guan.suns.basicClass.Department;

/**
 * Created by lenovo on 2016/6/5.
 */
public class GetStudentDetailsStatisticsRequest {

    private String studentID;
    private String name;
    private String className;
    private Department department;

    private String courseID;
    private String courseName;

    public GetStudentDetailsStatisticsRequest() {
    }

    public GetStudentDetailsStatisticsRequest(String studentID, String name, String className, Department department, String courseID, String courseName) {
        this.studentID = studentID;
        this.name = name;
        this.className = className;
        this.department = department;
        this.courseID = courseID;
        this.courseName = courseName;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
