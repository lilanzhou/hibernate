package com.ryan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2019:04:17
 *
 * @Author : Lilanzhou
 * ¹¦ÄÜ :
 */
@Controller
public class HelloController {
    @RequestMapping(value = "/hello")
    public String word(){
      System.out.println("ok");
        return "index";
    }

    @RequestMapping(value = "/heo")
    public String success(){
        //System.out.println("ok");
        return "success";
    }
}
