package guan.suns.response.ResponseProcessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import guan.suns.response.TeacherDetailResponse;

/**
 * Created by lenovo on 2016/5/29.
 */
public class TeacherDetailResponseProcessor {
    public String generateResponse(TeacherDetailResponse teacherDetailResponse)
    {
        ObjectMapper mapper = new ObjectMapper();
        String strResponse="";

        try{
            String rawResponse = mapper.writeValueAsString(teacherDetailResponse);
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
