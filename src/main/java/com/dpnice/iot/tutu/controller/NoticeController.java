package com.dpnice.iot.tutu.controller;

import com.dpnice.iot.tutu.model.result.RestResult;
import com.dpnice.iot.tutu.service.NoticeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DPnice
 * @date 2019-12-13 下午 2:48
 */
@RestController
@RequestMapping("/notice/")
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @ResponseBody
    @RequestMapping(value = "msg", method = RequestMethod.GET)
    public RestResult getAllGroup() {

        return new RestResult<>(HttpStatus.OK.value(), noticeService.getNotice().getContent());
    }

}
