package io.github.patrick_vonsteht.pokerhandkata.rules;

import io.github.patrick_vonsteht.pokerhandkata.model.CardSuit;
import io.github.patrick_vonsteht.pokerhandkata.model.CardValue;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PairRuleTest {
    private static final ComparisonRuleFactory factory = new ComparisonRuleFactory();
    private static final PokerHandComparisonRule rule = factory.createPairRule();

    private static final List<CardSuit> anySuits = RuleTestHelper.ANY_CARD_SUITS;

    private static final List<CardValue> anyValues = RuleTestHelper.ANY_CARD_VALUES;
    private static final List<CardValue> lowPairValues = RuleTestHelper.LOW_PAIR_VALUES;
    private static final List<CardValue> highPairValues = RuleTestHelper.HIGH_PAIR_VALUES;
    private static final List<CardValue> pairWithLowFollowUpValues = RuleTestHelper.PAIR_WITH_LOW_FOLLOW_UP_VALUES;
    private static final List<CardValue> pairWithHighFollowUpValues = RuleTestHelper.PAIR_WITH_HIGH_FOLLOW_UP_VALUES;


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
