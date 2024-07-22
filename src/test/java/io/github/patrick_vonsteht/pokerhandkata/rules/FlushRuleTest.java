package io.github.patrick_vonsteht.pokerhandkata.rules;

import io.github.patrick_vonsteht.pokerhandkata.matchers.PokerHandMatcher;
import io.github.patrick_vonsteht.pokerhandkata.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FlushRuleTest {

    private final PokerHand hand1 = PokerHand.fromCards(
        new Card(CardSuit.DIAMONDS, CardValue.TWO),
        new Card(CardSuit.CLUBS, CardValue.FOUR),
        new Card(CardSuit.SPADES, CardValue.JACK),
        new Card(CardSuit.CLUBS, CardValue.KING),
        new Card(CardSuit.HEARTS, CardValue.SIX)
    );

    private final PokerHand hand2 = PokerHand.fromCards(
            new Card(CardSuit.DIAMONDS, CardValue.TWO),
            new Card(CardSuit.CLUBS, CardValue.FOUR),
            new Card(CardSuit.SPADES, CardValue.JACK),
            new Card(CardSuit.CLUBS, CardValue.KING),
            new Card(CardSuit.HEARTS, CardValue.SIX)
    );

    @Test
    void FlushRuleReturnsNoMatchWhenNoHandHasFlush() {
        assertFlushRuleResult(
                false,
                false,
                null,
                RuleResultType.NO_MATCH,
                null);
    }

    @Test
    void FlushRuleReturnsHand1WhenOnlyHand1HasFlush() {
        assertFlushRuleResult(
                true,
                false,
                null,
                RuleResultType.WINNER_MATCH,
                hand1);
    }

    @Test
    void FlushRuleReturnsHand2WhenOnlyHand2HasFlush() {
        assertFlushRuleResult(
                false,
                true,
                null,
                RuleResultType.WINNER_MATCH,
                hand2);

    }

    @Test
    void FlushRuleReturnsHand1WhenBothHandsHaveFlushAndHand1RanksHigherOnHighCardRule() {
        assertFlushRuleResult(
                true,
                true,
                RuleResult.winnerRuleResult(hand1),
                RuleResultType.WINNER_MATCH,
                hand1);
    }

    @Test
    void FlushRuleReturnsHand2WhenBothHandsHaveFlushAndHand2RanksHigherOnHighCardRule() {
        assertFlushRuleResult(
                true,
                true,
                RuleResult.winnerRuleResult(hand2),
                RuleResultType.WINNER_MATCH,
                hand2);
    }

    @Test
    void FlushRuleReturnsDrawWhenBothHandsHaveFlushAndSameValues() {
        assertFlushRuleResult(
                true,
                true,
                RuleResult.drawRuleResult(),
                RuleResultType.DRAW_MATCH,
                null);
    }

    private void assertFlushRuleResult(final boolean resultForHand1, final boolean resultForHand2,
                                       final RuleResult resultForHighCardRule, final RuleResultType expectedResultType,
                                       final PokerHand expectedWinner) {
        PokerHandMatcher mockedFlushMatcher = mock(PokerHandMatcher.class);
        when(mockedFlushMatcher.matches(hand1)).thenReturn(resultForHand1);
        when(mockedFlushMatcher.matches(hand2)).thenReturn(resultForHand2);

        PokerHandComparisonRule mockedHighCardRule = mock(MatcherRuleWithScorer.class);
        when(mockedHighCardRule.compare(hand1, hand2)).thenReturn(resultForHighCardRule);

        PokerHandComparisonRule rule = new MatcherRuleWithSecondaryRule(mockedFlushMatcher, mockedHighCardRule);
        RuleResult result = rule.compare(hand1, hand2);

        assertEquals(expectedResultType, result.getType());

        if (result.getType() == RuleResultType.WINNER_MATCH) {
            assertEquals(expectedWinner, result.getWinner());
        }
    }
}