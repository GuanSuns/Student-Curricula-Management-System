package guan.suns.request.StudentRequest;

/**
 * Created by lenovo on 2016/5/31.
 */
public class SelectOrDropCourseRequest {
    private String studentID;
    private String courseID;

    public SelectOrDropCourseRequest() {
    }

    public SelectOrDropCourseRequest(String studentID, String courseID) {
        this.studentID = studentID;
        this.courseID = courseID;
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
}
