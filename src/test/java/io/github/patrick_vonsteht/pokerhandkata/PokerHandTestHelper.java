package io.github.patrick_vonsteht.pokerhandkata;

import io.github.patrick_vonsteht.pokerhandkata.model.Card;
import io.github.patrick_vonsteht.pokerhandkata.model.CardSuit;
import io.github.patrick_vonsteht.pokerhandkata.model.CardValue;
import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;

import java.util.List;

public final class PokerHandTestHelper {

    private PokerHandTestHelper() {}

    public static PokerHand create() {
        return PokerHand.fromCards(
                new Card(CardSuit.DIAMONDS, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.FOUR),
                new Card(CardSuit.SPADES, CardValue.JACK),
                new Card(CardSuit.CLUBS, CardValue.FIVE),
                new Card(CardSuit.SPADES, CardValue.SIX)
        );
    }

    public static PokerHand createHandFromSuits(List<CardSuit> handSuits) {
        return PokerHand.fromCards(
                new Card(handSuits.get(0), CardValue.TWO),
                new Card(handSuits.get(1), CardValue.FOUR),
                new Card(handSuits.get(2), CardValue.JACK),
                new Card(handSuits.get(3), CardValue.FIVE),
                new Card(handSuits.get(4), CardValue.SIX)
        );
    }

    public static PokerHand createHandFromValues(List<CardValue> handValues) {
        return PokerHand.fromCards(
                new Card(CardSuit.DIAMONDS, handValues.get(0)),
                new Card(CardSuit.CLUBS, handValues.get(1)),
                new Card(CardSuit.SPADES, handValues.get(2)),
                new Card(CardSuit.CLUBS, handValues.get(3)),
                new Card(CardSuit.HEARTS, handValues.get(4)));
    }

    public static PokerHand createHandFromSuitsAndValues(List<CardSuit> handSuits, List<CardValue> handValues) {
        return PokerHand.fromCards(
                new Card(handSuits.get(0), handValues.get(0)),
                new Card(handSuits.get(1), handValues.get(1)),
                new Card(handSuits.get(2), handValues.get(2)),
                new Card(handSuits.get(3), handValues.get(3)),
                new Card(handSuits.get(4), handValues.get(4)));
    }
}
