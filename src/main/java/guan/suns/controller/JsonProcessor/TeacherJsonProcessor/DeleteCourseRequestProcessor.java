package guan.suns.controller.JsonProcessor.TeacherJsonProcessor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import guan.suns.exception.JsonErrorException;
import guan.suns.request.TeacherRequest.DeleteCourseRequest;

/**
 * Created by lenovo on 2016/5/30.
 */
public class DeleteCourseRequestProcessor {

    public DeleteCourseRequest getDeleteRequest(String rawJson) throws JsonErrorException
    {
        ObjectMapper mapper = new ObjectMapper();
        DeleteCourseRequest request = new DeleteCourseRequest();

        try{
            JsonNode root = mapper.readTree(rawJson);
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
