package com.zhaiyi.work_with_metrics.annotation;

import com.codahale.metrics.annotation.Timed;
import io.astefanutti.metrics.aspectj.Metrics;

/**
 * Created by zhaiyi on 2017/9/25.
 */

@Metrics(registry = "registry")
public class AnnotationWithNamedRegistry {

    @Timed(name = "timerName")
    public void timedMethod() {
    }
}