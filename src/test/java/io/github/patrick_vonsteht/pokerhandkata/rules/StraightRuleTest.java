package io.github.patrick_vonsteht.pokerhandkata.rules;

import io.github.patrick_vonsteht.pokerhandkata.model.CardSuit;
import io.github.patrick_vonsteht.pokerhandkata.model.CardValue;
import org.junit.jupiter.api.Test;

import java.util.List;

public class StraightRuleTest {

    private static final ComparisonRuleFactory factory = new ComparisonRuleFactory();
    private static final PokerHandComparisonRule rule = factory.createStraightRule();

    private static final List<CardSuit> anySuits = RuleTestHelper.ANY_CARD_SUITS;

    private static final List<CardValue> anyValues = RuleTestHelper.ANY_CARD_VALUES;
    private static final List<CardValue> highStreetValues = RuleTestHelper.HIGH_STREET_VALUES;
    private static final List<CardValue> lowStreetValues = RuleTestHelper.LOW_STREET_VALUES;

    @Test
    void ReturnsStraightHandWhenOtherHandDoesNotHaveStraight() {
        RuleTestHelper.assertHand1Wins(rule, anySuits, highStreetValues, anySuits, anyValues);
    }

    @Test
    void ReturnsStraightHandWithHigherHighestCardWhenBothHandsHaveStraight() {
        RuleTestHelper.assertHand1Wins(rule, anySuits, highStreetValues, anySuits, lowStreetValues);
    }

    @Test
    void ReturnsDrawWhenBothHandsHaveStraightWithSameHighestCard() {
        RuleTestHelper.assertDrawResult(rule, anySuits, highStreetValues, anySuits, highStreetValues);
    }

    @Test
    void ReturnsNoMatchWhenNoHandHasStraight() {
        RuleTestHelper.assertNoMatchResult(rule, anySuits, anyValues, anySuits, anyValues);
    }

}
