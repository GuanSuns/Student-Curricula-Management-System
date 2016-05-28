package guan.suns.controller.JsonProcessor.TeacherJsonProcessor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import guan.suns.basicClass.Department;
import guan.suns.exception.JsonErrorException;
import guan.suns.request.TeacherRequest.CreateTeacherRequest;

/**
 * Created by lenovo on 2016/5/28.
 */
public class CreateTeacherRequestProcessor {

    public CreateTeacherRequest getCreateRequest(String rawJson) throws JsonErrorException
    {
        ObjectMapper mapper = new ObjectMapper();
        CreateTeacherRequest request = new CreateTeacherRequest();

        try{
            JsonNode root = mapper.readTree(rawJson);
            request.setId(root.path("id").asText());
            request.setPassword(root.path("password").asText());
            request.setDepartment(Department.values()[root.path("department").asInt()]);
            request.setName(root.path("name").asText());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new JsonErrorException();
        }

        return request;
    }

}
