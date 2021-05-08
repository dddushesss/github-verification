package com.example.githubclient;

import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class MessageTemplateVerifierTest extends AbstractTest {

    @Test
    public void processLeetcodeAddedTest() {
        boolean test = MessageTemplateVerifier.process("LEETCODE 1022 Added 123");
        assertTrue(test);

    }

    @Test
    public void processLeetcodeFixedTest() {
        boolean test = MessageTemplateVerifier.process("LEETCODE 1022 Fixed 123");
        assertTrue(test);
    }

    @Test
    public void processLeetcodeDeletedTest() {
        boolean test = MessageTemplateVerifier.process("LEETCODE 1022 Deleted 123");
        assertTrue(test);
    }

    @Test
    public void processLeetcodeRefactoredTest() {
        boolean test = MessageTemplateVerifier.process("LEETCODE 1022 Refactored 123");
        assertTrue(test);
    }

    @Test
    public void processGeneratorRefactoredTest() {
        boolean test = MessageTemplateVerifier.process("GENERATOR 1022 Refactored 123");
        assertTrue(test);
    }

    @Test
    public void processGeneratorAddedTest() {
        boolean test = MessageTemplateVerifier.process("GENERATOR 1022 Added 123");
        assertTrue(test);
    }

    @Test
    public void processGeneratorFixedTest() {
        boolean test = MessageTemplateVerifier.process("GENERATOR 1022 Fixed 123");
        assertTrue(test);
    }

    @Test
    public void processGeneratorMovedTest() {
        boolean test = MessageTemplateVerifier.process("GENERATOR 1022 Moved 123");
        assertTrue(test);
    }
}
