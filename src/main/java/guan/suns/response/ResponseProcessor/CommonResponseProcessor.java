package guan.suns.response.ResponseProcessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import guan.suns.exception.GenerateResponseErrorException;
import guan.suns.response.CommonResponse;

/**
 * Created by lenovo on 2016/5/23.
 */
public class CommonResponseProcessor {
    public String generateResponse(CommonResponse commonResponse)
    {
        ObjectMapper mapper = new ObjectMapper();
        String strResponse="";

        try{
            String rawResponse = mapper.writeValueAsString(commonResponse);
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
