package guan.suns.response;

/**
 * Created by lenovo on 2016/5/31.
 */
public class InsertScoreResponse {

    private Integer status;
    private String info;

    private Integer dataSize;
    private Integer successCnt;

    public InsertScoreResponse() {
    }

    public InsertScoreResponse(Integer status, String info, Integer dataSize, Integer successCnt) {
        this.status = status;
        this.info = info;
        this.dataSize = dataSize;
        this.successCnt = successCnt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getDataSize() {
        return dataSize;
    }

    public void setDataSize(Integer dataSize) {
        this.dataSize = dataSize;
    }

    public Integer getSuccessCnt() {
        return successCnt;
    }

    public void setSuccessCnt(Integer successCnt) {
        this.successCnt = successCnt;
    }
}
