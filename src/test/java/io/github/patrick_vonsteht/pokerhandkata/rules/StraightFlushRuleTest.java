package io.github.patrick_vonsteht.pokerhandkata.rules;

import io.github.patrick_vonsteht.pokerhandkata.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StraightFlushRuleTest {

    private static final ComparisonRuleFactory factory = new ComparisonRuleFactory();
    private static final ComparisonRule rule = factory.createStraightFlushRule();

    private static final List<CardSuit> anySuits = RuleTestHelper.ANY_CARD_SUITS;
    private static final List<CardSuit> flushSuits = RuleTestHelper.FLUSH_SUITS;

    private static final List<CardValue> anyValues = RuleTestHelper.ANY_CARD_VALUES;
    private static final List<CardValue> highStraightValues = RuleTestHelper.HIGH_STRAIGHT_VALUES;
    private static final List<CardValue> lowStraightValues = RuleTestHelper.LOW_STRAIGHT_VALUES;

    @Test
    void ReturnsStraightFlushHandWhenOtherHandDoesNotHaveStraightFlush() {
        RuleTestHelper.assertHand1Wins(rule, flushSuits, highStraightValues, anySuits, anyValues);
    }

    @Test
    void ReturnsStraightFlushHandWithHigherHighestCardWhenBothHandsHaveStraightFlush() {
        RuleTestHelper.assertHand1Wins(rule, flushSuits, highStraightValues, flushSuits, lowStraightValues);
    }

    @Test
    void ReturnsDrawWhenBothHandsHaveStraightFlushWithSameHighestCard() {
        RuleTestHelper.assertDrawResult(rule, flushSuits, highStraightValues, flushSuits, highStraightValues);
    }

    @Test
    void ReturnsNoMatchWhenNoHandHasStraightFlush() {
        RuleTestHelper.assertNoMatchResult(rule, anySuits, anyValues, anySuits, anyValues);
    }

}
