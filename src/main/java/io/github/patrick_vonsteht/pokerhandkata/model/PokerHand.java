package io.github.patrick_vonsteht.pokerhandkata.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class PokerHand {
    private final List<Card> cards;

    public PokerHand(final Card card1, final Card card2, final Card card3, final Card card4, final Card card5) {
        final String nullMessage = "PokerHand cards must not be null";
        Objects.requireNonNull(card1, nullMessage);
        Objects.requireNonNull(card2, nullMessage);
        Objects.requireNonNull(card3, nullMessage);
        Objects.requireNonNull(card4, nullMessage);
        Objects.requireNonNull(card5, nullMessage);

        cards = List.of(card1, card2, card3, card4, card5);
    }

    public Stream<Card> stream() {
        return cards.stream();
    }
}
