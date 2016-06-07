package guan.suns.controller.JsonProcessor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import guan.suns.exception.JsonErrorException;
import guan.suns.request.GetUserDetailRequest;

/**
 * Created by lenovo on 2016/5/29.
 */
public class GetUserDetailRequestProcessor {

    public GetUserDetailRequest getRequest(String rawJson) throws JsonErrorException
    {
        ObjectMapper mapper = new ObjectMapper();
        GetUserDetailRequest request = new GetUserDetailRequest();

        try{
            JsonNode root = mapper.readTree(rawJson);

            String str = root.path("id").asText();
            if(str!=null && !str.equals("")){
                request.setId(str);
            }
            else{
                request.setId(null);
            }

            str = root.path("name").asText();
            if(str!=null && !str.equals("")){
                request.setName(str);
            }
            else{
                request.setName(null);
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
