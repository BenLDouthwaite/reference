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
}
