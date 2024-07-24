package io.github.patrick_vonsteht.pokerhandkata.rules;

import io.github.patrick_vonsteht.pokerhandkata.PokerHandTestHelper;
import io.github.patrick_vonsteht.pokerhandkata.model.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RuleTestHelper {
    // Any Suits
    public static List<CardSuit> ANY_CARD_SUITS =
            List.of(CardSuit.DIAMONDS, CardSuit.CLUBS, CardSuit.SPADES, CardSuit.DIAMONDS, CardSuit.HEARTS);

    // Flush Suits
    public static List<CardSuit> FLUSH_SUITS =
            List.of(CardSuit.DIAMONDS, CardSuit.DIAMONDS, CardSuit.DIAMONDS, CardSuit.DIAMONDS, CardSuit.DIAMONDS);

    public static List<CardSuit> OTHER_FLUSH_SUITS =
            List.of(CardSuit.CLUBS, CardSuit.CLUBS, CardSuit.CLUBS, CardSuit.CLUBS, CardSuit.CLUBS);

    // Any Values
    public static List<CardValue> ANY_CARD_VALUES =
            List.of(CardValue.TWO, CardValue.QUEEN, CardValue.NINE, CardValue.EIGHT, CardValue.ACE);

    public static List<CardValue> LOW_CARD_VALUES =
            List.of(CardValue.EIGHT, CardValue.SIX, CardValue.FIVE, CardValue.THREE, CardValue.TWO);

    public static List<CardValue> HIGH_CARD_VALUES =
            List.of(CardValue.ACE, CardValue.KING, CardValue.JACK, CardValue.TEN, CardValue.EIGHT);


    // Straight Values
    public static final List<CardValue> LOW_STRAIGHT_VALUES =
            List.of(CardValue.TWO, CardValue.THREE, CardValue.FOUR, CardValue.FIVE, CardValue.SIX);

    public static final List<CardValue> HIGH_STRAIGHT_VALUES =
            List.of(CardValue.TEN, CardValue.JACK, CardValue.QUEEN, CardValue.KING, CardValue.ACE);


    // Full House Values
    public static final List<CardValue> LOW_FULL_HOUSE_VALUES =
            List.of(CardValue.TWO, CardValue.TWO, CardValue.TWO, CardValue.ACE, CardValue.ACE);

    public static final List<CardValue> HIGH_FULL_HOUSE_VALUES =
            List.of(CardValue.ACE, CardValue.ACE, CardValue.ACE, CardValue.TWO, CardValue.TWO);


    // Four of a Kind Values
    public static final List<CardValue> LOW_FOUR_OF_A_KIND_VALUES =
            List.of(CardValue.TWO, CardValue.TWO, CardValue.TWO, CardValue.TWO, CardValue.ACE);

    public static final List<CardValue> HIGH_FOUR_OF_A_KIND_VALUES =
            List.of(CardValue.ACE, CardValue.ACE, CardValue.ACE, CardValue.ACE, CardValue.TWO);


    // Three of a Kind Values
    public static final List<CardValue> LOW_THREE_OF_A_KIND_VALUES =
            List.of(CardValue.TWO, CardValue.TWO, CardValue.TWO, CardValue.KING, CardValue.ACE);

    public static final List<CardValue> HIGH_THREE_OF_A_KIND_VALUES =
            List.of(CardValue.ACE, CardValue.ACE, CardValue.ACE, CardValue.THREE, CardValue.TWO);


    // Pair Values
    public static final List<CardValue> LOW_PAIR_VALUES =
            List.of(CardValue.TWO, CardValue.TWO, CardValue.QUEEN, CardValue.KING, CardValue.ACE);

    public static final List<CardValue> HIGH_PAIR_VALUES =
            List.of(CardValue.ACE, CardValue.ACE, CardValue.FOUR, CardValue.THREE, CardValue.TWO);

    public static final List<CardValue> PAIR_WITH_LOW_FOLLOW_UP_VALUES =
            List.of(CardValue.TEN, CardValue.TEN, CardValue.FOUR, CardValue.THREE, CardValue.TWO);

    public static final List<CardValue> PAIR_WITH_HIGH_FOLLOW_UP_VALUES =
            List.of(CardValue.TEN, CardValue.TEN, CardValue.ACE, CardValue.KING, CardValue.QUEEN);


    // Two Pair Values
    public static final List<CardValue> TWO_PAIR_WITH_LOW_FIRST_PAIR_VALUES =
            List.of(CardValue.TWO, CardValue.TWO, CardValue.TEN, CardValue.TEN, CardValue.NINE);

    public static final List<CardValue> TWO_PAIR_WITH_HIGH_FIRST_PAIR_VALUES =
            List.of(CardValue.ACE, CardValue.ACE, CardValue.TEN, CardValue.TEN, CardValue.NINE);

    public static final List<CardValue> TWO_PAIR_WITH_LOW_SECOND_PAIR_VALUES =
            List.of(CardValue.TEN, CardValue.TEN, CardValue.TWO, CardValue.TWO, CardValue.NINE);

    public static final List<CardValue> TWO_PAIR_WITH_HIGH_SECOND_PAIR_VALUES =
            List.of(CardValue.TEN, CardValue.TEN, CardValue.ACE, CardValue.ACE, CardValue.NINE);

    public static final List<CardValue> TWO_PAIR_WITH_HIGH_FOLLOW_UP_VALUE_VALUES =
            List.of(CardValue.TEN, CardValue.TEN, CardValue.NINE, CardValue.NINE, CardValue.ACE);

    public static final List<CardValue> TWO_PAIR_WITH_LOW_FOLLOW_UP_VALUE_VALUES =
            List.of(CardValue.TEN, CardValue.TEN, CardValue.NINE, CardValue.NINE, CardValue.TWO);


    // High Card Values
    public static final List<CardValue> HIGH_CARD_VALUES_HIGH_HIGH_HIGH_HIGH_HIGH =
            List.of(CardValue.ACE, CardValue.KING, CardValue.QUEEN, CardValue.JACK, CardValue.NINE);

    public static final List<CardValue> HIGH_CARD_VALUES_HIGH_HIGH_HIGH_HIGH_LOW =
            List.of(CardValue.ACE, CardValue.KING, CardValue.QUEEN, CardValue.JACK, CardValue.TWO);

    public static final List<CardValue> HIGH_CARD_VALUES_HIGH_HIGH_HIGH_LOW_LOW =
            List.of(CardValue.ACE, CardValue.KING, CardValue.QUEEN, CardValue.THREE, CardValue.TWO);

    public static final List<CardValue> HIGH_CARD_VALUES_HIGH_HIGH_LOW_LOW_LOW =
            List.of(CardValue.ACE, CardValue.KING, CardValue.FOUR, CardValue.THREE, CardValue.TWO);

    public static final List<CardValue> HIGH_CARD_VALUES_HIGH_LOW_LOW_LOW_LOW =
            List.of(CardValue.ACE, CardValue.FIVE, CardValue.FOUR, CardValue.THREE, CardValue.TWO);

    public static final List<CardValue> HIGH_CARD_VALUES_LOW_LOW_LOW_LOW_LOW =
            List.of(CardValue.SEVEN, CardValue.FIVE, CardValue.FOUR, CardValue.THREE, CardValue.TWO);


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
