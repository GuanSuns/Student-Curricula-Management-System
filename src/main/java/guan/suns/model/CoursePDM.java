package guan.suns.model;

import guan.suns.basicClass.Grade;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lenovo on 2016/5/5.
 */
@Entity
@Table(name = "course_table")
public class CoursePDM {

    @Id
    @Column(name = "courseID", length = 7)
    private String courseID;

    @Column(name = "courseName")
    private String courseName;

    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn(name = "teacherID", referencedColumnName = "teacherId")
    private TeacherPDM teacherID;

    @Column(name = "credit")
    private Integer credit;

    @Column(name = "expiredDate", nullable = true)
    private Timestamp expiredDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "suitableGrade")
    private Grade suitableGrade;

    @OneToMany(fetch=FetchType.LAZY,cascade={CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "courseID", referencedColumnName = "courseID")
    private Set<CourseSelectionPDM> selectStudents = new HashSet<CourseSelectionPDM>();


    public CoursePDM() {
    }

    public CoursePDM(String courseID, String courseName, TeacherPDM teacherID, Integer credit, Timestamp expiredDate, Grade suitableGrade) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.teacherID = teacherID;
        this.credit = credit;
        this.expiredDate = expiredDate;
        this.suitableGrade = suitableGrade;
    }

    public Set<CourseSelectionPDM> getSelectStudents() {
        return selectStudents;
    }

    public void setSelectStudents(Set<CourseSelectionPDM> selectStudents) {
        this.selectStudents = selectStudents;
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

    public TeacherPDM getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(TeacherPDM teacherID) {
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
