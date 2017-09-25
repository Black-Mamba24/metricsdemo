package com.zhaiyi.work_with_metrics.annotation;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.annotation.Timed;
import io.astefanutti.metrics.aspectj.Metrics;

import java.util.Random;

/**
 * Created by zhaiyi on 2017/9/25.
 */
@Metrics(registry = "${this.registry}")
public class AnnotationWithReloadMethod {
    private Random random = new Random();
    private final MetricRegistry registry;


    public AnnotationWithReloadMethod(MetricRegistry registry) {
        this.registry = registry;
    }

    public MetricRegistry getRegistry() {
        return registry;
    }

    @Timed
    public void timed() throws InterruptedException {
        Thread.sleep(random.nextInt(150));
    }

    @Timed
    public void timed(String s) throws InterruptedException {
        Thread.sleep(random.nextInt(100));
    }
}
