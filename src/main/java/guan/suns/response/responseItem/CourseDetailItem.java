package guan.suns.response.responseItem;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by lenovo on 2016/5/29.
 */
public class CourseDetailItem {

    private String courseID;
    private String courseName;
    private String teacherID;
    private String teacherName;
    private Timestamp expiredDate;
    private Integer suitableGrade;

    public CourseDetailItem() {
    }

    public CourseDetailItem(String courseID, String courseName, String teacherID, String teacherName, Timestamp expiredDate, Integer suitableGrade) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.teacherID = teacherID;
        this.teacherName = teacherName;
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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Timestamp getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Timestamp expiredDate) {
        this.expiredDate = expiredDate;
    }

    public Integer getSuitableGrade() {
        return suitableGrade;
    }

    public void setSuitableGrade(Integer suitableGrade) {
        this.suitableGrade = suitableGrade;
    }
}
