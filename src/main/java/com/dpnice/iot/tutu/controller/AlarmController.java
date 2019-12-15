package com.dpnice.iot.tutu.controller;

import com.dpnice.iot.tutu.model.Alarm;
import com.dpnice.iot.tutu.model.result.RestResult;
import com.dpnice.iot.tutu.repository.AlarmRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

/**
 * @author DPnice
 * @date 2019-12-15 下午 4:06
 */
@RestController
@RequestMapping("/alarm/")
public class AlarmController {
    @Resource
    private final AlarmRepository alarmRepository;

    public AlarmController(AlarmRepository alarmRepository) {
        this.alarmRepository = alarmRepository;
    }

    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public RestResult<List<Alarm>> getAllGroup() {
        PageRequest pageRequest = PageRequest.of(0, 10,Sort.by(DESC,"alarmTime"));
        Page<Alarm> all = alarmRepository.findAll(pageRequest);
        return new RestResult<>(HttpStatus.OK.value(), all.getContent());
    }

}
