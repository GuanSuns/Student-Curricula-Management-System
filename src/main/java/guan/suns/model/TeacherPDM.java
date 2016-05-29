package guan.suns.model;

import guan.suns.basicClass.Department;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lenovo on 2016/5/5.
 */

@Entity
@Table(name = "teacher_table")
public class TeacherPDM {
    @Id
    @Column(name = "teacherID", length = 5)
    private String teacherID;

    @Column(name = "teacherName")
    private String teacherName;

    @Column(name = "teacherDepartment")
    private Department department;

    @Column(name = "password")
    private String password;

    @OneToMany(fetch=FetchType.EAGER,cascade={CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "teacherID", referencedColumnName = "teacherID")
    private Set<CoursePDM> courses = new HashSet<CoursePDM>();

    @OneToMany(fetch=FetchType.LAZY,cascade={CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "teacherID", referencedColumnName = "teacherID")
    private Set<CourseSelectionPDM> beSelectedCourses = new HashSet<CourseSelectionPDM>();

    public TeacherPDM() {
    }

    public TeacherPDM(String teacherID, String teacherName, Department department, String password) {
        this.teacherID = teacherID;
        this.teacherName = teacherName;
        this.department = department;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<CourseSelectionPDM> getBeSelectedCourses() {
        return beSelectedCourses;
    }

    public void setBeSelectedCourses(Set<CourseSelectionPDM> beSelectedCourses) {
        this.beSelectedCourses = beSelectedCourses;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public Set<CoursePDM> getCourses() {
        return courses;
    }

    public void setCourses(Set<CoursePDM> courses) {
        this.courses = courses;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
