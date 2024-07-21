package io.github.patrick_vonsteht;

import java.util.List;

public class FourOfAKindMatcher implements PokerHandMatcher {

    @Override
    public boolean matches(final PokerHand hand) {
        final List<Integer> values = hand.stream().map(Card::getNumericValue).sorted().toList();

        return hasLowFourOfAKind(values) || hasHighFourOfAKind(values);
    }

    private boolean hasLowFourOfAKind(final List<Integer> values) {
        return areEqual(values.get(0), values.get(1), values.get(2), values.get(3));
    }

    private boolean hasHighFourOfAKind(final List<Integer> values) {
        return areEqual(values.get(1), values.get(2), values.get(3), values.get(4));
    }

    private boolean areEqual(final int value1, final int value2, final int value3, final int value4) {
        return value1 == value2 && value2 == value3 && value3 == value4;
    }
}
