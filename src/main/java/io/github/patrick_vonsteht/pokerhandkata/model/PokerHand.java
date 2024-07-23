package io.github.patrick_vonsteht.pokerhandkata.model;

import java.util.List;
import java.util.stream.Stream;

/**
 * A PokerHand which consists of exactly five playing cards.
 */
public final class PokerHand {
    private final List<Card> cards;

    public static PokerHand fromCards(final Card card1, final Card card2, final Card card3, final Card card4, final Card card5) {
        if (card1 == null || card2 == null || card3 == null || card4 == null || card5 == null) {
            throw new IllegalArgumentException("PokerHand cards must not be null.");
        }

        return new PokerHand(card1, card2, card3, card4, card5);
    }

    private PokerHand(final Card card1, final Card card2, final Card card3, final Card card4, final Card card5) {
        cards = List.of(card1, card2, card3, card4, card5);
    }

    public Stream<Card> stream() {
        return cards.stream();
    }
}
