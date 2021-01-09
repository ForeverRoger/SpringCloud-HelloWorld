package com.offcn.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/type")
public class MovieTypeController {


    @Value("${server.port}")//获取当前端口参数
    String port;

    @GetMapping("/movieTypeList")
    public List<String> movieTypeList(){
        return Arrays.asList("2D电影","3D电影","4D电影",port);
    }

}
