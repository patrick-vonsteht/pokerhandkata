package io.github.patrick_vonsteht;

import java.util.List;
import java.util.stream.Stream;

public class PokerHand {
    private final List<Card> cards;

    public PokerHand(final Card card1, final Card card2, final Card card3, final Card card4, final Card card5) {
        cards = List.of(card1, card2, card3, card4, card5);
    }

    public Stream<Card> stream() {
        return cards.stream();
    }
}
