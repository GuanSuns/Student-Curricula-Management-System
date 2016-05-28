package guan.suns.controller;

import guan.suns.controller.JsonProcessor.DeleteUserRequestProcessor;
import guan.suns.controller.JsonProcessor.LoginRequestProcessor;
import guan.suns.controller.JsonProcessor.StudentJsonProcessor.CreateStudentRequestProcessor;
import guan.suns.controller.JsonProcessor.TeacherJsonProcessor.CreateTeacherRequestProcessor;
import guan.suns.controller.mappingUrl.UrlConstant;
import guan.suns.exception.JsonErrorException;
import guan.suns.exception.PasswordErrorException;
import guan.suns.exception.UserExistedException;
import guan.suns.exception.UserNotFoundException;
import guan.suns.model.StudentPDM;
import guan.suns.model.TeacherPDM;
import guan.suns.request.DeleteUserRequest;
import guan.suns.request.LoginRequest;
import guan.suns.request.TeacherRequest.CreateTeacherRequest;
import guan.suns.response.CommonResponse;
import guan.suns.response.ResponseProcessor.CommonResponseProcessor;
import guan.suns.response.responseConstant.ResponseIntStatus;
import guan.suns.response.responseConstant.ResponseString;
import guan.suns.service.TeacherService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lenovo on 2016/5/28.
 */

@RestController
@RequestMapping(value = UrlConstant.TeacherAccountRoot)
public class TeacherAccountController {
    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = UrlConstant.TeacherLogin , method = RequestMethod.POST)
    @ResponseBody
    public String login(HttpServletRequest httpServletRequest){

        InputStream inputStream = null;
        CommonResponse commonResponse = new CommonResponse();
        CommonResponseProcessor commonResponseProcessor = new CommonResponseProcessor();
        LoginRequest loginRequest = null;
        LoginRequestProcessor loginRequestProcessor = new LoginRequestProcessor();
        TeacherPDM loginTeacher = null;

        try{
            inputStream = httpServletRequest.getInputStream();

            if(inputStream == null){
                commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
                commonResponse.setInfo(ResponseString.HttpServletRequestIOException);
                return commonResponseProcessor.generateResponse(commonResponse);
            }

            String requestBody = IOUtils.toString(inputStream,"utf-8");
            loginRequest = loginRequestProcessor.getLoginRequest(requestBody);

            TeacherPDM teacher = new TeacherPDM();
            teacher.setTeacherID(loginRequest.getId());
            teacher.setPassword(loginRequest.getPassword());

            loginTeacher = teacherService.loginTeacher(teacher);

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

        if(loginTeacher == null){
            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.CommonResponseFailDescription);
        }
        else{
            commonResponse.setStatus(ResponseIntStatus.CommonResponseSuccessStatus);
            commonResponse.setInfo(ResponseString.CommonResponseSuccessDescription);
        }

        return commonResponseProcessor.generateResponse(commonResponse);
    }

    @RequestMapping(value = UrlConstant.TeacherCreate , method = RequestMethod.POST)
    @ResponseBody
    public String createTeacher(HttpServletRequest httpServletRequest){

        InputStream inputStream = null;
        CommonResponse commonResponse = new CommonResponse();
        CommonResponseProcessor commonResponseProcessor = new CommonResponseProcessor();
        CreateTeacherRequest createTeacherRequest = null;
        CreateTeacherRequestProcessor createTeacherRequestProcessor = new CreateTeacherRequestProcessor();
        TeacherPDM newTeacher = new TeacherPDM();
        boolean isSuccess;

        try{
            inputStream = httpServletRequest.getInputStream();

            if(inputStream == null){
                commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
                commonResponse.setInfo(ResponseString.HttpServletRequestIOException);
                return commonResponseProcessor.generateResponse(commonResponse);
            }

            String requestBody = IOUtils.toString(inputStream,"utf-8");
            createTeacherRequest = createTeacherRequestProcessor.getCreateRequest(requestBody);

            newTeacher.setDepartment(createTeacherRequest.getDepartment());
            newTeacher.setTeacherName(createTeacherRequest.getName());
            newTeacher.setPassword(createTeacherRequest.getPassword());
            newTeacher.setTeacherID(createTeacherRequest.getId());

            //System.out.println(newStudent);

            isSuccess = teacherService.createTeacher(newTeacher);
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

    @RequestMapping(value = UrlConstant.TeacherDelete, method = RequestMethod.POST)
    @ResponseBody
    public String deleteTeacher(HttpServletRequest httpServletRequest){

        InputStream inputStream = null;
        CommonResponse commonResponse = new CommonResponse();
        CommonResponseProcessor commonResponseProcessor = new CommonResponseProcessor();
        DeleteUserRequest deleteUserRequest = null;
        DeleteUserRequestProcessor deleteUserRequestProcessor = new DeleteUserRequestProcessor();
        TeacherPDM deleteTeacher = new TeacherPDM();
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

            deleteTeacher.setTeacherID(deleteUserRequest.getId());

            isSuccess = teacherService.deleteTeacher(deleteTeacher);

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
            commonResponse.setInfo(ResponseString.FailToDeleteTeacherDescription);
        }

        return commonResponseProcessor.generateResponse(commonResponse);
    }
}
