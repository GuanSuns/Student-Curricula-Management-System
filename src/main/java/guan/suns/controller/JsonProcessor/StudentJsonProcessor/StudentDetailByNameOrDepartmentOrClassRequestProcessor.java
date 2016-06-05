package guan.suns.controller.JsonProcessor.StudentJsonProcessor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import guan.suns.basicClass.Department;
import guan.suns.exception.JsonErrorException;
import guan.suns.request.StudentRequest.GetStudentDetailByNameOrDepartmentOrClassRequest;

/**
 * Created by lenovo on 2016/6/5.
 */
public class StudentDetailByNameOrDepartmentOrClassRequestProcessor {
    public GetStudentDetailByNameOrDepartmentOrClassRequest getRequest(String rawJson) throws JsonErrorException
    {
        ObjectMapper mapper = new ObjectMapper();
        GetStudentDetailByNameOrDepartmentOrClassRequest request = new GetStudentDetailByNameOrDepartmentOrClassRequest();

        try{
            JsonNode root = mapper.readTree(rawJson);

            String str = root.path("name").asText();
            if(str==null || str.equals("")) {
                request.setName(null);
            }
            else{
                request.setName(str);
            }

            str = root.path("className").asText();
            if(str==null || str.equals("")) {
                request.setClassName(null);
            }
            else{
                request.setClassName(str);
            }

            str = root.path("department").asText();
            if(str==null || str.equals("")) {
                request.setDepartment(null);
            }
            else{
                request.setDepartment(Department.values()[root.path("department").asInt()]);
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
