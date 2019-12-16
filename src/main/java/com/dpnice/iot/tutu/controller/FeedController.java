package com.dpnice.iot.tutu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DPnice
 * @date 2019-12-16 下午 11:36
 */
@RestController
@RequestMapping("/feed")
public class FeedController {

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public String createGroup() {
        return "POST ok";
    }

}
