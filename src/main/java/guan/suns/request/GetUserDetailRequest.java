package guan.suns.request;

/**
 * Created by lenovo on 2016/5/29.
 */
public class GetUserDetailRequest {

    private String id;

    private String name;

    public GetUserDetailRequest() {
    }

    public GetUserDetailRequest(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
