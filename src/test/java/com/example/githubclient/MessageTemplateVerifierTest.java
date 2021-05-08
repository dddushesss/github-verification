package com.example.githubclient;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class MessageTemplateVerifierTest extends AbstractTest {

    @Test
    public void processLeetcodeAddedTest() {
        String  test = MessageTemplateVerifier.process("LEETCODE 1022 Added 123");
        assertEquals(test, "Commit test result:\nOK");

    }

    @Test
    public void processLeetcodeFixedTest() {
        String test = MessageTemplateVerifier.process("LEETCODE 1022 Fixed 123");
        assertEquals(test, "Commit test result:\nOK");
    }

    @Test
    public void processLeetcodeDeletedTest() {
        String test = MessageTemplateVerifier.process("LEETCODE 1022 Deleted 123");
        assertEquals(test, "Commit test result:\nOK");
    }

    @Test
    public void processLeetcodeRefactoredTest() {
        String test = MessageTemplateVerifier.process("LEETCODE 1022 Refactored 123");
        assertEquals(test, "Commit test result:\nOK");
    }

    @Test
    public void processGeneratorRefactoredTest() {
        String test = MessageTemplateVerifier.process("GENERATOR 1022 Refactored 123");
        assertEquals(test, "Commit test result:\nOK");
    }

    @Test
    public void processGeneratorAddedTest() {
        String test = MessageTemplateVerifier.process("GENERATOR 1022 Added 123");
        assertEquals(test, "Commit test result:\nOK");
    }

    @Test
    public void processGeneratorFixedTest() {
        String test = MessageTemplateVerifier.process("GENERATOR 1022 Fixed 123");
        assertEquals(test, "Commit test result:\nOK");
    }

    @Test
    public void processGeneratorMovedTest() {
        String test = MessageTemplateVerifier.process("GENERATOR 1022 Moved 123");
        assertEquals(test, "Commit test result:\nOK");
    }

    @Test
    public void processErrorMessageTest() {
        String test = MessageTemplateVerifier.process("Привет, я сделял дз, проверьте, ато я валнуюсь");
        assertEquals(test, MessageTemplateVerifier.VERIFICATION_RESULT + MessageTemplateVerifier.ERROR_RESULT);
    }
}
