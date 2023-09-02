package com.bendouthwaite;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class HelloController {

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/hello")
    public List<String> hello(@RequestParam(value = "name", defaultValue = "Ben") String name) {
        if ("".equals(name)) {
            return Collections.singletonList("Test 1");
        } else {
            return Collections.singletonList(name);
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/bye")
    public List<String> bye(@RequestParam(value = "name", defaultValue = "Bye BYe baby") String name) {
        if ("".equals(name)) {
            return Collections.singletonList("Bye test 1");
        } else {
            return Collections.singletonList(name);
        }
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/test")
    public List<String> test(@RequestParam(value = "name", defaultValue = "test for the best of the rest") String name) {
        if ("".equals(name)) {
            return Collections.singletonList("test test 2");
        } else {
            return Collections.singletonList(name);
        }
    }
}
