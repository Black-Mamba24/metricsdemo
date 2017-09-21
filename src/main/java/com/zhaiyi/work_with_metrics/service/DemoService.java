package com.zhaiyi.work_with_metrics.service;

import com.codahale.metrics.*;
import com.zhaiyi.work_with_metrics.factory.MetricRegisterFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhaiyi on 2017/9/20.
 */

//@Service
public class DemoService {
    private MetricRegistry metricRegistry;
    private Gauge gauge;
    private Counter counter;
    private Meter meter;
    private Histogram histogram;
    private Timer timer;
    private Queue<Integer> queue = new LinkedBlockingDeque<Integer>();
    private Random randam = new Random();

    @PostConstruct
    public void init() {
        metricRegistry = MetricRegisterFactory.getInstance();
        gauge = metricRegistry.register(MetricRegistry.name(DemoService.class, "gauge"), new Gauge() {
            public Object getValue() {
                return queue.size();
            }
        });
        counter = metricRegistry.counter(MetricRegistry.name(DemoService.class, "counter"));
        meter = metricRegistry.meter(MetricRegistry.name(DemoService.class, "meter"));
        histogram = metricRegistry.histogram(MetricRegistry.name(DemoService.class, "histogram"));
        timer = metricRegistry.timer(MetricRegistry.name(DemoService.class, "timer"));

        //定义reporter,控制台输出
        ConsoleReporter.forRegistry(metricRegistry).build().start(15, TimeUnit.SECONDS);
    }

    public void gaugeSevice(String action) {
        //统计某个值,如队列长度
        if ("enqueue".equals(action)) {
            queue.offer(randam.nextInt());
        } else if ("dequeue".equals(action)) {
            queue.poll();
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
    }

    public void counterService(String inc, long value) {
        //统计计数器
        if ("inc".equals(inc)) {
            counter.inc(value);
        } else if ("dec".equals(inc)) {
            counter.dec(value);
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
    }

    public void meterService() {
        //统计方法调用速率
        meter.mark();
        try {
            Thread.sleep(randam.nextInt(100));
        } catch (InterruptedException e) {
        }
    }

    public void histogramService(byte[] bytes) {
        //统计值的分布(最大/最小/平均/75%/90%/95%/98%/99%/99.9%)
        histogram.update(bytes.length);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
    }

    public void timerService() {
        //统计方法调用速率+处理时间的分布
        //开始计时
        Timer.Context context = timer.time();

        try {
            Thread.sleep(randam.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //结束计时
            context.stop();
        }

    }

}
