package guan.suns.request;

/**
 * Created by lenovo on 2016/5/29.
 */
public class GetUserDetailRequest {

    private String id;

    public GetUserDetailRequest() {
    }

    public GetUserDetailRequest(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
