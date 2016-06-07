package guan.suns.controller.JsonProcessor.StudentJsonProcessor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import guan.suns.basicClass.Department;
import guan.suns.exception.JsonErrorException;
import guan.suns.request.StudentRequest.GetStudentDetailsStatisticsRequest;

/**
 * Created by lenovo on 2016/6/5.
 */
public class StudentDetailsStatisticsRequestProcessor {
    public GetStudentDetailsStatisticsRequest getRequest(String rawJson) throws JsonErrorException
    {
        ObjectMapper mapper = new ObjectMapper();
        GetStudentDetailsStatisticsRequest request = new GetStudentDetailsStatisticsRequest();

        try{
            JsonNode root = mapper.readTree(rawJson);

            String str = root.path("name").asText();
            //System.out.println("Name: "+str);
            if(str==null || str.equals("")) {
                request.setName(null);
            }
            else{
                request.setName(str);
            }

            str = root.path("className").asText();
            //System.out.println("className: "+str);
            if(str==null || str.equals("")) {
                request.setClassName(null);
            }
            else{
                request.setClassName(str);
            }

            str = root.path("department").asText();
            //System.out.println("department: "+str);
            if(str==null || str.equals("")) {
                request.setDepartment(null);
            }
            else{
                request.setDepartment(Department.values()[root.path("department").asInt()]);
            }

            str = root.path("studentID").asText();
            //System.out.println("studentID: "+str);
            if(str==null || str.equals("")) {
                request.setStudentID(null);
            }
            else{
                request.setStudentID(str);
            }

            str = root.path("courseID").asText();
            //System.out.println("courseID: "+str);
            if(str==null || str.equals("")) {
                request.setCourseID(null);
            }
            else{
                request.setCourseID(str);
            }

            str = root.path("courseName").asText();
            //System.out.println("courseName: "+str);
            if(str==null || str.equals("")) {
                request.setCourseName(null);
            }
            else{
                request.setCourseName(str);
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
