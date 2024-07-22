package io.github.patrick_vonsteht.pokerhandkata.matchers;

import io.github.patrick_vonsteht.pokerhandkata.model.Card;
import io.github.patrick_vonsteht.pokerhandkata.model.CardSuit;
import io.github.patrick_vonsteht.pokerhandkata.model.CardValue;
import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;

import java.util.List;

public final class PokerHandTestHelper {

    private PokerHandTestHelper() {}

    public static PokerHand createHandFromSuits(List<CardSuit> handSuits) {
        return new PokerHand(
                new Card(handSuits.get(0), CardValue.TWO),
                new Card(handSuits.get(1), CardValue.FOUR),
                new Card(handSuits.get(2), CardValue.JACK),
                new Card(handSuits.get(3), CardValue.FIVE),
                new Card(handSuits.get(4), CardValue.SIX)
        );
    }

    public static PokerHand createHandFromValues(List<CardValue> handValues) {
        return new PokerHand(
                new Card(CardSuit.DIAMONDS, handValues.get(0)),
                new Card(CardSuit.CLUBS, handValues.get(1)),
                new Card(CardSuit.SPADES, handValues.get(2)),
                new Card(CardSuit.CLUBS, handValues.get(3)),
                new Card(CardSuit.HEARTS, handValues.get(4)));
    }
}
