package io.github.patrick_vonsteht.pokerhandkata.rules;

import io.github.patrick_vonsteht.pokerhandkata.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FullHouseRuleTest {

    private static final ComparisonRuleFactory factory = new ComparisonRuleFactory();
    private static final ComparisonRule rule = factory.createFullHouseRule();

    private static final List<CardSuit> anySuits = RuleTestHelper.ANY_CARD_SUITS;

    private static final List<CardValue> anyValues = RuleTestHelper.ANY_CARD_VALUES;
    private static final List<CardValue> highFullHouseValues = RuleTestHelper.HIGH_FULL_HOUSE_VALUES;
    private static final List<CardValue> lowFullHouseValues = RuleTestHelper.LOW_FULL_HOUSE_VALUES;

    @Test
    void ReturnsFullHouseHandWhenOtherHandDoesNotHaveFullHouse() {
        RuleTestHelper.assertHand1Wins(rule, anySuits, highFullHouseValues, anySuits, anyValues);
    }

    @Test
    void ReturnsFullHouseHandWithHigherThreeOfAKindWhenBothHandsHaveFullHouse() {
        RuleTestHelper.assertHand1Wins(rule, anySuits, highFullHouseValues, anySuits, lowFullHouseValues);
    }

    @Test
    void ReturnsNoMatchWhenNoHandHasFullHouse() {
        RuleTestHelper.assertNoMatchResult(rule, anySuits, anyValues, anySuits, anyValues);
    }
}
