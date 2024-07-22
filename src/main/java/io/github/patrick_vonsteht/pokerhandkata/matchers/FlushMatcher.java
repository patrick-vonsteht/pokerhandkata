package io.github.patrick_vonsteht.pokerhandkata.matchers;

import io.github.patrick_vonsteht.pokerhandkata.model.Card;
import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;

public class FlushMatcher implements PokerHandMatcher {

    @Override
    public boolean matches(final PokerHand hand) {
        final long distinctSuitCount = hand
                .stream()
                .map(Card::suit)
                .distinct()
                .count();

        return distinctSuitCount == 1;
    }
}
