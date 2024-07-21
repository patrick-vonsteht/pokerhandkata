package io.github.patrick_vonsteht;

import java.util.List;

public class StraightMatcher implements PokerHandMatcher {

    @Override
    public boolean matches(final PokerHand hand) {
        final List<Integer> cardValues = hand.stream()
                .map(Card::getNumericValue)
                .sorted()
                .toList();

        for (int i=0; i < cardValues.size() - 1; i++) {
            final int currentCardValue = cardValues.get(i);
            final int nextCardValue = cardValues.get(i+1);

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
