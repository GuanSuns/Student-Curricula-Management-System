package guan.suns.controller.JsonProcessor.StudentJsonProcessor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import guan.suns.basicClass.Department;
import guan.suns.basicClass.Gender;
import guan.suns.exception.JsonErrorException;
import guan.suns.request.StudentRequest.CreateStudentRequest;

import java.sql.Timestamp;


/**
 * Created by lenovo on 2016/5/23.
 */
public class CreateStudentRequestProcessor {
    public CreateStudentRequest getCreateRequest(String rawJson) throws JsonErrorException
    {
        ObjectMapper mapper = new ObjectMapper();
        CreateStudentRequest request = new CreateStudentRequest();

        try{
            JsonNode root = mapper.readTree(rawJson);
            request.setId(root.path("id").asText());
            request.setPassword(root.path("password").asText());
            request.setClassName(root.path("className").asText());
            request.setEnrolledAge(root.path("enrolledAge").asInt());
            request.setGender(Gender.values()[root.path("gender").asInt()]);
            request.setEnrolledTime(new Timestamp(root.path("enrolledTime").asLong()));
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
