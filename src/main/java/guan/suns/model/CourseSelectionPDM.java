package guan.suns.model;

import com.sun.jmx.snmp.Timestamp;

import javax.persistence.*;

/**
 * Created by lenovo on 2016/5/5.
 */

@Entity
@Table(name = "course_selection_table")
public class CourseSelectionPDM {
    @Id
    private CourseSelectionCompositeId courseSelectionCompositeId;

    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn(name = "teacherID", referencedColumnName = "teacherID")
    private TeacherPDM teacherID;

    @Column(name = "score")
    private Float score;

    @Column(name = "selectYear")
    private Timestamp selectYear;

    public CourseSelectionPDM() {
    }

    public CourseSelectionPDM(CourseSelectionCompositeId courseSelectionCompositeId, TeacherPDM teacherID, Float score, Timestamp selectYear) {
        this.courseSelectionCompositeId = courseSelectionCompositeId;
        this.teacherID = teacherID;
        this.score = score;
        this.selectYear = selectYear;
    }

    public CourseSelectionCompositeId getCourseSelectionCompositeId() {
        return courseSelectionCompositeId;
    }

    public void setCourseSelectionCompositeId(CourseSelectionCompositeId courseSelectionCompositeId) {
        this.courseSelectionCompositeId = courseSelectionCompositeId;
    }

    public TeacherPDM getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(TeacherPDM teacherID) {
        this.teacherID = teacherID;
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
