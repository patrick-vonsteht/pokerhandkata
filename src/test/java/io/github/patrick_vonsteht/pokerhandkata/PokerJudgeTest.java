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

class PokerJudgeTest {
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
        assertPokerJudgeResult(
                PokerJudgeResultType.DRAW,
                null);
    }

    @Test
    void PokerJudgeReturnsDrawWhenNoRuleMatches() {
        assertPokerJudgeResult(
                PokerJudgeResultType.DRAW,
                null,
                notMatchingRule);
    }

    @Test
    void PokerJudgeReturnsHand1WhenHand1WinsFirstRule() {
        assertPokerJudgeResult(
                PokerJudgeResultType.WINNER,
                mockedHand1,
                hand1WinningRule
        );
    }

    @Test
    void PokerJudgeReturnsHand2WhenHand2WinsFirstRule() {
        assertPokerJudgeResult(
                PokerJudgeResultType.WINNER,
                mockedHand2,
                hand2WinningRule
        );
    }

    @Test
    void PokerJudgeReturnsDrawWhenFirstRuleReturnsDraw() {
        assertPokerJudgeResult(
                PokerJudgeResultType.DRAW,
                null,
                drawRule
        );
    }

    @Test
    void PokerJudgeReturnsHand1WhenFirstRuleDoesNotMatchAndHand1WinsSecondRule() {
        assertPokerJudgeResult(
                PokerJudgeResultType.WINNER,
                mockedHand1,
                notMatchingRule,
                hand1WinningRule
        );
    }

    @Test
    void PokerJudgeReturnsHand2WhenFirstRuleDoesNotMatchAndHand2WinsSecondRule() {
        assertPokerJudgeResult(
                PokerJudgeResultType.WINNER,
                mockedHand2,
                notMatchingRule,
                hand2WinningRule
        );
    }

    @Test
    void PokerJudgeReturnsDrawWhenFirstRuleDoesNotMatchAndSecondRuleReturnsDraw() {
        assertPokerJudgeResult(
                PokerJudgeResultType.DRAW,
                null,
                notMatchingRule,
                drawRule
        );
    }

    private void assertPokerJudgeResult(final PokerJudgeResultType expectedType, final PokerHand expectedWinner,
                                        final PokerHandComparisonRule... rules) {
        PokerJudge judge = new PokerJudge(rules);
        PokerJudgeResult result = judge.judge(mockedHand1, mockedHand2);

        assertEquals(expectedType, result.getType());
        assertEquals(expectedWinner, result.getWinner());
    }
}