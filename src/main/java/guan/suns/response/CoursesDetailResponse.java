package guan.suns.response;

import guan.suns.response.responseItem.CourseDetailItem;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/5/30.
 */
public class CoursesDetailResponse {

    private Integer status;
    private String info;
    private CourseDetailItem detail;

    public CoursesDetailResponse() {
    }

    public CoursesDetailResponse(Integer status, String info, CourseDetailItem detail) {
        this.status = status;
        this.info = info;
        this.detail = detail;
    }

    public CourseDetailItem getDetail() {
        return detail;
    }

    public void setDetail(CourseDetailItem detail) {
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


}
