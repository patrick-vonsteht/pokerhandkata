package io.github.patrick_vonsteht.pokerhandkata;

import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;
import io.github.patrick_vonsteht.pokerhandkata.model.PokerJudgeResult;
import io.github.patrick_vonsteht.pokerhandkata.model.PokerJudgeResultType;
import io.github.patrick_vonsteht.pokerhandkata.rules.ComparisonRule;
import io.github.patrick_vonsteht.pokerhandkata.model.ComparisonRuleResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PokerJudgeUnitTest {
    private static final PokerHand mockedHand1 = mock(PokerHand.class);
    private static final PokerHand mockedHand2 = mock(PokerHand.class);

    private static final ComparisonRule notMatchingRule = Mockito.mock(ComparisonRule.class);
    private static final ComparisonRule hand1WinningRule = Mockito.mock(ComparisonRule.class);
    private static final ComparisonRule hand2WinningRule = Mockito.mock(ComparisonRule.class);
    private static final ComparisonRule drawRule = Mockito.mock(ComparisonRule.class);

    @BeforeAll
    static void initializePokerHandComparisonRuleMocks() {
        when(notMatchingRule.compare(mockedHand1, mockedHand2)).thenReturn(ComparisonRuleResult.noMatchRuleResult());
        when(hand1WinningRule.compare(mockedHand1, mockedHand2)).thenReturn(ComparisonRuleResult.winnerRuleResult(mockedHand1));
        when(hand2WinningRule.compare(mockedHand1, mockedHand2)).thenReturn(ComparisonRuleResult.winnerRuleResult(mockedHand2));
        when(drawRule.compare(mockedHand1, mockedHand2)).thenReturn(ComparisonRuleResult.drawRuleResult());
    }

    @Test
    void PokerJudgeReturnsDrawWhenNoRulesAreConfigured() {
        assertDraw();
    }

    @Test
    void PokerJudgeReturnsDrawWhenNoRuleMatches() {
        assertDraw(notMatchingRule);
    }

    @Test
    void PokerJudgeReturnsHand1WhenHand1WinsFirstRule() {
        assertWinner(mockedHand1, hand1WinningRule);
    }

    @Test
    void PokerJudgeReturnsHand2WhenHand2WinsFirstRule() {
        assertWinner(mockedHand2, hand2WinningRule);
    }

    @Test
    void PokerJudgeReturnsDrawWhenFirstRuleReturnsDraw() {
        assertDraw(drawRule);
    }

    @Test
    void PokerJudgeReturnsHand1WhenFirstRuleDoesNotMatchAndHand1WinsSecondRule() {
        assertWinner(mockedHand1, notMatchingRule, hand1WinningRule);
    }

    @Test
    void PokerJudgeReturnsHand2WhenFirstRuleDoesNotMatchAndHand2WinsSecondRule() {
        assertWinner(mockedHand2, notMatchingRule, hand2WinningRule);
    }

    @Test
    void PokerJudgeReturnsDrawWhenFirstRuleDoesNotMatchAndSecondRuleReturnsDraw() {
        assertDraw(notMatchingRule, drawRule);
    }

    private void assertDraw(ComparisonRule... rules) {
        PokerJudge judge = new PokerJudge(rules);
        PokerJudgeResult result = judge.judge(mockedHand1, mockedHand2);
        assertEquals(PokerJudgeResultType.DRAW, result.getType());
    }

    private void assertWinner(PokerHand winner, ComparisonRule... rules) {
        PokerJudge judge = new PokerJudge(rules);
        PokerJudgeResult result = judge.judge(mockedHand1, mockedHand2);

        assertEquals(PokerJudgeResultType.WINNER, result.getType());
        assertEquals(winner, result.getWinner());
    }
}