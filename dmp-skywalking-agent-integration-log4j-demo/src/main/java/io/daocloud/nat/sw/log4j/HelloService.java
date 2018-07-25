package io.daocloud.nat.sw.log4j;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String say(String words) {
        //System.out.println(words);
        return "hello " + words;
    }
}
