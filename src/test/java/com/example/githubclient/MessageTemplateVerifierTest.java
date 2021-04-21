package com.example.githubclient;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class MessageTemplateVerifierTest {

    @Test
    public void processTest() {
        boolean test = MessageTemplateVerifier.process("pull: 123");
        assertEquals(test,
                true);
        test = MessageTemplateVerifier.process(" 123");
        assertEquals(test,
                false);
    }

}
