package io.github.patrick_vonsteht.pokerhandkata.ui;

import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;
import io.github.patrick_vonsteht.pokerhandkata.model.PokerJudgeResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PokerJudgeResultPrinterTest {

    private final PokerHand winnerHand = mock(PokerHand.class);
    private final PokerHand nonWinnerHand = mock(PokerHand.class);

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
        PokerJudgeResult result = PokerJudgeResult.winnerRuleResult(winnerHand);
        PokerJudgeResultPrinter printer = new PokerJudgeResultPrinter();

        printer.print(result, winnerHand, nonWinnerHand);

        assertEquals("Hand1 has won!\n", testOut.toString());
    }

    @Test
    void PokerJudgeResultPrinterPrintsHand2WinnerMessageWhenHand2Won() {
        PokerJudgeResult result = PokerJudgeResult.winnerRuleResult(winnerHand);
        PokerJudgeResultPrinter printer = new PokerJudgeResultPrinter();

        printer.print(result, nonWinnerHand, winnerHand);

        assertEquals("Hand2 has won!\n", testOut.toString());
    }

    @Test
    void PokerJudgeResultPrinterPrintsDrawMessageWhenDraw() {
        PokerJudgeResult result = PokerJudgeResult.drawRuleResult();
        PokerJudgeResultPrinter printer = new PokerJudgeResultPrinter();

        printer.print(result, nonWinnerHand, nonWinnerHand);

        assertEquals("Draw between hand1 and hand2!\n", testOut.toString());
    }
}