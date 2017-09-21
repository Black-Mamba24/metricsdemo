package com.zhaiyi.work_with_metrics.service;

/**
 * Created by zhaiyi on 2017/9/21.
 */
public class AnnotationLauncher {

    public static void main(String[] args) throws InterruptedException {

        AnnotationService annotationService = new AnnotationService();

        for(int i = 0; i < 100; i++) {
            annotationService.timedService();
            annotationService.meteredService();
            try {
                annotationService.exceptionMeteredService();
            } catch (Exception e) {

            }
        }

        Thread.sleep(1000 * 60 * 2);

    }
}
