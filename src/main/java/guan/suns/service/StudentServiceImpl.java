package guan.suns.service;

import guan.suns.exception.PasswordErrorException;
import guan.suns.exception.UserExistedException;
import guan.suns.exception.UserInfoErrorException;
import guan.suns.exception.UserNotFoundException;
import guan.suns.model.StudentPDM;
import guan.suns.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lenovo on 2016/5/9.
 */

@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public boolean createStudent(StudentPDM student) throws UserExistedException, UserInfoErrorException {

        if(student == null
                || student.getPassword() == null
                || student.getPassword().equals("")
                || student.getName() == null
                || student.getName().equals("")
                || student.getStudentID() == null
                || student.getStudentID().length() != 10
                || student.getEnrolledAge() < 10
                || student.getEnrolledAge() > 50
                )
            throw new UserInfoErrorException();

        StudentPDM newStudent = studentRepository.findOne(student.getStudentID());
        if(newStudent != null) throw new UserExistedException();

        studentRepository.save(student);

        return true;
    }

    @Override
    public boolean deleteStudent(StudentPDM student) throws UserNotFoundException {

        if(student == null
                || student.getStudentID() == null
                || student.getStudentID().length() != 10
                )
            return false;

        StudentPDM newStudent = studentRepository.findOne(student.getStudentID());
        if(newStudent == null) throw new UserNotFoundException();

        studentRepository.delete(newStudent);

        return true;
    }

    @Override
    public StudentPDM loginStudent(StudentPDM student) throws UserNotFoundException, PasswordErrorException {

        if(student == null
                || student.getStudentID() == null
                || student.getStudentID().length() != 10
                || student.getPassword()==null
                || student.getPassword().isEmpty()
                ){
            throw new UserNotFoundException();
        }

        StudentPDM getStudent = studentRepository.findOne(student.getStudentID());

        if(getStudent==null){
            throw new UserNotFoundException();
        }
        if(!getStudent.getPassword().equals(student.getPassword())){
            throw new PasswordErrorException();
        }

        StudentPDM returnStudent = new StudentPDM(getStudent.getStudentID(),getStudent.getPassword(),getStudent.getName(),getStudent.getGender(),getStudent.getClassName(),getStudent.getDepartment(),getStudent.getEnrolledAge(),getStudent.getEnrolledTime());

        return returnStudent;
    }

    @Override
    public StudentPDM getStudentDetail(StudentPDM student) throws UserNotFoundException, UserInfoErrorException {

        if(student == null
                || student.getStudentID() == null
                || student.getStudentID().length() != 10
                ){
            throw new UserInfoErrorException();
        }

        StudentPDM getStudent = studentRepository.findOne(student.getStudentID());

        if(getStudent==null){
            throw new UserNotFoundException();
        }

        StudentPDM returnStudent = new StudentPDM(getStudent.getStudentID(),getStudent.getPassword(),getStudent.getName(),getStudent.getGender(),getStudent.getClassName(),getStudent.getDepartment(),getStudent.getEnrolledAge(),getStudent.getEnrolledTime());
        returnStudent.setSelectedCourses(getStudent.getSelectedCourses());

        return returnStudent;
    }

    @Override
    public boolean updateStudent(StudentPDM student) throws UserNotFoundException, UserInfoErrorException {
        if(student == null
                || student.getPassword() == null
                || student.getPassword().equals("")
                || student.getName() == null
                || student.getName().equals("")
                || student.getStudentID() == null
                || student.getStudentID().length() != 10
                || student.getEnrolledAge() < 10
                || student.getEnrolledAge() > 50
                )
            throw new UserInfoErrorException();

        StudentPDM newStudent = studentRepository.findOne(student.getStudentID());
        if(newStudent == null) throw new UserNotFoundException();

        newStudent.setClassName(student.getClassName());
        newStudent.setEnrolledAge(student.getEnrolledAge());
        newStudent.setGender(student.getGender());
        newStudent.setEnrolledTime(student.getEnrolledTime());
        newStudent.setDepartment(student.getDepartment());
        newStudent.setPassword(student.getPassword());
        newStudent.setName(student.getName());

        studentRepository.save(newStudent);

        return true;
    }
}
