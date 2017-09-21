# metricsdemo

基于spring mvc框架，目的只是为了适应大多是场景，提供简单的调用方法。没有集成metrics-spring

一、使用metrics原生类库

DemoController + DemoService

测试了metrics提供的所有metric

参考了http://metrics.dropwizard.io/3.2.3/getting-started.html

二、使用metrics注解

metrics提供了@Gauge，@CachedGauge，@Counted，@Metered，@ExceptionMetered，@Timed

尝试测试所有注解，但发现can't work，原因是没有AOP或Aspectj

我是通过https://stackoverflow.com/questions/28499621/codahale-metrics-using-timed-metrics-annotation-in-plain-java找到答案的

这一篇的问题就是在普通java工程中如何使用metrics注解。回答提供了两种方法，一是集成AOP或Aspectj，二是使用DropWizard框架，并选择了前者

@Timed、@Metered、@ExceptionMetered能工作，但还是发现了一些问题或bug，有兴趣的可以run一下，很方便