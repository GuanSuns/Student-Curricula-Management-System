package guan.suns.model;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by lenovo on 2016/5/5.
 */

@Embeddable
public class CourseSelectionCompositeId implements Serializable {

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn(name = "studentID", referencedColumnName = "studentID")
    private StudentPDM studentID;

    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn(name = "courseID", referencedColumnName = "courseID")
    private CoursePDM courseID;

    public CourseSelectionCompositeId() {
    }

    public CourseSelectionCompositeId(StudentPDM studentID, CoursePDM courseID) {
        this.studentID = studentID;
        this.courseID = courseID;
    }

    public StudentPDM getStudentID() {
        return studentID;
    }

    public void setStudentID(StudentPDM studentID) {
        this.studentID = studentID;
    }

    public CoursePDM getCourseID() {
        return courseID;
    }

    public void setCourseID(CoursePDM courseID) {
        this.courseID = courseID;
    }
}
