package io.github.patrick_vonsteht.pokerhandkata.rules;

import io.github.patrick_vonsteht.pokerhandkata.model.CardSuit;
import io.github.patrick_vonsteht.pokerhandkata.model.CardValue;
import org.junit.jupiter.api.Test;

import java.util.List;

public class StraightRuleTest {

    private static final ComparisonRuleFactory factory = new ComparisonRuleFactory();
    private static final ComparisonRule rule = factory.createStraightRule();

    private static final List<CardSuit> anySuits = RuleTestHelper.ANY_CARD_SUITS;

    private static final List<CardValue> anyValues = RuleTestHelper.ANY_CARD_VALUES;
    private static final List<CardValue> highStraightValues = RuleTestHelper.HIGH_STRAIGHT_VALUES;
    private static final List<CardValue> lowStraightValues = RuleTestHelper.LOW_STRAIGHT_VALUES;

    @Test
    void ReturnsStraightHandWhenOtherHandDoesNotHaveStraight() {
        RuleTestHelper.assertHand1Wins(rule, anySuits, highStraightValues, anySuits, anyValues);
    }

    @Test
    void ReturnsStraightHandWithHigherHighestCardWhenBothHandsHaveStraight() {
        RuleTestHelper.assertHand1Wins(rule, anySuits, highStraightValues, anySuits, lowStraightValues);
    }

    @Test
    void ReturnsDrawWhenBothHandsHaveStraightWithSameHighestCard() {
        RuleTestHelper.assertDrawResult(rule, anySuits, highStraightValues, anySuits, highStraightValues);
    }

    @Test
    void ReturnsNoMatchWhenNoHandHasStraight() {
        RuleTestHelper.assertNoMatchResult(rule, anySuits, anyValues, anySuits, anyValues);
    }

}
