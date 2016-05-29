package guan.suns.controller;

import guan.suns.controller.JsonProcessor.DeleteUserRequestProcessor;
import guan.suns.controller.JsonProcessor.GetUserDetailRequestProcessor;
import guan.suns.controller.JsonProcessor.LoginRequestProcessor;
import guan.suns.controller.JsonProcessor.StudentJsonProcessor.CreateStudentRequestProcessor;
import guan.suns.controller.mappingUrl.UrlConstant;
import guan.suns.exception.*;
import guan.suns.model.CourseSelectionPDM;
import guan.suns.model.StudentPDM;
import guan.suns.request.DeleteUserRequest;
import guan.suns.request.GetUserDetailRequest;
import guan.suns.request.LoginRequest;
import guan.suns.request.StudentRequest.CreateStudentRequest;
import guan.suns.response.CommonResponse;
import guan.suns.response.ResponseProcessor.CommonResponseProcessor;
import guan.suns.response.ResponseProcessor.StudentDetailResponseProcessor;
import guan.suns.response.StudentDetailResponse;
import guan.suns.response.responseConstant.*;
import guan.suns.response.responseItem.StudentAttendClassItem;
import guan.suns.service.StudentService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by lenovo on 2016/5/23.
 */

@RestController
@RequestMapping(value = UrlConstant.StudentAccountRoot)
public class StudentAccountController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = UrlConstant.StudentLogin , method = RequestMethod.POST)
    @ResponseBody
    public String login(HttpServletRequest httpServletRequest){

        InputStream inputStream = null;
        CommonResponse commonResponse = new CommonResponse();
        CommonResponseProcessor commonResponseProcessor = new CommonResponseProcessor();
        LoginRequest loginRequest = null;
        LoginRequestProcessor loginRequestProcessor = new LoginRequestProcessor();
        StudentPDM loginStudent = null;

        try{
            inputStream = httpServletRequest.getInputStream();

            if(inputStream == null){
                commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
                commonResponse.setInfo(ResponseString.HttpServletRequestIOException);
                return commonResponseProcessor.generateResponse(commonResponse);
            }

            String requestBody = IOUtils.toString(inputStream,"utf-8");
            loginRequest = loginRequestProcessor.getLoginRequest(requestBody);

            StudentPDM student = new StudentPDM();
            student.setStudentID(loginRequest.getId());
            student.setPassword(loginRequest.getPassword());

            loginStudent = studentService.loginStudent(student);

        }
        catch (IOException ioException){
            ioException.printStackTrace();
            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.HttpServletRequestIOException);

            return commonResponseProcessor.generateResponse(commonResponse);
        }
        catch (JsonErrorException jsonErrorException){
            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.JsonProcessingErrorException);

            return commonResponseProcessor.generateResponse(commonResponse);
        }
        catch (UserNotFoundException userNotFoundException){
            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.LoginUserNotFindException);

            return commonResponseProcessor.generateResponse(commonResponse);
        }
        catch (PasswordErrorException passwordErrorException){
            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.LoginPasswordErrorException);

            return commonResponseProcessor.generateResponse(commonResponse);
        }

        if(loginStudent==null){
            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.CommonResponseFailDescription);
        }
        else{
            commonResponse.setStatus(ResponseIntStatus.CommonResponseSuccessStatus);
            commonResponse.setInfo(ResponseString.CommonResponseSuccessDescription);
        }

        return commonResponseProcessor.generateResponse(commonResponse);
    }

    @RequestMapping(value = UrlConstant.StudentCreate , method = RequestMethod.POST)
    @ResponseBody
    public String createStudent(HttpServletRequest httpServletRequest){

        InputStream inputStream = null;
        CommonResponse commonResponse = new CommonResponse();
        CommonResponseProcessor commonResponseProcessor = new CommonResponseProcessor();
        CreateStudentRequest createStudentRequest = null;
        CreateStudentRequestProcessor createStudentRequestProcessor = new CreateStudentRequestProcessor();
        StudentPDM newStudent = new StudentPDM();
        boolean isSuccess;

        try{
            inputStream = httpServletRequest.getInputStream();

            if(inputStream == null){
                commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
                commonResponse.setInfo(ResponseString.HttpServletRequestIOException);
                return commonResponseProcessor.generateResponse(commonResponse);
            }

            String requestBody = IOUtils.toString(inputStream,"utf-8");
            createStudentRequest = createStudentRequestProcessor.getCreateRequest(requestBody);

            newStudent.setClassName(createStudentRequest.getClassName());
            newStudent.setDepartment(createStudentRequest.getDepartment());
            newStudent.setName(createStudentRequest.getName());
            newStudent.setPassword(createStudentRequest.getPassword());
            newStudent.setEnrolledTime(createStudentRequest.getEnrolledTime());
            newStudent.setEnrolledAge(createStudentRequest.getEnrolledAge());
            newStudent.setStudentID(createStudentRequest.getId());
            newStudent.setGender(createStudentRequest.getGender());

            //System.out.println(newStudent);

            isSuccess = studentService.createStudent(newStudent);

        }
        catch (IOException ioException){
            ioException.printStackTrace();
            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.HttpServletRequestIOException);

            return commonResponseProcessor.generateResponse(commonResponse);
        }
        catch (JsonErrorException jsonErrorException){
            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.JsonProcessingErrorException);

            return commonResponseProcessor.generateResponse(commonResponse);
        }
        catch (UserExistedException userExistedException){
            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.CreateUserExistedException);

            return commonResponseProcessor.generateResponse(commonResponse);
        }
        catch (UserInfoErrorException userInfoErrorException){
            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.UserInfoErrorException);

            return commonResponseProcessor.generateResponse(commonResponse);
        }

        if(isSuccess){
            commonResponse.setStatus(ResponseIntStatus.CommonResponseSuccessStatus);
            commonResponse.setInfo(ResponseString.CommonResponseSuccessDescription);
        }
        else{
            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.CommonResponseFailDescription);
        }

        return commonResponseProcessor.generateResponse(commonResponse);
    }

    @RequestMapping(value = UrlConstant.StudentUpdate , method = RequestMethod.POST)
    @ResponseBody
    public String updateStudent(HttpServletRequest httpServletRequest){

        InputStream inputStream = null;
        CommonResponse commonResponse = new CommonResponse();
        CommonResponseProcessor commonResponseProcessor = new CommonResponseProcessor();
        CreateStudentRequest createStudentRequest = null;
        CreateStudentRequestProcessor createStudentRequestProcessor = new CreateStudentRequestProcessor();
        StudentPDM newStudent = new StudentPDM();
        boolean isSuccess;

        try{
            inputStream = httpServletRequest.getInputStream();

            if(inputStream == null){
                commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
                commonResponse.setInfo(ResponseString.HttpServletRequestIOException);
                return commonResponseProcessor.generateResponse(commonResponse);
            }

            String requestBody = IOUtils.toString(inputStream,"utf-8");
            createStudentRequest = createStudentRequestProcessor.getCreateRequest(requestBody);

            newStudent.setClassName(createStudentRequest.getClassName());
            newStudent.setDepartment(createStudentRequest.getDepartment());
            newStudent.setName(createStudentRequest.getName());
            newStudent.setPassword(createStudentRequest.getPassword());
            newStudent.setEnrolledTime(createStudentRequest.getEnrolledTime());
            newStudent.setEnrolledAge(createStudentRequest.getEnrolledAge());
            newStudent.setStudentID(createStudentRequest.getId());
            newStudent.setGender(createStudentRequest.getGender());

            isSuccess = studentService.updateStudent(newStudent);

        }
        catch (IOException ioException){
            ioException.printStackTrace();
            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.HttpServletRequestIOException);

            return commonResponseProcessor.generateResponse(commonResponse);
        }
        catch (JsonErrorException jsonErrorException){
            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.JsonProcessingErrorException);

            return commonResponseProcessor.generateResponse(commonResponse);
        }
        catch (UserInfoErrorException userInfoErrorException){
            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.UserInfoErrorException);

            return commonResponseProcessor.generateResponse(commonResponse);
        }
        catch (UserNotFoundException userNotFoundException){
            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.NotFoundUpdateUserDescription);

            return commonResponseProcessor.generateResponse(commonResponse);
        }

        if(isSuccess){
            commonResponse.setStatus(ResponseIntStatus.CommonResponseSuccessStatus);
            commonResponse.setInfo(ResponseString.CommonResponseSuccessDescription);
        }
        else{
            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.CommonResponseFailDescription);
        }

        return commonResponseProcessor.generateResponse(commonResponse);
    }

    @RequestMapping(value = UrlConstant.StudentDelete, method = RequestMethod.POST)
    @ResponseBody
    public String deleteStudent(HttpServletRequest httpServletRequest){

        InputStream inputStream = null;
        CommonResponse commonResponse = new CommonResponse();
        CommonResponseProcessor commonResponseProcessor = new CommonResponseProcessor();
        DeleteUserRequest deleteUserRequest = null;
        DeleteUserRequestProcessor deleteUserRequestProcessor = new DeleteUserRequestProcessor();
        StudentPDM deleteStudent = new StudentPDM();
        boolean isSuccess;

        try{
            inputStream = httpServletRequest.getInputStream();

            if(inputStream == null){
                commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
                commonResponse.setInfo(ResponseString.HttpServletRequestIOException);
                return commonResponseProcessor.generateResponse(commonResponse);
            }

            String requestBody = IOUtils.toString(inputStream,"utf-8");
            deleteUserRequest = deleteUserRequestProcessor.getDeleteRequest(requestBody);

            deleteStudent.setStudentID(deleteUserRequest.getId());

            isSuccess = studentService.deleteStudent(deleteStudent);

        }
        catch (IOException ioException){
            ioException.printStackTrace();
            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.HttpServletRequestIOException);

            return commonResponseProcessor.generateResponse(commonResponse);
        }
        catch (JsonErrorException jsonErrorException){
            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.JsonProcessingErrorException);

            return commonResponseProcessor.generateResponse(commonResponse);
        }
        catch (UserNotFoundException userNotFoundException){
            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.DeleteUserNotFoundException);

            return commonResponseProcessor.generateResponse(commonResponse);
        }

        if(isSuccess){
            commonResponse.setStatus(ResponseIntStatus.CommonResponseSuccessStatus);
            commonResponse.setInfo(ResponseString.CommonResponseSuccessDescription);
        }
        else{
            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.FailToDeleteStudentDescription);
        }

        return commonResponseProcessor.generateResponse(commonResponse);
    }

    @RequestMapping(value = UrlConstant.GetStudentDetail, method = RequestMethod.POST)
    @ResponseBody
    public String getStudentDetail(HttpServletRequest httpServletRequest){

        InputStream inputStream = null;
        StudentDetailResponse studentDetailResponse = new StudentDetailResponse();
        StudentDetailResponseProcessor studentDetailResponseProcessor = new StudentDetailResponseProcessor();
        GetUserDetailRequest getUserDetailRequest = null;
        GetUserDetailRequestProcessor getUserDetailRequestProcessor = new GetUserDetailRequestProcessor();
        StudentPDM student = null;

        try{
            inputStream = httpServletRequest.getInputStream();

            if(inputStream == null){
                studentDetailResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
                studentDetailResponse.setInfo(ResponseString.HttpServletRequestIOException);
                return studentDetailResponseProcessor.generateResponse(studentDetailResponse);
            }

            String requestBody = IOUtils.toString(inputStream,"utf-8");
            getUserDetailRequest = getUserDetailRequestProcessor.getRequest(requestBody);

            student = studentService.getStudentDetail(new StudentPDM(getUserDetailRequest.getId(),"","",null,"",null,null,null));

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

        }
        catch (IOException ioException){

            ioException.printStackTrace();
            studentDetailResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            studentDetailResponse.setInfo(ResponseString.HttpServletRequestIOException);

            return studentDetailResponseProcessor.generateResponse(studentDetailResponse);
        }
        catch (JsonErrorException jsonErrorException){
            jsonErrorException.printStackTrace();

            studentDetailResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            studentDetailResponse.setInfo(ResponseString.JsonProcessingErrorException);

            return studentDetailResponseProcessor.generateResponse(studentDetailResponse);
        }
        catch (UserNotFoundException userNotFoundException){
            studentDetailResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            studentDetailResponse.setInfo(ResponseString.CommonResponseUserNotFoundDescription);

            return studentDetailResponseProcessor.generateResponse(studentDetailResponse);
        }


        studentDetailResponse.setStatus(ResponseIntStatus.CommonResponseSuccessStatus);
        studentDetailResponse.setInfo(ResponseString.CommonResponseSuccessDescription);
        return studentDetailResponseProcessor.generateResponse(studentDetailResponse);
    }

}