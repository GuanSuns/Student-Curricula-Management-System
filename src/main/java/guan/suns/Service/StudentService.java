package guan.suns.service;

import guan.suns.exception.PasswordErrorException;
import guan.suns.exception.UserExistedException;
import guan.suns.exception.UserNotFoundException;
import guan.suns.model.StudentPDM;

/**
 * Created by lenovo on 2016/5/9.
 */
public interface StudentService {

    public StudentPDM loginStudent(StudentPDM student) throws UserNotFoundException,PasswordErrorException;

    public boolean createStudent(StudentPDM student) throws UserExistedException;

    public boolean deleteStudent(StudentPDM student) throws UserNotFoundException;



}
