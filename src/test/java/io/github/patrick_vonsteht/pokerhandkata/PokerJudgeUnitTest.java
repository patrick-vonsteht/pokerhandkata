package io.github.patrick_vonsteht.pokerhandkata;

import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;
import io.github.patrick_vonsteht.pokerhandkata.model.PokerJudgeResult;
import io.github.patrick_vonsteht.pokerhandkata.model.PokerJudgeResultType;
import io.github.patrick_vonsteht.pokerhandkata.rules.PokerHandComparisonRule;
import io.github.patrick_vonsteht.pokerhandkata.model.RuleResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PokerJudgeUnitTest {
    private static final PokerHand mockedHand1 = mock(PokerHand.class);
    private static final PokerHand mockedHand2 = mock(PokerHand.class);

    private static final PokerHandComparisonRule notMatchingRule = Mockito.mock(PokerHandComparisonRule.class);
    private static final PokerHandComparisonRule hand1WinningRule = Mockito.mock(PokerHandComparisonRule.class);
    private static final PokerHandComparisonRule hand2WinningRule = Mockito.mock(PokerHandComparisonRule.class);
    private static final PokerHandComparisonRule drawRule = Mockito.mock(PokerHandComparisonRule.class);

    @BeforeAll
    static void initializePokerHandComparisonRuleMocks() {
        when(notMatchingRule.compare(mockedHand1, mockedHand2)).thenReturn(RuleResult.noMatchRuleResult());
        when(hand1WinningRule.compare(mockedHand1, mockedHand2)).thenReturn(RuleResult.winnerRuleResult(mockedHand1));
        when(hand2WinningRule.compare(mockedHand1, mockedHand2)).thenReturn(RuleResult.winnerRuleResult(mockedHand2));
        when(drawRule.compare(mockedHand1, mockedHand2)).thenReturn(RuleResult.drawRuleResult());
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

    private void assertDraw(PokerHandComparisonRule... rules) {
        PokerJudge judge = new PokerJudge(rules);
        PokerJudgeResult result = judge.judge(mockedHand1, mockedHand2);
        assertEquals(PokerJudgeResultType.DRAW, result.getType());
    }

    private void assertWinner(PokerHand winner, PokerHandComparisonRule... rules) {
        PokerJudge judge = new PokerJudge(rules);
        PokerJudgeResult result = judge.judge(mockedHand1, mockedHand2);

        assertEquals(PokerJudgeResultType.WINNER, result.getType());
        assertEquals(winner, result.getWinner());
    }
}