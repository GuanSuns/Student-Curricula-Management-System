package guan.suns.model;

import guan.suns.basicClass.Gender;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by lenovo on 2016/5/5.
 */

@Entity
@Table(name = "studentTable")
public class StudentPDM {
    @Id
    @GeneratedValue
    @Column(name = "studentID", length = 10)
    private String studentID;

    @OneToOne(fetch=FetchType.EAGER,cascade={CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name="studentID",referencedColumnName="userID")
    @Column(name = "userInfo")
    private UserPDM userInfo;

    @Column(name = "studentName")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "studentGender")
    private Gender gender;

    @Column(name = "className")
    private String className;

    @Column(name = "currentAge")
    private Integer age;

    @Column(name = "enrolledAge")
    @Range(min = 10,max = 50)
    private Integer enrolledAge;

    @Column(name = "enrolledTime")
    private Timestamp enrolledTime;

    public StudentPDM() {
    }

    public StudentPDM(UserPDM userInfo, String name, Gender gender, String className, Integer age, Integer enrolledAge, Timestamp enrolledTime) {
        this.userInfo = userInfo;
        this.name = name;
        this.gender = gender;
        this.className = className;
        this.age = age;
        this.enrolledAge = enrolledAge;
        this.enrolledTime = enrolledTime;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public UserPDM getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserPDM userInfo) {
        this.userInfo = userInfo;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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
}
