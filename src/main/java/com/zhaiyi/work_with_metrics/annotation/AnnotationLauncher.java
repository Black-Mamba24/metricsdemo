package com.zhaiyi.work_with_metrics.annotation;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.SharedMetricRegistries;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhaiyi on 2017/9/21.
 */
public class AnnotationLauncher {

    public static void main(String[] args) throws InterruptedException {

//        AnnotationService annotationService = new AnnotationService(MetricRegisterFactory.getInstance());
//
//        for(int i = 0; i < 100; i++) {
//            annotationService.timedService();
//            annotationService.meteredService();
//            try {
//                annotationService.exceptionMeteredService();
//            } catch (Exception e) {
//
//            }
//        }
//
//        Thread.sleep(1000 * 60 * 2);

        AnnotationWithNamedRegistry fromProperty = new AnnotationWithNamedRegistry();
        MetricRegistry registry = SharedMetricRegistries.getOrCreate("registry");
        ConsoleReporter.forRegistry(registry).build().start(1, TimeUnit.SECONDS);

        for(int i = 0; i < 1000; i++) {
            fromProperty.timedMethod();
        }

        Thread.sleep(1000 * 60 * 2);

    }
}
