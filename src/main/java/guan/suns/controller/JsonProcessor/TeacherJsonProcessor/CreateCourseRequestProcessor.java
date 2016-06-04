package guan.suns.controller.JsonProcessor.TeacherJsonProcessor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import guan.suns.basicClass.Grade;
import guan.suns.exception.JsonErrorException;
import guan.suns.request.TeacherRequest.CreateCourseRequest;

import java.sql.Timestamp;

/**
 * Created by lenovo on 2016/5/29.
 */
public class CreateCourseRequestProcessor {

    public CreateCourseRequest getCreateRequest(String rawJson) throws JsonErrorException
    {
        ObjectMapper mapper = new ObjectMapper();
        CreateCourseRequest request = new CreateCourseRequest();

        try{
            JsonNode root = mapper.readTree(rawJson);
            request.setTeacherID(root.path("teacherID").asText());
            request.setCourseName(root.path("courseName").asText());
            request.setSuitableGrade(Grade.values()[root.path("suitableGrade").asInt()]);
            request.setCourseID(root.path("courseID").asText());
            request.setCredit((float)root.path("credit").asDouble());

            String strDate = root.path("expiredDate").asText();
            if(strDate.equals("")) {
                request.setExpiredDate(null);
            }
            else{
                request.setExpiredDate(new Timestamp(root.path("expiredDate").asLong()));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new JsonErrorException();
        }

        return request;
    }

}
