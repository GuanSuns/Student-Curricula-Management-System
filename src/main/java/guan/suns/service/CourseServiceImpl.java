package guan.suns.service;

import guan.suns.controller.StatisticsProcessor.CourseSelectionStatisticProcessor;
import guan.suns.exception.*;
import guan.suns.model.CoursePDM;
import guan.suns.model.CourseSelectionPDM;
import guan.suns.model.TeacherPDM;
import guan.suns.repository.CourseRepository;
import guan.suns.repository.CourseSelectionRepository;
import guan.suns.repository.TeacherRepository;
import guan.suns.request.GetCourseDetailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2016/5/28.
 */

@Service("courseService")
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private CourseSelectionRepository courseSelectionRepository;
    @Autowired
    private CourseSelectionStatisticProcessor courseSelectionStatisticProcessor;

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

        ArrayList<CourseSelectionPDM> courseSelectionPDMs = courseSelectionStatisticProcessor.getCourseSelectionCondition(new GetCourseDetailRequest(deleteCourse.getCourseID(),deleteCourse.getCourseName()));

        for(CourseSelectionPDM courseSelectionPDM : courseSelectionPDMs){
            if(courseSelectionPDM!=null){
                courseSelectionRepository.delete(courseSelectionPDM);
            }
        }

        courseRepository.delete(deleteCourse);

        return true;
    }

    @Override
    public CoursePDM getCourseDetail(CoursePDM course) throws CourseInfoErrorException, CourseNotFoundException {

        if(course == null
                || ( (course.getCourseID()==null || course.getCourseID().equals("")) &&  (course.getCourseName()==null || course.getCourseName().equals("")) )
                ) {
            throw new CourseInfoErrorException();
        }

        CoursePDM detailCourse;
        if(course.getCourseID() != null && !course.getCourseID().equals("")){

            detailCourse = courseRepository.findOne(course.getCourseID());
            if(detailCourse == null){
                throw new CourseNotFoundException();
            }
        }
        else{
            ArrayList<CoursePDM> coursePDMs = courseRepository.findByCourseName(course.getCourseName());
            if(coursePDMs==null || coursePDMs.isEmpty()) {
                throw new CourseNotFoundException();
            }
            detailCourse = coursePDMs.get(0);
        }

        CoursePDM returnCourse = new CoursePDM(detailCourse.getCourseID(),detailCourse.getCourseName(),detailCourse.getTeacherID(),detailCourse.getCredit(),detailCourse.getExpiredDate(),detailCourse.getSuitableGrade());
        returnCourse.setSelectStudents(null);

        return returnCourse;
    }

    @Override
    public boolean teacherInsertScore(CourseSelectionPDM courseSelection) throws CourseNotSelectedException, TeacherCannotModifyScoreException {

        if(courseSelection == null
                || courseSelection.getTeacherID() == null
                || courseSelection.getCourseSelectionCompositeId() == null
                || courseSelection.getCourseSelectionCompositeId().getCourseID() == null
                || courseSelection.getCourseSelectionCompositeId().getStudentID()== null
                ){
            throw new CourseNotSelectedException();
        }

        CourseSelectionPDM courseScore = courseSelectionRepository.findOne(courseSelection.getCourseSelectionCompositeId());
        if( courseScore == null ) {
            throw new CourseNotSelectedException();
        }
        /*
        if( courseScore.getScore() != 0) {
            throw new TeacherCannotModifyScoreException();
        }
        */

        courseScore.setScore(courseSelection.getScore());
        courseScore.setSelectYear(courseSelection.getSelectYear());

        courseSelectionRepository.save(courseScore);

        return true;
    }

    @Override
    public boolean administratorInsertScore(CourseSelectionPDM courseSelection) throws CourseNotSelectedException {
        if(courseSelection == null
                || courseSelection.getTeacherID() == null
                || courseSelection.getCourseSelectionCompositeId() == null
                || courseSelection.getCourseSelectionCompositeId().getCourseID() == null
                || courseSelection.getCourseSelectionCompositeId().getStudentID()== null
                ){
            throw new CourseNotSelectedException();
        }

        CourseSelectionPDM courseScore = courseSelectionRepository.findOne(courseSelection.getCourseSelectionCompositeId());
        if( courseScore == null ) {
            throw new CourseNotSelectedException();
        }

        courseScore.setScore(courseSelection.getScore());
        courseScore.setSelectYear(courseSelection.getSelectYear());

        courseSelectionRepository.save(courseScore);

        return true;
    }

    @Override
    public boolean selectCourse(CourseSelectionPDM courseSelection) throws CourseSelectionExistedException,CourseSelectionInfoError,StudentCanNotSelectCourseException, UserInfoErrorException {
        if(courseSelection == null
                || courseSelection.getTeacherID() == null
                || courseSelection.getCourseSelectionCompositeId() == null
                || courseSelection.getCourseSelectionCompositeId().getCourseID() == null
                || courseSelection.getCourseSelectionCompositeId().getStudentID()== null
                || courseSelection.getScore() != 0F
                ){
            throw new CourseSelectionInfoError();
        }

        CourseSelectionPDM courseScore = courseSelectionRepository.findByCourseSelectionCompositeIdStudentIDAndCourseSelectionCompositeIdCourseID(courseSelection.getCourseSelectionCompositeId().getStudentID(),courseSelection.getCourseSelectionCompositeId().getCourseID());
        if( courseScore != null ) {
            //System.out.println("Test");
            throw new CourseSelectionExistedException();
        }

        Date studentEnrollTime = courseSelection.getCourseSelectionCompositeId().getStudentID().getEnrolledTime();
        Calendar studentEnrollCalendar = Calendar.getInstance();
        studentEnrollCalendar.setTime(studentEnrollTime);
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(new Date());
        int studentEnrollYear = studentEnrollCalendar.get(Calendar.YEAR);
        int studentEnrollMonth = studentEnrollCalendar.get(Calendar.MONTH);
        int currentYear = currentCalendar.get(Calendar.YEAR);
        int currentMonth = currentCalendar.get(Calendar.MONTH) + 1;
        int intGrade = 0;

        //System.out.println("studentEnrollMonth:"+studentEnrollMonth+" currentMonth: "+currentMonth);

        if(courseSelection.getCourseSelectionCompositeId().getCourseID().getExpiredDate()!=null){
            Date courseExpiredDate = courseSelection.getCourseSelectionCompositeId().getCourseID().getExpiredDate();
            Calendar courseExpiredCalendar = Calendar.getInstance();
            courseExpiredCalendar.setTime(courseExpiredDate);

            if(currentCalendar.after(courseExpiredCalendar)){

                throw new StudentCanNotSelectCourseException();
            }
        }

        if(studentEnrollMonth < 9 ){
            studentEnrollYear--;
        }

        if(currentMonth < 9){
            intGrade = currentYear - studentEnrollYear - 1;
        }
        else{
            intGrade = currentYear - studentEnrollYear;
        }

        if(intGrade < 0) {
            throw new UserInfoErrorException();
        }

        if(intGrade < courseSelection.getCourseSelectionCompositeId().getCourseID().getSuitableGrade().ordinal()){
            throw new StudentCanNotSelectCourseException();
        }

        courseSelectionRepository.save(courseSelection);

        return true;
    }

    @Override
    public boolean dropCourse(CourseSelectionPDM courseSelection) throws CourseNotSelectedException, CourseSelectionInfoError {
        if(courseSelection == null
                || courseSelection.getTeacherID() == null
                || courseSelection.getCourseSelectionCompositeId() == null
                || courseSelection.getCourseSelectionCompositeId().getCourseID() == null
                || courseSelection.getCourseSelectionCompositeId().getStudentID()== null
                ){
            throw new CourseSelectionInfoError();
        }

        CourseSelectionPDM courseScore = courseSelectionRepository.findByCourseSelectionCompositeIdStudentIDAndCourseSelectionCompositeIdCourseID(courseSelection.getCourseSelectionCompositeId().getStudentID(),courseSelection.getCourseSelectionCompositeId().getCourseID());
        if( courseScore == null ) {
            throw new CourseNotSelectedException();
        }

        courseSelectionRepository.delete(courseScore);

        return true;
    }

    @Override
    public ArrayList<CoursePDM> getAllCourses() {
        List<CoursePDM> coursePDMs = courseRepository.findAll();
        ArrayList<CoursePDM> courses = new ArrayList<>();

        if(coursePDMs==null || coursePDMs.isEmpty()) return courses;

        courses.addAll(coursePDMs);

        return courses;
    }
}
