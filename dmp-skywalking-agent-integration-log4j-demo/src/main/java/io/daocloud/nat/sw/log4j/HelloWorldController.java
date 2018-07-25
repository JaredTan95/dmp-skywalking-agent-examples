package io.daocloud.nat.sw.log4j;

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

    @GetMapping("/hello/{words}")
    public String hello(@PathVariable("words") String words) {
        log.info("words:{}", words);
        return helloService.say(words);
    }
}
