package com.dpnice.iot.tutu.controller;

import lombok.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author DPnice
 */
@RestController
@RequestMapping
public class ExampleController {

    @ResponseBody
    @RequestMapping(value = "ping", method = RequestMethod.POST)
    public String createGroup() {
        return "POST ok";
    }

    @ResponseBody
    @RequestMapping(value = "ping", method = RequestMethod.DELETE)
    public String removeGroup(@RequestBody A s) {
        return "DELETE ok:" + s.getName();
    }

    @ResponseBody
    @RequestMapping(value = "ping", method = RequestMethod.PUT)
    public String updateGroup(@RequestBody A s) {
        return "PUT ok:" + s.getName();
    }


    @ResponseBody
    @RequestMapping(value = "ping", method = RequestMethod.GET)
    public String getAllGroup() {
        return "pong";
    }

    @Getter
    @Setter
    static class A {
        @NonNull
        String name;

    }

}
