package com.offcn.dao;

import com.offcn.pojo.Movie;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Component//不确定
@Controller//controller对象
@Service//service对象
@Repository//dao对象
public class MovieDao {

    public Movie getMovie(){
        Movie movie = new Movie();
        movie.setId(1);
        movie.setMovieName("送你一朵小红花");
        return movie;
    }

}
