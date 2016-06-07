package guan.suns.controller.StatisticsProcessor;

import guan.suns.exception.QueryInfoError;
import guan.suns.model.CourseSelectionPDM;
import guan.suns.model.StudentPDM;
import guan.suns.request.GetUserDetailRequest;
import guan.suns.request.StudentRequest.GetStudentDetailsStatisticsRequest;
import guan.suns.response.StudentDetailResponse;
import guan.suns.response.StudentsStatisticsResponse;
import guan.suns.response.responseItem.StudentAttendClassItem;
import guan.suns.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/6/5.
 */
public class StudentsStatisticsProcessor {

    public StudentsStatisticsResponse getStudentsResponse(ArrayList<StudentPDM> students, GetStudentDetailsStatisticsRequest getStudentDetailsStatisticsRequest) throws QueryInfoError{

        if(students == null || getStudentDetailsStatisticsRequest == null) throw new QueryInfoError();

        StudentsStatisticsResponse studentsStatisticsResponse = new StudentsStatisticsResponse();
        ArrayList<StudentDetailResponse> studentsDetails = new ArrayList<>();
        studentsDetails.clear();

        Float totalScore = 0F;
        int cntCourse = 0;

        for(StudentPDM student : students){

            StudentDetailResponse studentDetailResponse = new StudentDetailResponse();

            studentDetailResponse.setClassName(student.getClassName());
            studentDetailResponse.setDepartment(student.getDepartment().ordinal());
            studentDetailResponse.setEnrolledAge(student.getEnrolledAge());
            studentDetailResponse.setEnrolledTime(student.getEnrolledTime());
            studentDetailResponse.setGender(student.getGender().ordinal());
            studentDetailResponse.setName(student.getName());
            studentDetailResponse.setStudentID(student.getStudentID());

            ArrayList<StudentAttendClassItem> classes = new ArrayList<>();
            classes.clear();
            boolean flag = false;
            if( getStudentDetailsStatisticsRequest.getCourseName()==null
                    && getStudentDetailsStatisticsRequest.getCourseID()==null)
                flag = true;

            for(CourseSelectionPDM courseItem : student.getSelectedCourses()){

                if( (getStudentDetailsStatisticsRequest.getCourseName()==null || getStudentDetailsStatisticsRequest.getCourseName().equals(courseItem.getCourseSelectionCompositeId().getCourseID().getCourseName()))
                    && (getStudentDetailsStatisticsRequest.getCourseID()==null || getStudentDetailsStatisticsRequest.getCourseID().equals(courseItem.getCourseSelectionCompositeId().getCourseID().getCourseID())) ) {

                    StudentAttendClassItem studentAttendClassItem = new StudentAttendClassItem();
                    studentAttendClassItem.setDepartment(courseItem.getCourseSelectionCompositeId().getCourseID().getTeacherID().getDepartment().ordinal());
                    studentAttendClassItem.setCourseID(courseItem.getCourseSelectionCompositeId().getCourseID().getCourseID());
                    studentAttendClassItem.setCourseName(courseItem.getCourseSelectionCompositeId().getCourseID().getCourseName());
                    studentAttendClassItem.setCredit(courseItem.getCourseSelectionCompositeId().getCourseID().getCredit());
                    studentAttendClassItem.setScore(courseItem.getScore());
                    studentAttendClassItem.setTeacherID(courseItem.getTeacherID().getTeacherID());
                    studentAttendClassItem.setTeacherName(courseItem.getTeacherID().getTeacherName());

                    classes.add(studentAttendClassItem);

                    if(courseItem.getScore()!=0){
                        cntCourse++;
                        totalScore = totalScore + courseItem.getScore();
                    }

                    flag = true;
                }
            }

            studentDetailResponse.setAttendClasses(classes);

            if(flag){
                studentsDetails.add(studentDetailResponse);
            }
        }

        studentsStatisticsResponse.setStudents(studentsDetails);
        if(cntCourse==0){
            studentsStatisticsResponse.setAvg(Float.valueOf(0));
        }
        else{
            studentsStatisticsResponse.setAvg(totalScore/cntCourse);
        }


        return studentsStatisticsResponse;
    }
}
