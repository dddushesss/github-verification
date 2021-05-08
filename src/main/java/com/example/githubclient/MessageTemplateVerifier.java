package com.example.githubclient;

import java.util.regex.Pattern;

public class MessageTemplateVerifier
{
    public static final String regx = "^(GENERATOR|LEETCODE)\\s(2021|2022|1021|1022)\\s(Added|Fixed|Refactored|Deleted|Moved)\\s.+";


    public static boolean process(String message){

        return Pattern.matches(regx, message);
    }
}
