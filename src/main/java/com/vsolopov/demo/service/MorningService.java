package com.vsolopov.demo.service;

import com.vsolopov.myspring.annotation.MyBean;

@MyBean("MyMorningService")
public class MorningService {

    public void morning(){
        System.out.println("Good morning!");
    }

}
