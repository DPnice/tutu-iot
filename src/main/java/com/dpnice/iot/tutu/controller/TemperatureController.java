package com.dpnice.iot.tutu.controller;

import com.dpnice.iot.tutu.model.TemperatureSequential;
import com.dpnice.iot.tutu.model.result.RestResult;
import com.dpnice.iot.tutu.repository.TemperatureSequentialRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DPnice
 * @date 2019-12-15 下午 10:52
 */
@RestController
@RequestMapping("/")
public class TemperatureController {

    @Resource
    private TemperatureSequentialRepository temperatureSequentialRepository;

    @ResponseBody
    @RequestMapping(value = "temperature/list", method = RequestMethod.GET)
    public RestResult temperatureList() {
        List<TemperatureSequential> temperatureSequential = temperatureSequentialRepository.queryDataInOneMinute();
        return new RestResult<>(HttpStatus.OK.value(), temperatureSequential);
    }


}
