package com.dpnice.iot.tutu.controller;

import com.dpnice.iot.tutu.model.TwoTuple;
import com.dpnice.iot.tutu.model.result.RestResult;
import com.dpnice.iot.tutu.service.FeedService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author DPnice
 * @date 2019-12-16 下午 11:36
 */
@RestController
@RequestMapping
public class FeedController {

    private final FeedService feedService;

    public FeedController(FeedService feedService) {
        this.feedService = feedService;
    }

    @ResponseBody
    @RequestMapping(value = "feed",method = RequestMethod.POST)
    public RestResult feed() {
        feedService.feed();
        return new RestResult(HttpStatus.OK.value());
    }

    @ResponseBody
    @RequestMapping(value = "/feed/list", method = RequestMethod.GET)
    public RestResult<List<TwoTuple<String, Integer>>> getFeedRecord() {
        return new RestResult<>(HttpStatus.OK.value(), feedService.getFeedList());
    }


}
