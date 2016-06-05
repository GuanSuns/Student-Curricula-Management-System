package guan.suns.response.ResponseProcessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import guan.suns.response.StudentsDetailsByNameAndClassAndDepartmentResponse;

/**
 * Created by lenovo on 2016/6/5.
 */
public class StudentsDetailsByNameAndClassAndDepartmentResponseProcessor {

    public String generateResponse(StudentsDetailsByNameAndClassAndDepartmentResponse studentsDetailResponse)
    {
        ObjectMapper mapper = new ObjectMapper();
        String strResponse="";

        try{
            String rawResponse = mapper.writeValueAsString(studentsDetailResponse);
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
