package io.github.patrick_vonsteht.pokerhandkata.matchers;

import io.github.patrick_vonsteht.pokerhandkata.model.Card;
import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;

import java.util.List;

/**
 * StraightMatcher matches hands with 5 cards of consecutive values.
 */
public class StraightMatcher implements Matcher {

    @Override
    public boolean matches(final PokerHand hand) {
        final List<Integer> sortedCardValues = hand.stream()
                .map(Card::numericValue)
                .sorted()
                .toList();

        for (int i=0; i < sortedCardValues.size() - 1; i++) {
            final int currentCardValue = sortedCardValues.get(i);
            final int nextCardValue = sortedCardValues.get(i+1);

            if (!areConsecutiveValues(currentCardValue, nextCardValue)) {
                return false;
            }
        }

        return true;
    }

    private boolean areConsecutiveValues(final int cardValue1, final int cardValue2) {
        return Math.abs(cardValue2 - cardValue1) == 1;
    }
}
