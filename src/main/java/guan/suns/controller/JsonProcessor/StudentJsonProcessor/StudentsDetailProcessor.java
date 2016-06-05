package guan.suns.controller.JsonProcessor.StudentJsonProcessor;

import guan.suns.model.CourseSelectionPDM;
import guan.suns.model.StudentPDM;
import guan.suns.request.GetUserDetailRequest;
import guan.suns.response.StudentDetailResponse;
import guan.suns.response.responseItem.StudentAttendClassItem;
import guan.suns.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/6/5.
 */
public class StudentsDetailProcessor {

    public ArrayList<StudentDetailResponse> getStudentsResponse(ArrayList<StudentPDM> students){
        if(students == null) return null;

        ArrayList<StudentDetailResponse> responses = new ArrayList<>();
        responses.clear();

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

            for(CourseSelectionPDM courseItem : student.getSelectedCourses()){

                StudentAttendClassItem studentAttendClassItem = new StudentAttendClassItem();
                studentAttendClassItem.setDepartment(courseItem.getCourseSelectionCompositeId().getCourseID().getTeacherID().getDepartment().ordinal());
                studentAttendClassItem.setCourseID(courseItem.getCourseSelectionCompositeId().getCourseID().getCourseID());
                studentAttendClassItem.setCourseName(courseItem.getCourseSelectionCompositeId().getCourseID().getCourseName());
                studentAttendClassItem.setCredit(courseItem.getCourseSelectionCompositeId().getCourseID().getCredit());
                studentAttendClassItem.setScore(courseItem.getScore());
                studentAttendClassItem.setTeacherID(courseItem.getTeacherID().getTeacherID());
                studentAttendClassItem.setTeacherName(courseItem.getTeacherID().getTeacherName());

                classes.add(studentAttendClassItem);
            }

            studentDetailResponse.setAttendClasses(classes);
            responses.add(studentDetailResponse);
        }

        return responses;
    }
}
