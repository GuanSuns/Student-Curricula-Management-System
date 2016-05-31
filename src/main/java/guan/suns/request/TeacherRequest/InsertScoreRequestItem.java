package guan.suns.request.TeacherRequest;


import java.sql.Timestamp;

/**
 * Created by lenovo on 2016/5/30.
 */
public class InsertScoreRequestItem {

    private String studentID;
    private String courseID;
    private Float score;
    private Timestamp selectYear;

    public InsertScoreRequestItem() {
    }

    public InsertScoreRequestItem(String studentID, String courseID, Float score, Timestamp selectYear) {
        this.studentID = studentID;
        this.courseID = courseID;
        this.score = score;
        this.selectYear = selectYear;
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

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Timestamp getSelectYear() {
        return selectYear;
    }

    public void setSelectYear(Timestamp selectYear) {
        this.selectYear = selectYear;
    }
}
