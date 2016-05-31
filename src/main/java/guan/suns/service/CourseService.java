package guan.suns.service;

import guan.suns.exception.*;
import guan.suns.model.CoursePDM;
import guan.suns.model.CourseSelectionPDM;

/**
 * Created by lenovo on 2016/5/28.
 */
public interface CourseService {
    public boolean createCourse(CoursePDM course) throws CourseExistedException, CourseInfoErrorException, TeacherNotExistedException;

    public boolean updateCourse(CoursePDM course) throws CourseInfoErrorException, TeacherNotExistedException, CourseNotFoundException;

    public boolean deleteCourse(CoursePDM course) throws CourseInfoErrorException, CourseNotFoundException;

    public CoursePDM getCourseDetail(CoursePDM course) throws CourseInfoErrorException, CourseNotFoundException;

    public boolean teacherInsertScore(CourseSelectionPDM courseSelection) throws CourseNotSelectedException, TeacherCannotModifyScoreException;

    public boolean administratorInsertScore(CourseSelectionPDM courseSelection) throws CourseNotSelectedException;

    public boolean selectCourse(CourseSelectionPDM courseSelection) throws CourseSelectionExistedException,CourseSelectionInfoError, StudentCanNotSelectCourseException;

    public boolean dropCourse(CourseSelectionPDM courseSelection) throws CourseNotSelectedException, CourseSelectionInfoError;
}
