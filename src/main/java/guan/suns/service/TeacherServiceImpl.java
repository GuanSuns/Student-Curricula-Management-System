package guan.suns.service;

import guan.suns.exception.PasswordErrorException;
import guan.suns.exception.UserExistedException;
import guan.suns.exception.UserNotFoundException;
import guan.suns.model.TeacherPDM;
import guan.suns.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lenovo on 2016/5/25.
 */

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService{

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public TeacherPDM loginTeacher(TeacherPDM teacher) throws UserNotFoundException, PasswordErrorException {
        if(teacher == null
                || teacher.getTeacherID() == null
                || teacher.getTeacherID().length() != 5
                || teacher.getPassword()==null
                || teacher.getPassword().isEmpty()
                ){
            throw new UserNotFoundException();
        }

        TeacherPDM getTeacher = teacherRepository.findOne(teacher.getTeacherID());

        if(getTeacher==null){
            throw new UserNotFoundException();
        }
        if(!getTeacher.getPassword().equals(teacher.getPassword())){
            throw new PasswordErrorException();
        }

        TeacherPDM returnTeacher = new TeacherPDM(getTeacher.getTeacherID(),getTeacher.getTeacherName(),getTeacher.getDepartment(),getTeacher.getPassword());

        return returnTeacher;
    }

    @Override
    public boolean createTeacher(TeacherPDM teacher) throws UserExistedException {
        if(teacher == null
                || teacher.getPassword() == null
                || teacher.getPassword().equals("")
                || teacher.getTeacherName() == null
                || teacher.getTeacherName().equals("")
                || teacher.getTeacherID() == null
                || teacher.getTeacherID().length() != 5
                )
            return false;

        TeacherPDM newTeacher = teacherRepository.findOne(teacher.getTeacherID());
        if(newTeacher != null) throw new UserExistedException();

        teacherRepository.save(teacher);

        return true;
    }

    @Override
    public boolean deleteTeacher(TeacherPDM teacher) throws UserNotFoundException {
        if(teacher == null
                || teacher.getTeacherID() == null
                || teacher.getTeacherID().length() != 5
                )
            return false;

        TeacherPDM newTeacher = teacherRepository.findOne(teacher.getTeacherID());
        if(newTeacher == null) throw new UserNotFoundException();

        teacherRepository.delete(newTeacher);

        return true;
    }

    @Override
    public TeacherPDM getTeacherDetail(TeacherPDM teacher) throws UserNotFoundException {
        if(teacher == null
                || teacher.getTeacherID() == null
                || teacher.getTeacherID().length() != 5
                ){
            throw new UserNotFoundException();
        }

        TeacherPDM getTeacher = teacherRepository.findOne(teacher.getTeacherID());

        if(getTeacher==null){
            throw new UserNotFoundException();
        }

        TeacherPDM returnTeacher = new TeacherPDM(getTeacher.getTeacherID(),getTeacher.getTeacherName(),getTeacher.getDepartment(),getTeacher.getPassword());

        return returnTeacher;
    }
}