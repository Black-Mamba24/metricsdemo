package com.zhaiyi.work_with_metrics.factory;

import com.codahale.metrics.MetricRegistry;

/**
 * Created by zhaiyi on 2017/9/20.
 */
public class MetricRegisterFactory {
    private static final MetricRegistry INSTANCE;

    static {
        INSTANCE = new MetricRegistry();
    }

    private MetricRegisterFactory() {
    }

    public static MetricRegistry getInstance() {
        return INSTANCE;
    }

}
