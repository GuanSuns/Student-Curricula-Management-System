package guan.suns.response.ResponseProcessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import guan.suns.response.GetAllCoursesResponse;
import guan.suns.response.InsertScoreResponse;

/**
 * Created by lenovo on 2016/6/7.
 */
public class GetAllCoursesResponseProcessor {
    public String generateResponse(GetAllCoursesResponse getAllCoursesResponse)
    {
        ObjectMapper mapper = new ObjectMapper();
        String strResponse="";

        try{
            String rawResponse = mapper.writeValueAsString(getAllCoursesResponse);
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
