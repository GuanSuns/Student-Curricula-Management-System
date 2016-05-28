package guan.suns.request.TeacherRequest;

import guan.suns.basicClass.Department;

/**
 * Created by lenovo on 2016/5/28.
 */
public class CreateTeacherRequest {

    private String id;
    private String name;
    private Department department;
    private String password;

    public CreateTeacherRequest() {
    }

    public CreateTeacherRequest(String id, String name, Department department, String password) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
