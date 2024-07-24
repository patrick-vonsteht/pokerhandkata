package io.github.patrick_vonsteht.pokerhandkata;

import io.github.patrick_vonsteht.pokerhandkata.model.CardSuit;
import io.github.patrick_vonsteht.pokerhandkata.model.CardValue;

import java.util.List;

public final class TestValues {
    // Any Suits
    public static List<CardSuit> ANY_CARD_SUITS =
            List.of(CardSuit.DIAMONDS, CardSuit.CLUBS, CardSuit.SPADES, CardSuit.DIAMONDS, CardSuit.HEARTS);

    // Flush Suits
    public static List<CardSuit> FLUSH_SUITS =
            List.of(CardSuit.DIAMONDS, CardSuit.DIAMONDS, CardSuit.DIAMONDS, CardSuit.DIAMONDS, CardSuit.DIAMONDS);


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

    public static final List<CardValue> MIDDLE_STRAIGHT_VALUES =
            List.of(CardValue.FIVE, CardValue.SIX, CardValue.SEVEN, CardValue.EIGHT, CardValue.NINE);

    public static final List<CardValue> HIGH_STRAIGHT_VALUES =
            List.of(CardValue.TEN, CardValue.JACK, CardValue.QUEEN, CardValue.KING, CardValue.ACE);


    // Full House Values
    public static final List<CardValue> LOW_FULL_HOUSE_VALUES =
            List.of(CardValue.TWO, CardValue.TWO, CardValue.TWO, CardValue.ACE, CardValue.ACE);

    public static final List<CardValue> HIGH_FULL_HOUSE_VALUES =
            List.of(CardValue.ACE, CardValue.ACE, CardValue.ACE, CardValue.TWO, CardValue.TWO);


    // Five of a Kind Values
    public static final List<CardValue> HIGH_FIVE_OF_A_KIND_VALUES =
            List.of(CardValue.THREE, CardValue.THREE, CardValue.THREE, CardValue.THREE, CardValue.THREE);

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
}
