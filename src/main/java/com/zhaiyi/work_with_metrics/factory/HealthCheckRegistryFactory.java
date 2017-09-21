package com.zhaiyi.work_with_metrics.factory;

import com.codahale.metrics.health.HealthCheckRegistry;

/**
 * Created by zhaiyi on 2017/9/20.
 */
public class HealthCheckRegistryFactory {
    private static final HealthCheckRegistry INSTANCE;

    static {
        INSTANCE = new HealthCheckRegistry();
    }

    private HealthCheckRegistryFactory() {

    }

    public static HealthCheckRegistry getInstance() {
        return INSTANCE;
    }
}
