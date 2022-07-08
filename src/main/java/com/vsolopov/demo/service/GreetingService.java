package com.vsolopov.demo.service;

import com.vsolopov.myspring.annotation.MyBean;

@MyBean
public class GreetingService {

    public void hello(){
        System.out.println("Hey. What's up?");
    }

}
