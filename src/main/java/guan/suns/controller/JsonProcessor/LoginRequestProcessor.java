package guan.suns.controller.JsonProcessor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import guan.suns.exception.JsonErrorException;
import guan.suns.request.LoginRequest;

/**
 * Created by lenovo on 2016/5/23.
 */
public class LoginRequestProcessor {

    public LoginRequest getLoginRequest(String rawJson) throws JsonErrorException
    {
        ObjectMapper mapper = new ObjectMapper();
        LoginRequest request = new LoginRequest();

        try{
            JsonNode root = mapper.readTree(rawJson);
            request.setId(root.path("id").asText());
            request.setPassword(root.path("password").asText());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new JsonErrorException();
        }

        return request;
    }
}
