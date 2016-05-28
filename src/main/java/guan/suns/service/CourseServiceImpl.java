package guan.suns.service;

import guan.suns.exception.CourseExistedException;
import guan.suns.exception.CourseInfoErrorException;
import guan.suns.exception.TeacherNotExistedException;
import guan.suns.model.CoursePDM;
import guan.suns.model.TeacherPDM;
import guan.suns.repository.CourseRepository;
import guan.suns.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lenovo on 2016/5/28.
 */
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
}
