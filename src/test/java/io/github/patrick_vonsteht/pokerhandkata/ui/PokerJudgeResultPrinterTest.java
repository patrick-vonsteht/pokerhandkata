package io.github.patrick_vonsteht.pokerhandkata.ui;

import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;
import io.github.patrick_vonsteht.pokerhandkata.model.PokerJudgeResult;
import io.github.patrick_vonsteht.pokerhandkata.model.PokerJudgeResultType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PokerJudgeResultPrinterTest {

    private final PokerHand hand1 = mock(PokerHand.class);
    private final PokerHand hand2 = mock(PokerHand.class);

    private final PrintStream standardOut = System.out;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void captureOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    public void uncaptureOutput() {
        System.setOut(standardOut);
    }

    @Test
    void PokerJudgeResultPrinterPrintsHand1WinnerMessageWhenHand1Won() {
        PokerJudgeResult result = PokerJudgeResult.winnerResult(hand1);
        assertOutput("Hand1 has won!\n", result);
    }

    @Test
    void PokerJudgeResultPrinterPrintsHand2WinnerMessageWhenHand2Won() {
        PokerJudgeResult result = PokerJudgeResult.winnerResult(hand2);
        assertOutput("Hand2 has won!\n", result);
    }

    @Test
    void PokerJudgeResultPrinterPrintsDrawMessageWhenDraw() {
        PokerJudgeResult result = PokerJudgeResult.drawResult();
        assertOutput("Draw between hand1 and hand2!\n", result);
    }

    private void assertOutput(final String expectedOutput, final PokerJudgeResult result) {
        PokerJudgeResultPrinter printer = new PokerJudgeResultPrinter();
        printer.print(result, hand1, hand2);
        assertEquals(expectedOutput, testOut.toString());
    }

}