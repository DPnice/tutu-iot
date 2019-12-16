package com.dpnice.iot.tutu.controller;

import com.dpnice.iot.tutu.model.WaterQuantitySequential;
import com.dpnice.iot.tutu.model.result.RestResult;
import com.dpnice.iot.tutu.repository.WaterQuantitySequentialRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DPnice
 * @date 2019-12-16 下午 10:18
 */
@RestController
@RequestMapping("/")
public class WaterController {

    @Resource
    private WaterQuantitySequentialRepository waterQuantitySequentialRepository;

    @ResponseBody
    @RequestMapping(value = "water/list", method = RequestMethod.GET)
    public RestResult temperatureList() {
        List<WaterQuantitySequential> temperatureSequential = waterQuantitySequentialRepository.queryDataInOneMinute();
        return new RestResult<>(HttpStatus.OK.value(), temperatureSequential);
    }
}
