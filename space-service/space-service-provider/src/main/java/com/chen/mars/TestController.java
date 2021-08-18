package com.chen.mars;

import com.chen.mars.service.DubbotestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
@RequiredArgsConstructor
public class TestController {

    @Autowired
    private DubbotestService dubbotestService;

    @RequestMapping(value = "/test", method = {RequestMethod.GET})
    public String test() {
        String test = dubbotestService.test();
        return test;
    }

}
