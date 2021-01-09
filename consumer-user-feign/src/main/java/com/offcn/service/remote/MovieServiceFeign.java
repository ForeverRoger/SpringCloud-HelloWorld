package com.offcn.service.remote;

import com.offcn.pojo.Movie;
import com.offcn.service.remote.nativeimpl.MovieServiceFeignImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "PROVIDERMOVIE",fallback = MovieServiceFeignImpl.class)//目标服务的名称
public interface MovieServiceFeign {

    @GetMapping("/movie")
    public Movie remoteGetMovie();

    @GetMapping("/type/movieTypeList")
    public List<String> remoteMovieTypeList();

}
