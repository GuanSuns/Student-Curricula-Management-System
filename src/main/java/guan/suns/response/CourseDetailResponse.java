package guan.suns.response;

import guan.suns.response.responseItem.CourseDetailItem;

/**
 * Created by lenovo on 2016/5/30.
 */
public class CourseDetailResponse {

    private Integer status;
    private String info;
    private CourseDetailItem detail;

    public CourseDetailResponse() {
    }

    public CourseDetailResponse(Integer status, String info, CourseDetailItem detail) {
        this.status = status;
        this.info = info;
        this.detail = detail;
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

    public CourseDetailItem getDetail() {
        return detail;
    }

    public void setDetail(CourseDetailItem detail) {
        this.detail = detail;
    }
}
