package guan.suns.service;

import guan.suns.exception.CourseExistedException;
import guan.suns.exception.CourseInfoErrorException;
import guan.suns.exception.CourseNotFoundException;
import guan.suns.exception.TeacherNotExistedException;
import guan.suns.model.CoursePDM;
import guan.suns.model.TeacherPDM;
import guan.suns.repository.CourseRepository;
import guan.suns.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lenovo on 2016/5/28.
 */

@Service("courseService")
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public boolean createCourse(CoursePDM course) throws CourseExistedException, CourseInfoErrorException, TeacherNotExistedException {

        if(course == null
                || course.getTeacherID() == null
                || course.getCourseID() == null
                || course.getCourseID().equals("")
                || course.getCourseName() == null
                || course.getCourseName().equals("")
                ) {
            throw new CourseInfoErrorException();
        }

        CoursePDM newCourse = courseRepository.findOne(course.getCourseID());
        if(newCourse != null){
            throw new CourseExistedException();
        }

        TeacherPDM teacher = teacherRepository.findOne(course.getTeacherID().getTeacherID());
        if(teacher == null) {
            throw new TeacherNotExistedException();
        }

        courseRepository.save(course);

        return true;
    }

    @Override
    public boolean updateCourse(CoursePDM course) throws CourseInfoErrorException, TeacherNotExistedException, CourseNotFoundException {
        if(course == null
                || course.getTeacherID() == null
                || course.getCourseID() == null
                || course.getCourseID().equals("")
                || course.getCourseName() == null
                || course.getCourseName().equals("")
                ) {
            throw new CourseInfoErrorException();
        }

        CoursePDM newCourse = courseRepository.findOne(course.getCourseID());
        if(newCourse == null){
            throw new CourseNotFoundException();
        }

        TeacherPDM teacher = teacherRepository.findOne(course.getTeacherID().getTeacherID());
        if(teacher == null) {
            throw new TeacherNotExistedException();
        }

        newCourse.setExpiredDate(course.getExpiredDate());
        newCourse.setCourseName(course.getCourseName());
        newCourse.setCredit(course.getCredit());
        newCourse.setSuitableGrade(course.getSuitableGrade());
        newCourse.setTeacherID(teacher);

        courseRepository.save(newCourse);

        return true;
    }

    @Override
    public boolean deleteCourse(CoursePDM course) throws CourseInfoErrorException, CourseNotFoundException {
        if(course == null
                || course.getCourseID() == null
                || course.getCourseID().equals("")
                ) {
            throw new CourseInfoErrorException();
        }

        CoursePDM deleteCourse = courseRepository.findOne(course.getCourseID());
        if(deleteCourse == null){
            throw new CourseNotFoundException();
        }

        courseRepository.delete(deleteCourse);

        return true;
    }

    @Override
    public CoursePDM getCourseDetail(CoursePDM course) throws CourseInfoErrorException, CourseNotFoundException {

        if(course == null
                || course.getCourseID() == null
                || course.getCourseID().equals("")
                ) {
            throw new CourseInfoErrorException();
        }

        CoursePDM detailCourse = courseRepository.findOne(course.getCourseID());
        if(detailCourse == null){
            throw new CourseNotFoundException();
        }

        CoursePDM returnCourse = new CoursePDM(detailCourse.getCourseID(),detailCourse.getCourseName(),detailCourse.getTeacherID(),detailCourse.getCredit(),detailCourse.getExpiredDate(),detailCourse.getSuitableGrade());
        returnCourse.setSelectStudents(null);

        return returnCourse;
    }

}
