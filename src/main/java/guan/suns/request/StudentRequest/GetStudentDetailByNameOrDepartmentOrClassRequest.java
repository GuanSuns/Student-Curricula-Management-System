package guan.suns.request.StudentRequest;

import guan.suns.basicClass.Department;

/**
 * Created by lenovo on 2016/6/5.
 */
public class GetStudentDetailByNameOrDepartmentOrClassRequest {

    private String name;
    private String className;
    private Department department;

    public GetStudentDetailByNameOrDepartmentOrClassRequest() {
    }

    public GetStudentDetailByNameOrDepartmentOrClassRequest(String name, String className, Department department) {
        this.name = name;
        this.className = className;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
