package guan.suns.service;

import guan.suns.exception.UserExistedException;
import guan.suns.exception.UserNotFoundException;
import guan.suns.model.StudentPDM;

/**
 * Created by lenovo on 2016/5/9.
 */
public interface StudentService {

    public boolean createStudent(StudentPDM student) throws UserExistedException;

    public boolean deleteStudent(StudentPDM student) throws UserNotFoundException;

}
