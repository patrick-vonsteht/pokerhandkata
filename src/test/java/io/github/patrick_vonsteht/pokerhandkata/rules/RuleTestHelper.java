package io.github.patrick_vonsteht.pokerhandkata.rules;

import io.github.patrick_vonsteht.pokerhandkata.PokerHandTestHelper;
import io.github.patrick_vonsteht.pokerhandkata.model.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RuleTestHelper {
    public static void assertHand1Wins(final ComparisonRule rule,
                                       final List<CardSuit> suits1, final List<CardValue> values1,
                                       final List<CardSuit> suits2, final List<CardValue> values2) {
        PokerHand hand1 = PokerHandTestHelper.createHandFromSuitsAndValues(suits1, values1);
        PokerHand hand2 = PokerHandTestHelper.createHandFromSuitsAndValues(suits2, values2);
        assertResult(rule, hand1, hand2, ComparisonRuleResultType.WINNER_MATCH, hand1);
    }

    public static void assertDrawResult(ComparisonRule rule,
                                        final List<CardSuit> suits1, final List<CardValue> values1,
                                        final List<CardSuit> suits2, final List<CardValue> values2) {
        PokerHand hand1 = PokerHandTestHelper.createHandFromSuitsAndValues(suits1, values1);
        PokerHand hand2 = PokerHandTestHelper.createHandFromSuitsAndValues(suits2, values2);
        assertResult(rule, hand1, hand2, ComparisonRuleResultType.DRAW_MATCH, null);
    }

    public static void assertNoMatchResult(final ComparisonRule rule,
                                           final List<CardSuit> suits1, final List<CardValue> values1,
                                           final List<CardSuit> suits2, final List<CardValue> values2) {
        PokerHand hand1 = PokerHandTestHelper.createHandFromSuitsAndValues(suits1, values1);
        PokerHand hand2 = PokerHandTestHelper.createHandFromSuitsAndValues(suits2, values2);
        assertResult(rule, hand1, hand2, ComparisonRuleResultType.NO_MATCH, null);
    }

    private static void assertResult(ComparisonRule rule, PokerHand hand1, PokerHand hand2,
                                     ComparisonRuleResultType expectedResultType, PokerHand expectedWinner) {
        ComparisonRuleResult result = rule.compare(hand1, hand2);

        assertEquals(expectedResultType, result.getType());

        if (result.getType() == ComparisonRuleResultType.WINNER_MATCH) {
            assertEquals(expectedWinner, result.getWinner());
        }
    }
}
