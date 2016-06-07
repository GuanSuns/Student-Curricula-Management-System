package guan.suns.response.responseItem;

import guan.suns.basicClass.Department;

import java.sql.Timestamp;

/**
 * Created by lenovo on 2016/6/7.
 */
public class CourseStudentItem {

    private String studentName;
    private String studentID;
    private Float score;
    private Integer department;
    private String className;
    private Timestamp selectYear;

    public CourseStudentItem() {
    }

    public CourseStudentItem(String studentName, String studentID, Float score, Integer department, String className, Timestamp selectYear) {
        this.studentName = studentName;
        this.studentID = studentID;
        this.score = score;
        this.department = department;
        this.className = className;
        this.selectYear = selectYear;
    }

    public Timestamp getSelectYear() {
        return selectYear;
    }

    public void setSelectYear(Timestamp selectYear) {
        this.selectYear = selectYear;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
