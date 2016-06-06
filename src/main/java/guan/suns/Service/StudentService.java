package guan.suns.service;

import guan.suns.exception.*;
import guan.suns.model.StudentPDM;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/5/9.
 */

public interface StudentService {

    public StudentPDM loginStudent(StudentPDM student) throws UserNotFoundException,PasswordErrorException;

    public boolean createStudent(StudentPDM student) throws UserExistedException, UserInfoErrorException;

    public boolean deleteStudent(StudentPDM student) throws UserNotFoundException;

    public StudentPDM getStudentDetail(StudentPDM student) throws UserNotFoundException, UserInfoErrorException;

    public boolean updateStudent(StudentPDM student) throws UserNotFoundException, UserInfoErrorException;

    public ArrayList<StudentPDM> getStudentDetailByName(StudentPDM student) throws QueryInfoError;

    public ArrayList<StudentPDM> getStudentDetailByDepartment(StudentPDM student) throws QueryInfoError;

    public ArrayList<StudentPDM> getStudentDetailByClassName(StudentPDM student) throws QueryInfoError;

    public ArrayList<StudentPDM> getStudentDetailByNameAndDepartment(StudentPDM student) throws QueryInfoError;

    public ArrayList<StudentPDM> getStudentDetailByNameAndClassName(StudentPDM student) throws QueryInfoError;

    public ArrayList<StudentPDM> getStudentDetailByClassNameAndDepartment(StudentPDM student) throws QueryInfoError;

    public ArrayList<StudentPDM> getStudentDetailByNameAndClassNameAndDepartment(StudentPDM student) throws QueryInfoError;

    public ArrayList<StudentPDM> getAllStudentsDetail();

}
