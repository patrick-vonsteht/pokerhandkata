package io.github.patrick_vonsteht;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

class PokerHand {
    private final List<Card> cards;

    public PokerHand(final Card card1, final Card card2, final Card card3, final Card card4, final Card card5) {
        cards = Stream.of(card1, card2, card3, card4, card5)
                .sorted(Comparator.comparing(Card::getNumericValue))
                .toList();
    }

    public Iterator<Card> sortedByValueIterator() {
        return cards.iterator();
    }
}
