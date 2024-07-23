package io.github.patrick_vonsteht.pokerhandkata.matchers;

import io.github.patrick_vonsteht.pokerhandkata.model.Card;
import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;

/**
 * Flush matcher matches hands with all cards of the same suit.
 */
public class FlushMatcher implements Matcher {

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
