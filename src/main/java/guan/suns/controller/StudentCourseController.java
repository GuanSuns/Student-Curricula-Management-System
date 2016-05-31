package guan.suns.controller;

import guan.suns.controller.JsonProcessor.StudentJsonProcessor.SelectCourseRequestProcessor;
import guan.suns.controller.JsonProcessor.TeacherJsonProcessor.InsertScoreRequestProcessor;
import guan.suns.controller.mappingUrl.UrlConstant;
import guan.suns.exception.*;
import guan.suns.model.CoursePDM;
import guan.suns.model.CourseSelectionCompositeId;
import guan.suns.model.CourseSelectionPDM;
import guan.suns.model.StudentPDM;
import guan.suns.request.StudentRequest.SelectCourseRequest;
import guan.suns.request.TeacherRequest.InsertScoreRequest;
import guan.suns.request.TeacherRequest.InsertScoreRequestItem;
import guan.suns.response.CommonResponse;
import guan.suns.response.InsertScoreResponse;
import guan.suns.response.ResponseProcessor.CommonResponseProcessor;
import guan.suns.response.ResponseProcessor.InsertScoreResponseProcessor;
import guan.suns.response.responseConstant.ResponseIntStatus;
import guan.suns.response.responseConstant.ResponseString;
import guan.suns.service.CourseService;
import guan.suns.service.StudentService;
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
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by lenovo on 2016/5/31.
 */

@RestController
@RequestMapping(value = UrlConstant.StudentCourseRoot)
public class StudentCourseController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;


    @RequestMapping(value = UrlConstant.SelectCourse , method = RequestMethod.POST)
    @ResponseBody
    public String selectCourse(HttpServletRequest httpServletRequest){

        InputStream inputStream = null;
        SelectCourseRequest selectCourseRequest = null ;
        SelectCourseRequestProcessor selectCourseRequestProcessor = new SelectCourseRequestProcessor();
        CommonResponse commonResponse = new CommonResponse();
        CommonResponseProcessor commonResponseProcessor = new CommonResponseProcessor();
        boolean isSuccess;

        try{
            inputStream = httpServletRequest.getInputStream();

            if(inputStream == null){
                commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
                commonResponse.setInfo(ResponseString.HttpServletRequestIOException);
                return commonResponseProcessor.generateResponse(commonResponse);
            }

            String requestBody = IOUtils.toString(inputStream,"utf-8");
            selectCourseRequest = selectCourseRequestProcessor.getRequest(requestBody);

            StudentPDM student = studentService.getStudentDetail(new StudentPDM(selectCourseRequest.getStudentID(),"","",null,"",null,null,null));
            CoursePDM course = courseService.getCourseDetail(new CoursePDM(selectCourseRequest.getCourseID(),"",null,null,null,null));

            isSuccess = courseService.selectCourse(new CourseSelectionPDM(new CourseSelectionCompositeId(student,course),course.getTeacherID(),0F,new Timestamp(new Date().getTime())));

        }
        catch (IOException ioException){

            ioException.printStackTrace();
            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.HttpServletRequestIOException);

            return commonResponseProcessor.generateResponse(commonResponse);
        }
        catch (JsonErrorException jsonErrorException){

            jsonErrorException.printStackTrace();

            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.JsonProcessingErrorException);

            return commonResponseProcessor.generateResponse(commonResponse);
        }
        catch (UserNotFoundException userNotFoundException){

            userNotFoundException.printStackTrace();

            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.CommonResponseUserNotFoundDescription);

            return commonResponseProcessor.generateResponse(commonResponse);

        }
        catch (UserInfoErrorException userInfoErrorException){

            userInfoErrorException.printStackTrace();

            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.UserInfoErrorException);

            return commonResponseProcessor.generateResponse(commonResponse);

        }
        catch (CourseInfoErrorException courseInfoErrorException){

            courseInfoErrorException.printStackTrace();

            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.CourseInfoErrorExceptionDescription);

            return commonResponseProcessor.generateResponse(commonResponse);
        }
        catch (CourseNotFoundException courseNotFoundException){

            courseNotFoundException.printStackTrace();

            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.CourseNotFoundExceptionDescription);

            return commonResponseProcessor.generateResponse(commonResponse);

        }
        catch (CourseSelectionInfoError courseSelectionInfoError){

            courseSelectionInfoError.printStackTrace();

            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.CourseSelectionInfoError);

            return commonResponseProcessor.generateResponse(commonResponse);

        }
        catch (CourseSelectionExistedException courseSelectionExistedException){

            courseSelectionExistedException.printStackTrace();

            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.CourseSelectionExisted);

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

}
