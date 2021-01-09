package com.offcn.service;

import com.netflix.discovery.converters.Auto;
import com.offcn.dao.UserDao;
import com.offcn.pojo.Movie;
import com.offcn.pojo.User;
import com.offcn.service.remote.MovieServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private MovieServiceFeign movieServiceFeign;

    /*@Autowired
    RestTemplate restTemplate;*/

    /**
     * 根据ID得到用户对象
     *
     * @param id
     * @return
     */
    public User getUserById(Integer id) {
        User user = userDao.getUser(id);
        return user;
    }

    /**
     * 购买最新的电影票
     * @param id 用户ID
     * @return
     */
    public Map<String, Object> buyMovie(Integer id) {
        Map<String, Object> result = new HashMap<>();
        //1.查询用户信息
        //调用自己的方法去查询用户信息
        User user = this.getUserById(id);
        result.put("user", user);
        //2.查到最新电影票
        //TODO 暂时为null(用户服务调用电影服务，返回一个电影对象)

        //ribbon方式的调用
        /*restTemplate.getForObject();
        restTemplate.postForObject();
        restTemplate.put();
        restTemplate.delete();*/
/*
        //远程调用1
        Movie movie = restTemplate.getForObject("http://PROVIDERMOVIE/movie", Movie.class);

        //远程调用2
        List list = restTemplate.getForObject("http://PROVIDERMOVIE/type/movieTypeList", List.class);*/

        //result.put("movieTypeList",list);

        //result.put("movie", movie);

        /////////////////////////////////////////////////////
        //feign方式调用
        Movie movie = movieServiceFeign.remoteGetMovie();
        List<String> list = movieServiceFeign.remoteMovieTypeList();

        result.put("movie",movie);
        result.put("movieTypeList",list);

        return result;
    }
}