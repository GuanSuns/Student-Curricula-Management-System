package guan.suns.controller;

import guan.suns.controller.mappingUrl.UrlConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lenovo on 2016/5/23.
 */
@RestController
@RequestMapping(value = UrlConstant.StudentAccountRoot)
public class StudentAccountController {

    @RequestMapping(value = UrlConstant.StudentLogin , method = RequestMethod.POST)
    @ResponseBody
    public String login(HttpServletRequest httpServletRequest){


        return null;
    }

}
