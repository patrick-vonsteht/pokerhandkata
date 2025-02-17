package io.github.patrick_vonsteht.pokerhandkata.rules;

import io.github.patrick_vonsteht.pokerhandkata.TestValues;
import io.github.patrick_vonsteht.pokerhandkata.model.CardSuit;
import io.github.patrick_vonsteht.pokerhandkata.model.CardValue;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TwoPairRuleTest {
    private static final ComparisonRuleFactory factory = new ComparisonRuleFactory();
    private static final ComparisonRule rule = factory.createTwoPairsRule();

    private static final List<CardSuit> anySuits = TestValues.ANY_CARD_SUITS;

    private static final List<CardValue> anyValues = TestValues.ANY_CARD_VALUES;
    private static final List<CardValue> twoPairWithLowFirstPair = TestValues.TWO_PAIR_WITH_LOW_FIRST_PAIR_VALUES;
    private static final List<CardValue> twoPairWithHighFirstPair = TestValues.TWO_PAIR_WITH_HIGH_FIRST_PAIR_VALUES;
    private static final List<CardValue> twoPairWithLowSecondPair = TestValues.TWO_PAIR_WITH_LOW_SECOND_PAIR_VALUES;
    private static final List<CardValue> twoPairWithHighSecondPair = TestValues.TWO_PAIR_WITH_HIGH_SECOND_PAIR_VALUES;
    private static final List<CardValue> twoPairWithLowFollowUpValue = TestValues.TWO_PAIR_WITH_LOW_FOLLOW_UP_VALUE_VALUES;
    private static final List<CardValue> twoPairWithHighFollowUpValue = TestValues.TWO_PAIR_WITH_HIGH_FOLLOW_UP_VALUE_VALUES;

    @Test
    void ReturnsTwoPairHandWhenOtherHandDoesNotHaveTwoPair() {
        RuleTestHelper.assertHand1Wins(rule, anySuits, twoPairWithHighFirstPair, anySuits, anyValues);
    }

    @Test
    void ReturnsTwoPairHandWithHigherFirstPairWhenBothHandsHaveTwoPair() {
        RuleTestHelper.assertHand1Wins(rule, anySuits, twoPairWithHighFirstPair, anySuits, twoPairWithLowFirstPair);
    }

    @Test
    void ReturnsTwoPairHandWithHigherSecondPairWhenBothHandsHaveTwoPairAndFirstPairHasSameValue() {
        RuleTestHelper.assertHand1Wins(rule, anySuits, twoPairWithHighSecondPair, anySuits, twoPairWithLowSecondPair);
    }

    @Test
    void ReturnsTwoPairHandWithHigherFollowUpValueWhenBothHandsHaveTwoPairAndPairsHaveSameValue() {
        RuleTestHelper.assertHand1Wins(rule, anySuits, twoPairWithHighFollowUpValue, anySuits, twoPairWithLowFollowUpValue);
    }

    @Test
    void ReturnsDrawWhenBothHandsHaveTwoPairWithSameValues() {
        RuleTestHelper.assertDrawResult(rule, anySuits, twoPairWithHighFirstPair, anySuits, twoPairWithHighFirstPair);
    }

    @Test
    void ReturnsNoMatchWhenNoHandHasTwoPair() {
        RuleTestHelper.assertNoMatchResult(rule, anySuits, anyValues, anySuits, anyValues);
    }
}
