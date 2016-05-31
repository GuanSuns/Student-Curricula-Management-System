package guan.suns.response.ResponseProcessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import guan.suns.response.InsertScoreResponse;

/**
 * Created by lenovo on 2016/5/31.
 */
public class InsertScoreResponseProcessor {

    public String generateResponse(InsertScoreResponse insertScoreResponse)
    {
        ObjectMapper mapper = new ObjectMapper();
        String strResponse="";

        try{
            String rawResponse = mapper.writeValueAsString(insertScoreResponse);
            strResponse = new String(rawResponse.getBytes("UTF-8"),"UTF-8");
        }
        catch ( Exception e)
        {
            e.printStackTrace();
            return null;
        }

        return strResponse;
    }

}
