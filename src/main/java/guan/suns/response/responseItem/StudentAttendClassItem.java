package guan.suns.response.responseItem;

/**
 * Created by lenovo on 2016/5/29.
 */
public class StudentAttendClassItem {

    private String courseID;
    private String courseName;
    private Float credit;
    private Float score;
    private String teacherID;
    private String teacherName;
    private Integer department;

    public StudentAttendClassItem() {
    }

    public StudentAttendClassItem(String courseID, String courseName, Float credit, Float score, String teacherID, String teacherName, Integer department) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.credit = credit;
        this.score = score;
        this.teacherID = teacherID;
        this.teacherName = teacherName;
        this.department = department;
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

    public Float getCredit() {
        return credit;
    }

    public void setCredit(Float credit) {
        this.credit = credit;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
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
}
