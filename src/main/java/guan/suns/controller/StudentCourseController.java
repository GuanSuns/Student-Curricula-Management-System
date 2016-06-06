package guan.suns.controller;

import guan.suns.controller.JsonProcessor.StudentJsonProcessor.SelectOrDropCourseRequestProcessor;
import guan.suns.controller.JsonProcessor.StudentJsonProcessor.StudentDetailsStatisticsRequestProcessor;
import guan.suns.controller.StatisticsProcessor.StudentsStatisticsProcessor;
import guan.suns.controller.StatisticsProcessor.StudentsDetailQueryProcessor;
import guan.suns.controller.mappingUrl.UrlConstant;
import guan.suns.exception.*;
import guan.suns.model.CoursePDM;
import guan.suns.model.CourseSelectionCompositeId;
import guan.suns.model.CourseSelectionPDM;
import guan.suns.model.StudentPDM;
import guan.suns.request.StudentRequest.GetStudentDetailsStatisticsRequest;
import guan.suns.request.StudentRequest.SelectOrDropCourseRequest;
import guan.suns.response.CommonResponse;
import guan.suns.response.ResponseProcessor.CommonResponseProcessor;
import guan.suns.response.ResponseProcessor.StudentsStatisticsResponseProcessor;
import guan.suns.response.StudentDetailResponse;
import guan.suns.response.StudentsStatisticsResponse;
import guan.suns.response.responseConstant.ResponseIntStatus;
import guan.suns.response.responseConstant.ResponseString;
import guan.suns.service.CourseService;
import guan.suns.service.StudentService;
import guan.suns.service.TeacherService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by lenovo on 2016/5/31.
 */

@CrossOrigin
@RestController
@RequestMapping(value = UrlConstant.StudentCourseRoot)
public class StudentCourseController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentsDetailQueryProcessor studentsDetailQueryProcessor;

    @RequestMapping(value = UrlConstant.SelectCourse , method = RequestMethod.POST)
    @ResponseBody
    public String selectCourse(HttpServletRequest httpServletRequest){

        InputStream inputStream = null;
        SelectOrDropCourseRequest selectOrDropCourseRequest = null ;
        SelectOrDropCourseRequestProcessor selectOrDropCourseRequestProcessor = new SelectOrDropCourseRequestProcessor();
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
            selectOrDropCourseRequest = selectOrDropCourseRequestProcessor.getRequest(requestBody);

            StudentPDM student = studentService.getStudentDetail(new StudentPDM(selectOrDropCourseRequest.getStudentID(),"","",null,"",null,null,null));
            CoursePDM course = courseService.getCourseDetail(new CoursePDM(selectOrDropCourseRequest.getCourseID(),"",null,null,null,null));

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
        catch (StudentCanNotSelectCourseException studentCanNotSelectCourseException){
            studentCanNotSelectCourseException.printStackTrace();

            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.StudentCanNotSelectCourseDescription);

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

    @RequestMapping(value = UrlConstant.DropCourse , method = RequestMethod.POST)
    @ResponseBody
    public String dropCourse(HttpServletRequest httpServletRequest){

        InputStream inputStream = null;
        SelectOrDropCourseRequest selectOrDropCourseRequest = null ;
        SelectOrDropCourseRequestProcessor selectOrDropCourseRequestProcessor = new SelectOrDropCourseRequestProcessor();
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
            selectOrDropCourseRequest = selectOrDropCourseRequestProcessor.getRequest(requestBody);

            StudentPDM student = studentService.getStudentDetail(new StudentPDM(selectOrDropCourseRequest.getStudentID(),"","",null,"",null,null,null));
            CoursePDM course = courseService.getCourseDetail(new CoursePDM(selectOrDropCourseRequest.getCourseID(),"",null,null,null,null));

            isSuccess = courseService.dropCourse(new CourseSelectionPDM(new CourseSelectionCompositeId(student,course),course.getTeacherID(),0F,new Timestamp(new Date().getTime())));

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
        catch (CourseNotSelectedException courseNotSelectedException){

            courseNotSelectedException.printStackTrace();

            commonResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            commonResponse.setInfo(ResponseString.CourseNotSelectedDescription);

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

    @RequestMapping(value = UrlConstant.GetStudentDetailsStatistics, method = RequestMethod.POST)
    @ResponseBody
    public String getStudentDetailsStatistics(HttpServletRequest httpServletRequest){

        InputStream inputStream = null;
        StudentsStatisticsResponse studentsDetailResponse = new StudentsStatisticsResponse();
        StudentsStatisticsResponseProcessor studentsDetailsResponseProcessor = new StudentsStatisticsResponseProcessor();
        StudentDetailsStatisticsRequestProcessor studentsDetailsRequestProcessor = new StudentDetailsStatisticsRequestProcessor();
        GetStudentDetailsStatisticsRequest getStudentsDetailRequest;

        try{
            inputStream = httpServletRequest.getInputStream();

            if(inputStream == null){
                studentsDetailResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
                studentsDetailResponse.setInfo(ResponseString.HttpServletRequestIOException);
                return studentsDetailsResponseProcessor.generateResponse(studentsDetailResponse);
            }

            String requestBody = IOUtils.toString(inputStream,"utf-8");
            getStudentsDetailRequest = studentsDetailsRequestProcessor.getRequest(requestBody);

            ArrayList<StudentPDM> students = studentsDetailQueryProcessor.getStudentsMultiConditionSearch(getStudentsDetailRequest);
            studentsDetailResponse = new StudentsStatisticsProcessor().getStudentsResponse(students,getStudentsDetailRequest);
        }
        catch (IOException ioException){

            ioException.printStackTrace();
            studentsDetailResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            studentsDetailResponse.setInfo(ResponseString.HttpServletRequestIOException);

            return studentsDetailsResponseProcessor.generateResponse(studentsDetailResponse);
        }
        catch (JsonErrorException jsonErrorException){

            jsonErrorException.printStackTrace();
            studentsDetailResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            studentsDetailResponse.setInfo(ResponseString.JsonProcessingErrorException);

            return studentsDetailsResponseProcessor.generateResponse(studentsDetailResponse);
        }
        catch (QueryInfoError queryInfoError){
            queryInfoError.printStackTrace();

            studentsDetailResponse.setStatus(ResponseIntStatus.CommonResponseFailStatus);
            studentsDetailResponse.setInfo(ResponseString.GetStudentsDetailsByNameOrDepartmentOrClassNameQueryInfoError);

            return studentsDetailsResponseProcessor.generateResponse(studentsDetailResponse);
        }

        studentsDetailResponse.setStatus(ResponseIntStatus.CommonResponseSuccessStatus);
        studentsDetailResponse.setInfo(ResponseString.CommonResponseSuccessDescription);
        return studentsDetailsResponseProcessor.generateResponse(studentsDetailResponse);
    }

}
