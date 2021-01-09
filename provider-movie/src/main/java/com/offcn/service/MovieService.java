package com.offcn.service;

import com.offcn.dao.MovieDao;
import com.offcn.pojo.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    private MovieDao movieDao;

    public Movie getNewMovie() {
        return movieDao.getMovie();
    }
}
