package guan.suns.service;

import guan.suns.exception.*;
import guan.suns.model.CoursePDM;
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
    @Autowired
    private CourseService courseService;

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
    public boolean createTeacher(TeacherPDM teacher) throws UserExistedException, UserInfoErrorException {
        if(teacher == null
                || teacher.getPassword() == null
                || teacher.getPassword().equals("")
                || teacher.getTeacherName() == null
                || teacher.getTeacherName().equals("")
                || teacher.getTeacherID() == null
                || teacher.getTeacherID().length() != 5
                )
            throw new UserInfoErrorException();

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

        TeacherPDM deleteTeacher = teacherRepository.findOne(teacher.getTeacherID());
        if(deleteTeacher == null) throw new UserNotFoundException();

        for(CoursePDM course: deleteTeacher.getCourses()){
            if(course!=null){
                try{
                    courseService.deleteCourse(course);
                }
                catch (CourseInfoErrorException courseInfoErrorException){
                    courseInfoErrorException.printStackTrace();
                }
                catch (CourseNotFoundException courseNotFoundException){
                    courseNotFoundException.printStackTrace();
                }

            }
        }

        teacherRepository.delete(deleteTeacher);

        return true;
    }

    @Override
    public TeacherPDM getTeacherDetail(TeacherPDM teacher) throws UserNotFoundException, UserInfoErrorException {
        if(teacher == null
                || teacher.getTeacherID() == null
                || teacher.getTeacherID().length() != 5
                ){
            throw new UserInfoErrorException();
        }

        TeacherPDM getTeacher = teacherRepository.findOne(teacher.getTeacherID());

        if(getTeacher==null){
            throw new UserNotFoundException();
        }

        TeacherPDM returnTeacher = new TeacherPDM(getTeacher.getTeacherID(),getTeacher.getTeacherName(),getTeacher.getDepartment(),getTeacher.getPassword());
        returnTeacher.setCourses(getTeacher.getCourses());

        return returnTeacher;
    }

    @Override
    public boolean updateTeacher(TeacherPDM teacher) throws UserNotFoundException, UserInfoErrorException {
        if(teacher == null
                || teacher.getPassword() == null
                || teacher.getPassword().equals("")
                || teacher.getTeacherName() == null
                || teacher.getTeacherName().equals("")
                || teacher.getTeacherID() == null
                || teacher.getTeacherID().length() != 5
                )
            throw new UserInfoErrorException();

        TeacherPDM newTeacher = teacherRepository.findOne(teacher.getTeacherID());
        if(newTeacher == null) throw new UserNotFoundException();

        newTeacher.setPassword(teacher.getPassword());
        newTeacher.setTeacherName(teacher.getTeacherName());
        newTeacher.setDepartment(teacher.getDepartment());

        teacherRepository.save(newTeacher);

        return true;
    }
}

