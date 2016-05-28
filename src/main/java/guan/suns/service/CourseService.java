package guan.suns.service;

import guan.suns.exception.CourseExistedException;
import guan.suns.exception.CourseInfoErrorException;
import guan.suns.exception.TeacherNotExistedException;
import guan.suns.model.CoursePDM;

/**
 * Created by lenovo on 2016/5/28.
 */
public interface CourseService {
    public boolean createCourse(CoursePDM course) throws CourseExistedException, CourseInfoErrorException, TeacherNotExistedException;
}
