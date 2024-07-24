package io.github.patrick_vonsteht.pokerhandkata.rules;

import io.github.patrick_vonsteht.pokerhandkata.TestValues;
import io.github.patrick_vonsteht.pokerhandkata.model.CardSuit;
import io.github.patrick_vonsteht.pokerhandkata.model.CardValue;
import org.junit.jupiter.api.Test;

import java.util.List;

public class HighCardRuleTest {
    private static final ComparisonRuleFactory factory = new ComparisonRuleFactory();
    private static final ComparisonRule rule = factory.createHighCardRule();

    private static final List<CardSuit> anySuits = TestValues.ANY_CARD_SUITS;

    public static final List<CardValue> highCardValuesHighHighHighHighHigh = TestValues.HIGH_CARD_VALUES_HIGH_HIGH_HIGH_HIGH_HIGH;
    public static final List<CardValue> highCardValuesHighHighHighHighLow = TestValues.HIGH_CARD_VALUES_HIGH_HIGH_HIGH_HIGH_LOW;
    public static final List<CardValue> highCardValuesHighHighHighLowLow = TestValues.HIGH_CARD_VALUES_HIGH_HIGH_HIGH_LOW_LOW;
    public static final List<CardValue> highCardValuesHighHighLowLowLow = TestValues.HIGH_CARD_VALUES_HIGH_HIGH_LOW_LOW_LOW;
    public static final List<CardValue> highCardValuesHighLowLowLowLow = TestValues.HIGH_CARD_VALUES_HIGH_LOW_LOW_LOW_LOW;
    public static final List<CardValue> highCardValuesLowLowLowLowLow = TestValues.HIGH_CARD_VALUES_LOW_LOW_LOW_LOW_LOW;

    @Test
    void ReturnsHandWithHigherFirstValue() {
        RuleTestHelper.assertHand1Wins(rule, anySuits, highCardValuesHighHighHighHighHigh, anySuits, highCardValuesLowLowLowLowLow);
    }

    @Test
    void ReturnsHandWithHigherSecondValueWhenFirstValueHasSameValue() {
        RuleTestHelper.assertHand1Wins(rule, anySuits, highCardValuesHighHighHighHighHigh, anySuits, highCardValuesHighLowLowLowLow);

    }

    @Test
    void ReturnsHandWithHigherThirdValueWhenFirstTwoValuesHaveSameValue() {
        RuleTestHelper.assertHand1Wins(rule, anySuits, highCardValuesHighHighHighHighHigh, anySuits, highCardValuesHighHighLowLowLow);

    }

    @Test
    void ReturnsHandWithHigherFourthValueWhenFirstThreeValuesHaveSameValue() {
        RuleTestHelper.assertHand1Wins(rule, anySuits, highCardValuesHighHighHighHighHigh, anySuits, highCardValuesHighHighHighLowLow);
    }

    @Test
    void ReturnsHandWithHigherFifthsValueWhenFirstFourValuesHaveSameValue() {
        RuleTestHelper.assertHand1Wins(rule, anySuits, highCardValuesHighHighHighHighHigh, anySuits, highCardValuesHighHighHighHighLow);
    }

    @Test
    void ReturnsDrawWhenBothHandsHaveSameValues() {
        RuleTestHelper.assertDrawResult(rule, anySuits, highCardValuesHighHighHighHighHigh, anySuits, highCardValuesHighHighHighHighHigh);
    }
}
