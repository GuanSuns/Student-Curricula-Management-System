package guan.suns.request.TeacherRequest;

import guan.suns.basicClass.Grade;
import guan.suns.model.TeacherPDM;

import java.sql.Timestamp;

/**
 * Created by lenovo on 2016/5/28.
 */
public class CreateCourseRequest {

    private String courseID;
    private String courseName;
    private String teacherID;
    private Integer credit;
    private Timestamp expiredDate;
    private Grade suitableGrade;

    public CreateCourseRequest() {
    }

    public CreateCourseRequest(String courseID, String courseName, String teacherID, Integer credit, Timestamp expiredDate, Grade suitableGrade) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.teacherID = teacherID;
        this.credit = credit;
        this.expiredDate = expiredDate;
        this.suitableGrade = suitableGrade;
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

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Timestamp getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Timestamp expiredDate) {
        this.expiredDate = expiredDate;
    }

    public Grade getSuitableGrade() {
        return suitableGrade;
    }

    public void setSuitableGrade(Grade suitableGrade) {
        this.suitableGrade = suitableGrade;
    }
}
