package com.example.githubclient;

import org.springframework.stereotype.Component;

@Component
public class VerifierExecuter {


    //@Scheduled(cron = "* * * ? * *")
    public void verify() {
        System.out.printf("Hello");
    }
}
