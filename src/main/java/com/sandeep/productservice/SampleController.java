package com.sandeep.productservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/say")
public class SampleController {

    @GetMapping("/hello")
    public String sayHello()
    {
        return "Hello";
    }

    @GetMapping("/hello/{name}")
    public String sayHelloToName(@PathVariable("name") String name)
    {
        return "Hello "+name;
    }

//    @GetMapping("/hello/{name}/{city}")
//    public String sayHelloToNameAndCity(@PathVariable("name") String name, @PathVariable("city") String city)
//    {
//        return "Hello "+name + " " + city;
//    }

    @GetMapping("/hello/{name}/{number}")
    public String sayHelloToNameAndNumber(@PathVariable("name") String name, @PathVariable("number") int number)
    {
        return ("Hello " + name).repeat(Math.max(0, number));
    }


}
