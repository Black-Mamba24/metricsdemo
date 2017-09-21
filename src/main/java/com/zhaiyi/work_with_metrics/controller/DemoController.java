package com.zhaiyi.work_with_metrics.controller;

import com.zhaiyi.work_with_metrics.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhaiyi on 2017/9/20.
 */

//@Controller
public class DemoController {

    @Autowired
    private DemoService demoService;

    @ResponseBody
    @RequestMapping("/gauge/{action}")
    public String gauge(@PathVariable(value = "action") String action) {
        demoService.gaugeSevice(action);
        return "gauge ok\n";
    }

    @ResponseBody
    @RequestMapping("/counter/{inc}/{value}")
    public String counter(@PathVariable(value = "inc") String inc, @PathVariable(value = "value") String value) {
        demoService.counterService(inc, Long.parseLong(value));
        return "counter ok\n";
    }

    @ResponseBody
    @RequestMapping("/meter")
    public String meter() {
        demoService.meterService();
        return "meter ok\n";
    }

    @ResponseBody
    @RequestMapping("/histogram/{value}")
    public String histogram(@PathVariable(value = "value") String value) {
        byte[] bytes = new byte[Integer.parseInt(value)];
        demoService.histogramService(bytes);
        return "histogram ok\n";
    }

    @ResponseBody
    @RequestMapping("/timer")
    public String timer() {
        demoService.timerService();
        return "timer ok\n";
    }
}
