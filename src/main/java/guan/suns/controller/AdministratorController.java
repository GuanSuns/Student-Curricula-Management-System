package guan.suns.controller;

import guan.suns.controller.JsonProcessor.TeacherJsonProcessor.InsertScoreRequestProcessor;
import guan.suns.controller.mappingUrl.UrlConstant;
import guan.suns.exception.*;
import guan.suns.model.CoursePDM;
import guan.suns.model.CourseSelectionCompositeId;
import guan.suns.model.CourseSelectionPDM;
import guan.suns.model.StudentPDM;
import guan.suns.request.TeacherRequest.InsertScoreRequest;
import guan.suns.request.TeacherRequest.InsertScoreRequestItem;
import guan.suns.response.InsertScoreResponse;
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

/**
 * Created by lenovo on 2016/5/31.
 */

@RestController
@RequestMapping(value = UrlConstant.AdministratorRoot)
public class AdministratorController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = UrlConstant.AdministratorInsertScore , method = RequestMethod.POST)
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

                boolean isSuccess = courseService.administratorInsertScore(new CourseSelectionPDM(new CourseSelectionCompositeId(student,course),course.getTeacherID(),scoreItem.getScore(),scoreItem.getSelectYear()));

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
