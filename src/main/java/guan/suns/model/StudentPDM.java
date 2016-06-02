package guan.suns.model;

import guan.suns.basicClass.Department;
import guan.suns.basicClass.Gender;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lenovo on 2016/5/5.
 */

@Entity
@Table(name = "student_table")
public class StudentPDM {
    @Id
    @Column(name = "studentID")
    private String studentID;

    @Column(name = "password")
    private String password;

    @Column(name = "studentName")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "studentGender")
    private Gender gender;

    @Column(name = "className")
    private String className;

    @Enumerated(EnumType.STRING)
    @Column(name = "department")
    private Department department;

    @Column(name = "enrolledAge")
    @Range(min = 10,max = 50)
    private Integer enrolledAge;

    @Column(name = "enrolledTime")
    private Timestamp enrolledTime;

    @OneToMany(fetch=FetchType.LAZY,cascade={CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "studentID", referencedColumnName = "studentID")
    private Set<CourseSelectionPDM> selectedCourses = new HashSet<CourseSelectionPDM>();

    public StudentPDM() {
    }

    public StudentPDM(String studentID, String password, String name, Gender gender, String className, Department department, Integer enrolledAge, Timestamp enrolledTime) {
        this.studentID = studentID;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.className = className;
        this.department = department;
        this.enrolledAge = enrolledAge;
        this.enrolledTime = enrolledTime;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getEnrolledAge() {
        return enrolledAge;
    }

    public void setEnrolledAge(Integer enrolledAge) {
        this.enrolledAge = enrolledAge;
    }

    public Timestamp getEnrolledTime() {
        return enrolledTime;
    }

    public void setEnrolledTime(Timestamp enrolledTime) {
        this.enrolledTime = enrolledTime;
    }

    public Set<CourseSelectionPDM> getSelectedCourses() {
        return selectedCourses;
    }

    public void setSelectedCourses(Set<CourseSelectionPDM> selectedCourses) {
        this.selectedCourses = selectedCourses;
    }

    @Override
    public String toString() {
        return "StudentPDM{" +
                "enrolledTime=" + enrolledTime +
                ", studentID='" + studentID + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", className='" + className + '\'' +
                ", department=" + department +
                ", enrolledAge=" + enrolledAge +
                '}';
    }
}
