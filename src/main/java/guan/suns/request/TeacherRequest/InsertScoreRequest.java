package guan.suns.request.TeacherRequest;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/5/30.
 */
public class InsertScoreRequest {

    private ArrayList<InsertScoreRequestItem> scores;

    public InsertScoreRequest() {
    }

    public InsertScoreRequest(ArrayList<InsertScoreRequestItem> scores) {
        this.scores = scores;
    }

    public ArrayList<InsertScoreRequestItem> getScores() {
        return scores;
    }

    public void setScores(ArrayList<InsertScoreRequestItem> scores) {
        this.scores = scores;
    }
}
