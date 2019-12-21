package com.dpnice.iot.tutu.controller;

import com.dpnice.iot.tutu.model.Msg;
import com.dpnice.iot.tutu.model.result.RestResult;
import com.dpnice.iot.tutu.repository.MsgRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author DPnice
 * @date 2019-12-21 下午 1:34
 */
@RestController
@RequestMapping
public class MsgController {

    @Resource
    private final MsgRepository msgRepository;

    public MsgController(MsgRepository msgRepository) {
        this.msgRepository = msgRepository;
    }


    @ResponseBody
    @RequestMapping(value = "msg", method = RequestMethod.POST)
    public RestResult msg(@RequestBody Msg msg) {
        msgRepository.save(msg);
        return new RestResult(HttpStatus.OK.value());
    }

    @ResponseBody
    @RequestMapping(value = "/msg/list", method = RequestMethod.GET)
    public RestResult getMsg(@RequestParam Long time) {
        List<Msg> allByCreateTimeAfter = msgRepository.findAllByCreateTimeGreaterThanEqual(time);
        return new RestResult<>(HttpStatus.OK.value(), allByCreateTimeAfter);
    }


}
