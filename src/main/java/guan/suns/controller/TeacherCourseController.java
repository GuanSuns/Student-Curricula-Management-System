package guan.suns.controller;

import guan.suns.controller.JsonProcessor.GetCourseDetailRequestProcessor;
import guan.suns.controller.JsonProcessor.TeacherJsonProcessor.CreateCourseRequestProcessor;
import guan.suns.controller.JsonProcessor.TeacherJsonProcessor.DeleteCourseRequestProcessor;
import guan.suns.controller.JsonProcessor.TeacherJsonProcessor.InsertScoreRequestProcessor;
import guan.suns.controller.mappingUrl.UrlConstant;
import guan.suns.exception.*;
import guan.suns.model.*;
import guan.suns.repository.TeacherRepository;
import guan.suns.request.GetCourseDetailRequest;
import guan.suns.request.TeacherRequest.CreateCourseRequest;
import guan.suns.request.TeacherRequest.DeleteCourseRequest;
import guan.suns.request.TeacherRequest.InsertScoreRequest;
import guan.suns.request.TeacherRequest.InsertScoreRequestItem;
import guan.suns.response.CommonResponse;
import guan.suns.response.CourseDetailResponse;
import guan.suns.response.InsertScoreResponse;
import guan.suns.response.ResponseProcessor.CommonResponseProcessor;
import guan.suns.response.ResponseProcessor.CourseDetailResponseProcessor;
import guan.suns.response.ResponseProcessor.InsertScoreResponseProcessor;
import guan.suns.response.responseConstant.ResponseIntStatus;
import guan.suns.response.responseConstant.ResponseString;
import guan.suns.response.responseItem.CourseDetailItem;
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
    @Autowired
    private StudentService studentService;

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

    @RequestMapping(value = UrlConstant.TeacherDeleteCourse , method = RequestMethod.POST)
    @ResponseBody
    public String deleteCourse(HttpServletRequest httpServletRequest){

        InputStream inputStream = null;
        DeleteCourseRequest deleteCourseRequest = null;
        DeleteCourseRequestProcessor deleteCourseRequestProcessor = new DeleteCourseRequestProcessor();
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
            deleteCourseRequest = deleteCourseRequestProcessor.getDeleteRequest(requestBody);

            isSuccess = courseService.deleteCourse(new CoursePDM(deleteCourseRequest.getCourseID(),"",null,null,null,null));
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

    @RequestMapping(value = UrlConstant.TeacherUpdateCourse , method = RequestMethod.POST)
    @ResponseBody
    public String updateCourse(HttpServletRequest httpServletRequest){

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

            isSuccess = courseService.updateCourse(newCourse);
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
        catch (CourseNotFoundException courseNotFoundException) {

            courseNotFoundException.printStackTrace();

            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.CourseNotFoundExceptionDescription);

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

    @RequestMapping(value = UrlConstant.GetCourseDetail , method = RequestMethod.POST)
    @ResponseBody
    public String getCourseDetail(HttpServletRequest httpServletRequest){

        InputStream inputStream = null;
        GetCourseDetailRequest getCourseDetailRequest = null;
        GetCourseDetailRequestProcessor getCourseDetailRequestProcessor = new GetCourseDetailRequestProcessor();
        CourseDetailResponse courseDetailResponse = new CourseDetailResponse();
        CourseDetailResponseProcessor courseDetailResponseProcessor = new CourseDetailResponseProcessor();
        CoursePDM course = null;

        try{
            inputStream = httpServletRequest.getInputStream();

            if(inputStream == null){
                courseDetailResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
                courseDetailResponse.setInfo(ResponseString.HttpServletRequestIOException);
                return courseDetailResponseProcessor.generateResponse(courseDetailResponse);
            }

            String requestBody = IOUtils.toString(inputStream,"utf-8");
            getCourseDetailRequest = getCourseDetailRequestProcessor.getRequest(requestBody);

            course = courseService.getCourseDetail(new CoursePDM(getCourseDetailRequest.getId(),"",null,null,null,null));

            CourseDetailItem courseDetailItem = new CourseDetailItem(course.getCourseID(),course.getCourseName(),course.getTeacherID().getTeacherID(),course.getTeacherID().getTeacherName(),course.getExpiredDate(),course.getSuitableGrade().ordinal());
            courseDetailResponse.setDetail(courseDetailItem);

        }
        catch (IOException ioException){

            ioException.printStackTrace();
            courseDetailResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            courseDetailResponse.setInfo(ResponseString.HttpServletRequestIOException);

            return courseDetailResponseProcessor.generateResponse(courseDetailResponse);
        }
        catch (JsonErrorException jsonErrorException){

            jsonErrorException.printStackTrace();

            courseDetailResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            courseDetailResponse.setInfo(ResponseString.JsonProcessingErrorException);

            return courseDetailResponseProcessor.generateResponse(courseDetailResponse);
        }
        catch (CourseNotFoundException courseNotFoundException){

            courseNotFoundException.printStackTrace();

            courseDetailResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            courseDetailResponse.setInfo(ResponseString.CourseNotFoundExceptionDescription);

            return courseDetailResponseProcessor.generateResponse(courseDetailResponse);

        }
        catch (CourseInfoErrorException courseInfoErrorException){

            courseInfoErrorException.printStackTrace();

            courseDetailResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            courseDetailResponse.setInfo(ResponseString.CourseInfoErrorExceptionDescription);

            return courseDetailResponseProcessor.generateResponse(courseDetailResponse);

        }

        courseDetailResponse.setStatus(ResponseIntStatus.CommonResponseSuccessStatus);
        courseDetailResponse.setInfo(ResponseString.CommonResponseSuccessDescription);
        return courseDetailResponseProcessor.generateResponse(courseDetailResponse);
    }

    @RequestMapping(value = UrlConstant.InsertScore , method = RequestMethod.POST)
    @ResponseBody
    public String insertScore(HttpServletRequest httpServletRequest){

        InputStream inputStream = null;
        InsertScoreRequest insertScoreRequest = null ;
        InsertScoreRequestProcessor insertScoreRequestProcessor = new InsertScoreRequestProcessor();
        InsertScoreResponse insertScoreResponse = new InsertScoreResponse();
        InsertScoreResponseProcessor insertScoreResponseProcessor = new InsertScoreResponseProcessor();

        try{
            inputStream = httpServletRequest.getInputStream();

            if(inputStream == null){
                insertScoreResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
                insertScoreResponse.setInfo(ResponseString.HttpServletRequestIOException);
                return insertScoreResponseProcessor.generateResponse(insertScoreResponse);
            }

            String requestBody = IOUtils.toString(inputStream,"utf-8");
            insertScoreRequest = insertScoreRequestProcessor.getRequest(requestBody);

        }
        catch (IOException ioException){

            ioException.printStackTrace();
            insertScoreResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            insertScoreResponse.setInfo(ResponseString.HttpServletRequestIOException);

            return insertScoreResponseProcessor.generateResponse(insertScoreResponse);
        }
        catch (JsonErrorException jsonErrorException){

            jsonErrorException.printStackTrace();

            insertScoreResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            insertScoreResponse.setInfo(ResponseString.JsonProcessingErrorException);

            return insertScoreResponseProcessor.generateResponse(insertScoreResponse);
        }

        int cntSize = insertScoreRequest.getScores().size();
        int cntSuccess = 0;
        for(int i=0; i < insertScoreRequest.getScores().size(); i++){

            try{
                InsertScoreRequestItem scoreItem = insertScoreRequest.getScores().get(i);
                StudentPDM student = studentService.getStudentDetail(new StudentPDM(scoreItem.getStudentID(),"","",null,"",null,null,null));
                CoursePDM course = courseService.getCourseDetail(new CoursePDM(scoreItem.getCourseID(),"",null,null,null,null));

                boolean isSuccess = courseService.insertScore(new CourseSelectionPDM(new CourseSelectionCompositeId(student,course),course.getTeacherID(),scoreItem.getScore(),scoreItem.getSelectYear()));

                if(isSuccess) cntSuccess++;

            }
            catch (UserNotFoundException userNotFoundException){
                userNotFoundException.printStackTrace();
            }
            catch (UserInfoErrorException userInfoErrorException){
                userInfoErrorException.printStackTrace();
            }
            catch (CourseNotFoundException courseNotFoundException){
                courseNotFoundException.printStackTrace();
            }
            catch (CourseInfoErrorException courseInfoErrorException){
                courseInfoErrorException.printStackTrace();
            }
            catch (CourseNotSelectedException courseNotSelectedException){
                courseNotSelectedException.printStackTrace();
            }
        }

        insertScoreResponse.setSuccessCnt(cntSuccess);
        insertScoreResponse.setDataSize(cntSize);
        insertScoreResponse.setStatus(ResponseIntStatus.CommonResponseSuccessStatus);
        insertScoreResponse.setInfo(ResponseString.CommonResponseSuccessDescription);
        return insertScoreResponseProcessor.generateResponse(insertScoreResponse);
    }

}
