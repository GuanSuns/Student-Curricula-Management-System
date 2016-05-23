package guan.suns.response;

/**
 * Created by lenovo on 2016/5/23.
 */
public class LoginResponse {

    private int status;
    private String info;

    public LoginResponse() {
    }

    public LoginResponse(int status, String info) {
        this.status = status;
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
