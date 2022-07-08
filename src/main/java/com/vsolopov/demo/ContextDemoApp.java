package com.vsolopov.demo;

import com.vsolopov.demo.service.GreetingService;
import com.vsolopov.demo.service.MorningService;
import com.vsolopov.myspring.context.PackageScanningApplicationContext;

public class ContextDemoApp {

    public static void main(String[] args) {
        var context = new PackageScanningApplicationContext("com.vsolopov.demo");
        var morningService = context.getBean(MorningService.class);
        morningService.morning();

        var greetingService = context.getBean("GreetingService", GreetingService.class);
        greetingService.hello();

        

    }
}
