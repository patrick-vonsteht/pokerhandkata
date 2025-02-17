package io.github.patrick_vonsteht.pokerhandkata.rules;

import io.github.patrick_vonsteht.pokerhandkata.TestValues;
import io.github.patrick_vonsteht.pokerhandkata.model.CardSuit;
import io.github.patrick_vonsteht.pokerhandkata.model.CardValue;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ThreeOfAKindRuleTest {
    private static final ComparisonRuleFactory factory = new ComparisonRuleFactory();
    private static final ComparisonRule rule = factory.createThreeOfAKindRule();

    private static final List<CardSuit> anySuits = TestValues.ANY_CARD_SUITS;

    private static final List<CardValue> anyValues = TestValues.ANY_CARD_VALUES;
    private static final List<CardValue> lowThreeOfAKindValues = TestValues.LOW_THREE_OF_A_KIND_VALUES;
    private static final List<CardValue> highThreeOfAKindValues = TestValues.HIGH_THREE_OF_A_KIND_VALUES;

    @Test
    void ReturnsThreeOfAKindHandWhenOtherHandDoesNotHaveThreeOfAKind() {
        RuleTestHelper.assertHand1Wins(rule, anySuits, highThreeOfAKindValues, anySuits, anyValues);
    }

    @Test
    void ReturnsThreeOfAKindHandWithHigherThreeOfAKindWhenBothHandsHaveThreeOfAKind() {
        RuleTestHelper.assertHand1Wins(rule, anySuits, highThreeOfAKindValues, anySuits, lowThreeOfAKindValues);
    }

    @Test
    void ReturnsNoMatchWhenNoHandHasThreeOfAKind() {
        RuleTestHelper.assertNoMatchResult(rule, anySuits, anyValues, anySuits, anyValues);
    }
}
