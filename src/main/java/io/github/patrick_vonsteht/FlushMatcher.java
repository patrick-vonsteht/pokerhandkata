package io.github.patrick_vonsteht;

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
