package guan.suns.service;

import guan.suns.exception.CourseExistedException;
import guan.suns.exception.CourseInfoErrorException;
import guan.suns.exception.CourseNotFoundException;
import guan.suns.exception.TeacherNotExistedException;
import guan.suns.model.CoursePDM;

/**
 * Created by lenovo on 2016/5/28.
 */
public interface CourseService {
    public boolean createCourse(CoursePDM course) throws CourseExistedException, CourseInfoErrorException, TeacherNotExistedException;

    public boolean updateCourse(CoursePDM course) throws CourseInfoErrorException, TeacherNotExistedException, CourseNotFoundException;

    public boolean deleteCourse(CoursePDM course) throws CourseInfoErrorException, CourseNotFoundException;

    public CoursePDM getCourseDetail(CoursePDM course) throws CourseInfoErrorException, CourseNotFoundException;
}
