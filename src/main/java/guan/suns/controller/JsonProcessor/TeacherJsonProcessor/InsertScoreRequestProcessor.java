package guan.suns.controller.JsonProcessor.TeacherJsonProcessor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Timestamp;
import guan.suns.exception.JsonErrorException;
import guan.suns.request.TeacherRequest.InsertScoreRequest;
import guan.suns.request.TeacherRequest.InsertScoreRequestItem;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/5/30.
 */
public class InsertScoreRequestProcessor {
    public InsertScoreRequest getRequest(String rawJson) throws JsonErrorException
    {
        ObjectMapper mapper = new ObjectMapper();
        InsertScoreRequest request = new InsertScoreRequest();

        ArrayList<InsertScoreRequestItem> scoresList = new ArrayList<>();

        try{
            JsonNode root = mapper.readTree(rawJson);
            JsonNode datas = root.path("scores");

            for(int i=0; i < datas.size(); i++){
                JsonNode data = datas.get(i);

                if(data!=null){

                    InsertScoreRequestItem scoreItem = new InsertScoreRequestItem();
                    scoreItem.setCourseID(data.path("courseID").asText());
                    scoreItem.setScore(Float.valueOf(data.path("score").asText()));
                    scoreItem.setSelectYear(new Timestamp(data.path("selectYear").asLong()));
                    scoreItem.setStudentID(data.path("studentID").asText());

                    scoresList.add(scoreItem);
                }
            }

            request.setScores(scoresList);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new JsonErrorException();
        }

        return request;
    }
}
