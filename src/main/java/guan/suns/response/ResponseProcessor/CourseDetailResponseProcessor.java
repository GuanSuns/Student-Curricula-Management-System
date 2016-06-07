package guan.suns.response.ResponseProcessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import guan.suns.response.CoursesDetailResponse;

/**
 * Created by lenovo on 2016/5/30.
 */
public class CourseDetailResponseProcessor {

    public String generateResponse(CoursesDetailResponse coursesDetailResponse)
    {
        ObjectMapper mapper = new ObjectMapper();
        String strResponse="";

        try{
            String rawResponse = mapper.writeValueAsString(coursesDetailResponse);
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
