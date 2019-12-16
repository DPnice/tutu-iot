package com.dpnice.iot.tutu.controller;

import com.dpnice.iot.tutu.model.HumiditySequential;
import com.dpnice.iot.tutu.model.TemperatureSequential;
import com.dpnice.iot.tutu.model.result.RestResult;
import com.dpnice.iot.tutu.repository.HumiditySequentialRepository;
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
public class HumidityController {

    @Resource
    private HumiditySequentialRepository humiditySequentialRepository;

    @ResponseBody
    @RequestMapping(value = "humidity/list", method = RequestMethod.GET)
    public RestResult temperatureList() {
        List<HumiditySequential> temperatureSequential = humiditySequentialRepository.queryDataInOneMinute();
        return new RestResult<>(HttpStatus.OK.value(), temperatureSequential);
    }


}
