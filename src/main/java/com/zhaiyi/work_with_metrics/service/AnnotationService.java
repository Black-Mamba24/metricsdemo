package com.zhaiyi.work_with_metrics.service;

import com.codahale.metrics.*;
import com.codahale.metrics.annotation.*;
import com.codahale.metrics.annotation.CachedGauge;
import com.codahale.metrics.annotation.Gauge;
import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Metric;
import com.zhaiyi.work_with_metrics.factory.MetricRegisterFactory;
import io.astefanutti.metrics.aspectj.Metrics;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhaiyi on 2017/9/20.
 */

@Metrics(registry = "${this.registry}")
public class AnnotationService {
    private Random random = new Random();
    private Set<String> set = new HashSet<String>();
    private MetricRegistry registry = null;

    public AnnotationService() {
        this(MetricRegisterFactory.getInstance());
    }

    public AnnotationService(MetricRegistry registry) {
        this.registry = registry;
        ConsoleReporter.forRegistry(this.registry).build().start(15, TimeUnit.SECONDS);
    }

    //必要的,${this.registry}会调用
    public MetricRegistry getRegistry() {
        return registry;
    }
//有bug,会导致反复注册同一个gauge
//    @Gauge
//    public int setSize() {
//        return set.size();
//    }

    //Metrics注解不识别,不能注册
    @CachedGauge(timeout = 10, timeoutUnit = TimeUnit.SECONDS)
    private int cachedSetSize() {
        return set.size();
    }

    private boolean add(String string) {
        return set.add(string);
    }

    private boolean remove(String string) {
        return set.remove(string);
    }

    public boolean gaugeService(String action, String string) {
        if ("add".equals(action)) {
            return add(string);
        } else if ("remove".equals(action)) {
            return remove(string);
        }
        return false;
    }

    //Metrics注解不识别,不能注册
    @Counted
    public void countedService() {
        try {
            Thread.sleep(random.nextInt(150));
        } catch (InterruptedException e) {
        }
    }

    @Metered
    public void meteredService() {
        try {
            Thread.sleep(random.nextInt(150));
        } catch (InterruptedException e) {
        }
    }

    @ExceptionMetered
    public void exceptionMeteredService() {
        if (!random.nextBoolean()) {
            throw new RuntimeException("runtime error");
        }
        try {
            Thread.sleep(random.nextInt(150));
        } catch (InterruptedException e) {
        }
    }

    @Timed
    public void timedService() {
        try {
            Thread.sleep(random.nextInt(150));
        } catch (InterruptedException e) {
        }
    }
}
