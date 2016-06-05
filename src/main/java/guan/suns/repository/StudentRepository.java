package guan.suns.repository;

import guan.suns.basicClass.Department;
import guan.suns.model.StudentPDM;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/5/5.
 */

public interface StudentRepository extends JpaRepository<StudentPDM, String> {

    ArrayList<StudentPDM> findByName(String name);
    ArrayList<StudentPDM> findByDepartment(Department department);
    ArrayList<StudentPDM> findByClassName(String className);
    ArrayList<StudentPDM> findByNameAndDepartment(String name, Department department);
    ArrayList<StudentPDM> findByNameAndClassName(String name, String className);
    ArrayList<StudentPDM> findByClassNameAndDepartment(String className, Department department);
    ArrayList<StudentPDM> findByNameAndClassNameAndDepartment(String name, String className, Department department);

}
