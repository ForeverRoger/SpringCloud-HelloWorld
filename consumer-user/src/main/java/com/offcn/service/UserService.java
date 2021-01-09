package com.offcn.service;

import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.offcn.dao.UserDao;
import com.offcn.pojo.Movie;
import com.offcn.pojo.User;
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
    RestTemplate restTemplate;

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
    //该方法内需要通过ribbon进行远程服务调用
    //添加注解，当该方法进行远程调用过程中失败了，就让该方法执行一段本地程序并返回（如果没有断路器，就会报错）
    @HystrixCommand(fallbackMethod = "buyMovieFallbackMethod")
    public Map<String, Object> buyMovie(Integer id) {
        Map<String, Object> result = new HashMap<>();
        //1.查询用户信息
        //调用自己的方法去查询用户信息
        User user = this.getUserById(id);
        result.put("user", user);
        //2.查到最新电影票
        //TODO 暂时为null(用户服务调用电影服务，返回一个电影对象)

        /*restTemplate.getForObject();
        restTemplate.postForObject();
        restTemplate.put();
        restTemplate.delete();*/

        //远程调用1
        Movie movie = restTemplate.getForObject("http://PROVIDERMOVIE/movie", Movie.class);

        //远程调用2
        List list = restTemplate.getForObject("http://PROVIDERMOVIE/type/movieTypeList", List.class);

        result.put("movieTypeList",list);

        result.put("movie", movie);
        return result;
    }

    public Map<String, Object> buyMovieFallbackMethod(Integer id) {
        Map map = new HashMap();
        map.put("code",40001);
        map.put("msg","服务器异常，请稍后重试");
        return map;
    }
}