package io.daocloud.nat.traceActivation.controller;

import io.daocloud.nat.traceActivation.service.HelloService;
import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloWorldController {

    private static final Logger log = LoggerFactory.getLogger(HelloWorldController.class);

    private HelloService helloService;

    public HelloWorldController(HelloService helloService) {
        this.helloService = helloService;
    }

    @Trace
    @GetMapping("/hello/{words}")
    public String hello(@PathVariable("words") String words) {
        log.info("traceId:{}", TraceContext.traceId());
        ActiveSpan.tag("hello-trace activation", words);
        return helloService.say(words);
    }

    @Trace
    @GetMapping("/err")
    public String err() {
        String traceId =  TraceContext.traceId();
        log.info("traceId:{}", traceId);
        ActiveSpan.tag("error-trace activation", "error");
        throw new RuntimeException("err");
    }

    @Trace
    @GetMapping("/sleep")
    public void sleep() throws InterruptedException {
        ActiveSpan.tag("sleep-trace activation", "sleeping...");
        log.info("{}", TraceContext.traceId());
        Thread.sleep(6000l);
    }
}
