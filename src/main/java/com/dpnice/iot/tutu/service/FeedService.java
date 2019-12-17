package com.dpnice.iot.tutu.service;

import com.dpnice.iot.tutu.model.TwoTuple;

import java.util.List;

/**
 * @author DPnice
 * @date 2019-12-17 下午 9:13
 */
public interface FeedService {


    List<TwoTuple<String, Integer>> getFeedList();


    void feed();

}
