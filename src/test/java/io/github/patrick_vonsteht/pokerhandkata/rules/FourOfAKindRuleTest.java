package io.github.patrick_vonsteht.pokerhandkata.rules;

import io.github.patrick_vonsteht.pokerhandkata.TestValues;
import io.github.patrick_vonsteht.pokerhandkata.model.CardSuit;
import io.github.patrick_vonsteht.pokerhandkata.model.CardValue;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FourOfAKindRuleTest {
    private static final ComparisonRuleFactory factory = new ComparisonRuleFactory();
    private static final ComparisonRule rule = factory.createFourOfAKindRule();

    private static final List<CardSuit> anySuits = TestValues.ANY_CARD_SUITS;

    private static final List<CardValue> anyValues = TestValues.ANY_CARD_VALUES;
    private static final List<CardValue> lowFourOfAKindValues = TestValues.LOW_FOUR_OF_A_KIND_VALUES;
    private static final List<CardValue> highFourOfAKindValues = TestValues.HIGH_FOUR_OF_A_KIND_VALUES;

    @Test
    void ReturnsFourOfAKindHandWhenOtherHandDoesNotHaveFourOfAKind() {
        RuleTestHelper.assertHand1Wins(rule, anySuits, highFourOfAKindValues, anySuits, anyValues);
    }

    @Test
    void ReturnsFourOfAKindHandWithHigherFourOfAKindWhenBothHandsHaveFourOfAKind() {
        RuleTestHelper.assertHand1Wins(rule, anySuits, highFourOfAKindValues, anySuits, lowFourOfAKindValues);
    }

    @Test
    void ReturnsNoMatchWhenNoHandHasFourOfAKind() {
        RuleTestHelper.assertNoMatchResult(rule, anySuits, anyValues, anySuits, anyValues);
    }
}
