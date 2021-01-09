package com.offcn.controller;

import com.netflix.discovery.converters.Auto;
import com.offcn.pojo.Movie;
import com.offcn.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    /**
     * 获取最新电影
     * @return
     */
    @GetMapping("/movie")
    public Movie getMovie(){
        return movieService.getNewMovie();
    }

}
