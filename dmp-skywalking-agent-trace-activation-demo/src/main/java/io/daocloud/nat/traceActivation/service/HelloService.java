package io.daocloud.nat.traceActivation.service;

import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String say(String words) {
        //System.out.println(words);
        return "hello " + words + TraceContext.traceId();
    }
}
