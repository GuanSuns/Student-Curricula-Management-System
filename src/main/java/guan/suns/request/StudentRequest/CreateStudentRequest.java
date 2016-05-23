package guan.suns.request.StudentRequest;

import guan.suns.basicClass.Department;
import guan.suns.basicClass.Gender;

import java.sql.Timestamp;

/**
 * Created by lenovo on 2016/5/23.
 */
public class CreateStudentRequest {

    private String id;
    private String password;
    private String name;
    private Gender gender;
    private String className;
    private Integer enrolledAge;
    private Timestamp enrolledTime;
    private Department department;

    public CreateStudentRequest() {
    }

    public CreateStudentRequest(String id, String password, String name, Gender gender, String className, Integer enrolledAge, Timestamp enrolledTime, Department department) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.className = className;
        this.enrolledAge = enrolledAge;
        this.enrolledTime = enrolledTime;
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
