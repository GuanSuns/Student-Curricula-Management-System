package guan.suns.request;

/**
 * Created by lenovo on 2016/5/30.
 */
public class GetCourseDetailRequest {

    private String id;

    public GetCourseDetailRequest() {
    }

    public GetCourseDetailRequest(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
