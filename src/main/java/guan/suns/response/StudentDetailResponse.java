package guan.suns.response;

import guan.suns.response.responseItem.StudentAttendClassItem;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by lenovo on 2016/5/29.
 */
public class StudentDetailResponse {

    private Integer status;
    private String info;

    private String studentID;
    private String name;
    private Integer gender;
    private String className;
    private Integer department;
    private Integer enrolledAge;
    private Timestamp enrolledTime;

    private ArrayList<StudentAttendClassItem> attendClasses;

    public StudentDetailResponse() {
    }

    public StudentDetailResponse(Integer status, String info, String studentID, String name, Integer gender, String className, Integer department, Integer enrolledAge, Timestamp enrolledTime, ArrayList<StudentAttendClassItem> attendClasses) {
        this.status = status;
        this.info = info;
        this.studentID = studentID;
        this.name = name;
        this.gender = gender;
        this.className = className;
        this.department = department;
        this.enrolledAge = enrolledAge;
        this.enrolledTime = enrolledTime;
        this.attendClasses = attendClasses;
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

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public Integer getEnrolledAge() {
        return enrolledAge;
    }

    public void setEnrolledAge(Integer enrolledAge) {
        this.enrolledAge = enrolledAge;
    }

    public Timestamp getEnrolledTime() {
        return enrolledTime;
    }

    public void setEnrolledTime(Timestamp enrolledTime) {
        this.enrolledTime = enrolledTime;
    }

    public ArrayList<StudentAttendClassItem> getAttendClasses() {
        return attendClasses;
    }

    public void setAttendClasses(ArrayList<StudentAttendClassItem> attendClasses) {
        this.attendClasses = attendClasses;
    }
}
