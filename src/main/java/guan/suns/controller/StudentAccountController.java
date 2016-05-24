package guan.suns.controller;

import guan.suns.controller.JsonProcessor.DeleteUserRequestProcessor;
import guan.suns.controller.JsonProcessor.LoginRequestProcessor;
import guan.suns.controller.JsonProcessor.StudentJsonProcessor.CreateStudentRequestProcessor;
import guan.suns.controller.mappingUrl.UrlConstant;
import guan.suns.exception.JsonErrorException;
import guan.suns.exception.PasswordErrorException;
import guan.suns.exception.UserExistedException;
import guan.suns.exception.UserNotFoundException;
import guan.suns.model.StudentPDM;
import guan.suns.request.DeleteUserRequest;
import guan.suns.request.LoginRequest;
import guan.suns.request.StudentRequest.CreateStudentRequest;
import guan.suns.response.CommonResponse;
import guan.suns.response.ResponseProcessor.CommonResponseProcessor;
import guan.suns.response.responseConstant.*;
import guan.suns.service.StudentService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

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

        if(isSuccess){
            commonResponse.setStatus(ResponseIntStatus.CommonResponseSuccessStatus);
            commonResponse.setInfo(ResponseString.CommonResponseSuccessDescription);
        }
        else{
            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.UserInfoErrorException);
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

}