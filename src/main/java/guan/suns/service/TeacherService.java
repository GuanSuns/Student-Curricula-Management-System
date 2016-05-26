package guan.suns.service;

import guan.suns.exception.PasswordErrorException;
import guan.suns.exception.UserExistedException;
import guan.suns.exception.UserNotFoundException;
import guan.suns.model.TeacherPDM;

/**
 * Created by lenovo on 2016/5/25.
 */
public interface TeacherService {
    public TeacherPDM loginTeacher(TeacherPDM teacher) throws UserNotFoundException,PasswordErrorException;

    public boolean createTeacher(TeacherPDM teacher) throws UserExistedException;

    public boolean deleteTeacher(TeacherPDM teacher) throws UserNotFoundException;

    public TeacherPDM getTeacherDetail(TeacherPDM teacher) throws UserNotFoundException;
}
