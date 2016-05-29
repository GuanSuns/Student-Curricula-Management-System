package guan.suns.controller.JsonProcessor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import guan.suns.exception.JsonErrorException;
import guan.suns.request.GetUserDetailRequest;

/**
 * Created by lenovo on 2016/5/29.
 */
public class GetUserDetailRequestProcessor {

    public GetUserDetailRequest getDeleteRequest(String rawJson) throws JsonErrorException
    {
        ObjectMapper mapper = new ObjectMapper();
        GetUserDetailRequest request = new GetUserDetailRequest();

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
