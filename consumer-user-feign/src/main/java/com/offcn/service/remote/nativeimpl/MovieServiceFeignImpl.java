package com.offcn.service.remote.nativeimpl;

import com.offcn.pojo.Movie;
import com.offcn.service.remote.MovieServiceFeign;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class MovieServiceFeignImpl implements MovieServiceFeign {
    @Override
    public Movie remoteGetMovie() {
        Movie movie = new Movie();
        movie.setId(100001);
        movie.setMovieName("无法加载电影");
        return movie;
    }

    @Override
    public List<String> remoteMovieTypeList() {
        return Arrays.asList("暂无法加载电影类型列表");
    }

}
