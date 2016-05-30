package guan.suns.request.TeacherRequest;

/**
 * Created by lenovo on 2016/5/30.
 */
public class DeleteCourseRequest {

    private String courseID;

    public DeleteCourseRequest() {
    }

    public DeleteCourseRequest(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
}
