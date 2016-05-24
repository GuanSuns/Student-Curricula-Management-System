package guan.suns.request;

/**
 * Created by lenovo on 2016/5/24.
 */
public class DeleteUserRequest {
    private String id;

    public DeleteUserRequest() {
    }

    public DeleteUserRequest(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
