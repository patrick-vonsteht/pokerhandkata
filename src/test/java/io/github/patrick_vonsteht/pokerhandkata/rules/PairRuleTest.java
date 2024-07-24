package io.github.patrick_vonsteht.pokerhandkata.rules;

import io.github.patrick_vonsteht.pokerhandkata.TestValues;
import io.github.patrick_vonsteht.pokerhandkata.model.CardSuit;
import io.github.patrick_vonsteht.pokerhandkata.model.CardValue;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PairRuleTest {
    private static final ComparisonRuleFactory factory = new ComparisonRuleFactory();
    private static final ComparisonRule rule = factory.createPairRule();

    private static final List<CardSuit> anySuits = TestValues.ANY_CARD_SUITS;

    private static final List<CardValue> anyValues = TestValues.ANY_CARD_VALUES;
    private static final List<CardValue> lowPairValues = TestValues.LOW_PAIR_VALUES;
    private static final List<CardValue> highPairValues = TestValues.HIGH_PAIR_VALUES;
    private static final List<CardValue> pairWithLowFollowUpValues = TestValues.PAIR_WITH_LOW_FOLLOW_UP_VALUES;
    private static final List<CardValue> pairWithHighFollowUpValues = TestValues.PAIR_WITH_HIGH_FOLLOW_UP_VALUES;


    @Test
    void ReturnsPairHandWhenOtherHandDoesNotHavePair() {
        RuleTestHelper.assertHand1Wins(rule, anySuits, highPairValues, anySuits, anyValues);
    }

    @Test
    void ReturnsPairHandWithHigherPairWhenBothHandsHavePair() {
        RuleTestHelper.assertHand1Wins(rule, anySuits, highPairValues, anySuits, lowPairValues);
    }

    @Test
    void ReturnsPairHandWithHigherFollowUpCardsWhenBothHandsHavePairWithSameValue() {
        RuleTestHelper.assertHand1Wins(rule, anySuits, pairWithHighFollowUpValues, anySuits, pairWithLowFollowUpValues);
    }

    @Test
    void ReturnsNoMatchWhenNoHandHasPair() {
        RuleTestHelper.assertNoMatchResult(rule, anySuits, anyValues, anySuits, anyValues);
    }
}
