package guan.suns.controller;

import guan.suns.controller.JsonProcessor.TeacherJsonProcessor.CreateCourseRequestProcessor;
import guan.suns.controller.mappingUrl.UrlConstant;
import guan.suns.exception.*;
import guan.suns.model.CoursePDM;
import guan.suns.model.TeacherPDM;
import guan.suns.repository.TeacherRepository;
import guan.suns.request.TeacherRequest.CreateCourseRequest;
import guan.suns.response.CommonResponse;
import guan.suns.response.ResponseProcessor.CommonResponseProcessor;
import guan.suns.response.responseConstant.ResponseIntStatus;
import guan.suns.response.responseConstant.ResponseString;
import guan.suns.service.CourseService;
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
 * Created by lenovo on 2016/5/29.
 */
@RestController
@RequestMapping(value = UrlConstant.TeacherCourseRoot)
public class TeacherCourseController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;

    @RequestMapping(value = UrlConstant.TeacherCreateCourse , method = RequestMethod.POST)
    @ResponseBody
    public String createCourse(HttpServletRequest httpServletRequest){

        InputStream inputStream = null;
        CreateCourseRequest createCourseRequest = null;
        CreateCourseRequestProcessor createCourseRequestProcessor = new CreateCourseRequestProcessor();
        CommonResponse commonResponse = new CommonResponse();
        CommonResponseProcessor commonResponseProcessor = new CommonResponseProcessor();
        CoursePDM newCourse = null;
        boolean isSuccess;

        try{
            inputStream = httpServletRequest.getInputStream();

            if(inputStream == null){
                commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
                commonResponse.setInfo(ResponseString.HttpServletRequestIOException);
                return commonResponseProcessor.generateResponse(commonResponse);
            }

            String requestBody = IOUtils.toString(inputStream,"utf-8");
            createCourseRequest = createCourseRequestProcessor.getCreateRequest(requestBody);

            TeacherPDM teacher = teacherService.getTeacherDetail(new TeacherPDM(createCourseRequest.getTeacherID(),"",null,""));
            newCourse = new CoursePDM(createCourseRequest.getCourseID(),createCourseRequest.getCourseName(),teacher,createCourseRequest.getCredit(),createCourseRequest.getExpiredDate(),createCourseRequest.getSuitableGrade());

            isSuccess = courseService.createCourse(newCourse);
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
        catch(UserInfoErrorException userInfoErrorException){

            userInfoErrorException.printStackTrace();

            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.UserInfoErrorException);

            return commonResponseProcessor.generateResponse(commonResponse);
        }
        catch(UserNotFoundException userNotFoundException){

            userNotFoundException.printStackTrace();

            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.CommonResponseUserNotFoundDescription);

            return commonResponseProcessor.generateResponse(commonResponse);

        }
        catch (CourseExistedException courseExistedException){

            courseExistedException.printStackTrace();

            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.CourseExistedExceptionDescription);

            return commonResponseProcessor.generateResponse(commonResponse);
        }
        catch (TeacherNotExistedException teacherNotFoundException) {

            teacherNotFoundException.printStackTrace();

            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.CourseTeacherNotFoundExceptionDescription);

            return commonResponseProcessor.generateResponse(commonResponse);

        }
        catch (CourseInfoErrorException courseInfoErrorException){

            courseInfoErrorException.printStackTrace();

            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.CourseInfoErrorExceptionDescription);

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
