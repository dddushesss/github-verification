package com.example.githubclient;

import java.util.regex.Pattern;

public class MessageTemplateVerifier
{
    public static final String VERIFICATION_RESULT = "^pull: .+";

    public static boolean process(String message){

        return Pattern.matches(VERIFICATION_RESULT, message);
    }
}
