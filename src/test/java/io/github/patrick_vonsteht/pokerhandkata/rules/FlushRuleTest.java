package io.github.patrick_vonsteht.pokerhandkata.rules;

import io.github.patrick_vonsteht.pokerhandkata.model.CardSuit;
import io.github.patrick_vonsteht.pokerhandkata.model.CardValue;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FlushRuleTest {

    private static final ComparisonRuleFactory factory = new ComparisonRuleFactory();
    private static final ComparisonRule rule = factory.createFlushRule();

    private static final List<CardSuit> anySuits = RuleTestHelper.ANY_CARD_SUITS;
    private static final List<CardSuit> flushSuits = RuleTestHelper.FLUSH_SUITS;

    private static final List<CardValue> anyValues = RuleTestHelper.ANY_CARD_VALUES;
    private static final List<CardValue> lowValues = RuleTestHelper.LOW_CARD_VALUES;
    private static final List<CardValue> highValues = RuleTestHelper.HIGH_CARD_VALUES;

    @Test
    void ReturnsFlushHandWhenOtherHandDoesNotHaveFlush() {
        RuleTestHelper.assertHand1Wins(rule, flushSuits, anyValues, anySuits, anyValues);
    }

    @Test
    void ReturnsFlushHandWithHigherHighestCardWhenBothHandsHaveFlush() {
        RuleTestHelper.assertHand1Wins(rule, flushSuits, highValues, flushSuits, lowValues);
    }

    @Test
    void ReturnsDrawWhenBothHandsHaveFlushWithSameHighestCard() {
        RuleTestHelper.assertDrawResult(rule, flushSuits, highValues, flushSuits, highValues);
    }

    @Test
    void ReturnsNoMatchWhenNoHandHasFlush() {
        RuleTestHelper.assertNoMatchResult(rule, anySuits, anyValues, anySuits, anyValues);
    }

}
