package guan.suns.controller.JsonProcessor.StudentJsonProcessor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import guan.suns.exception.JsonErrorException;
import guan.suns.request.StudentRequest.SelectCourseRequest;

import java.sql.Timestamp;

/**
 * Created by lenovo on 2016/5/31.
 */
public class SelectCourseRequestProcessor {

    public SelectCourseRequest getCreateRequest(String rawJson) throws JsonErrorException
    {
        ObjectMapper mapper = new ObjectMapper();
        SelectCourseRequest request = new SelectCourseRequest();

        try{
            JsonNode root = mapper.readTree(rawJson);
            request.setStudentID(root.path("studentID").asText());
            request.setCourseID(root.path("courseID").asText());

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new JsonErrorException();
        }

        return request;
    }

}
