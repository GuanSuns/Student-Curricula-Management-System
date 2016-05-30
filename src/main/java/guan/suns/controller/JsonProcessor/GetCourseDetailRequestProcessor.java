package guan.suns.controller.JsonProcessor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import guan.suns.exception.JsonErrorException;
import guan.suns.request.GetCourseDetailRequest;

/**
 * Created by lenovo on 2016/5/30.
 */
public class GetCourseDetailRequestProcessor {

    public GetCourseDetailRequest getRequest(String rawJson) throws JsonErrorException
    {
        ObjectMapper mapper = new ObjectMapper();
        GetCourseDetailRequest request = new GetCourseDetailRequest();

        try{
            JsonNode root = mapper.readTree(rawJson);
            request.setId(root.path("id").asText());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new JsonErrorException();
        }

        return request;
    }

}
