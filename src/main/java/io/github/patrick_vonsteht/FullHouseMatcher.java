package io.github.patrick_vonsteht;

import java.util.List;

public class FullHouseMatcher implements PokerHandMatcher {

    @Override
    public boolean matches(final PokerHand hand) {
        final List<Integer> values = hand.stream().map(Card::getNumericValue).sorted().toList();

        return hasLowFullHouse(values) || hasHighFullHouse(values);
    }

    private boolean hasLowFullHouse(final List<Integer> values) {
        return hasLowThreeOfAKind(values) && hasHighTwoOfAKind(values);
    }

    private boolean hasHighFullHouse(final List<Integer> values) {
        return hasLowTwoOfAKind(values) && hasHighThreeOfAKind(values);
    }

    private boolean hasLowThreeOfAKind(final List<Integer> values) {
        return areEqual(values.get(0), values.get(1), values.get(2));
    }

    private boolean hasHighThreeOfAKind(final List<Integer> values) {
        return areEqual(values.get(2), values.get(3), values.get(4));
    }

    private boolean hasLowTwoOfAKind(final List<Integer> values) {
        return areEqual(values.get(0), values.get(1));
    }

    private boolean hasHighTwoOfAKind(final List<Integer> values) {
        return areEqual(values.get(3), values.get(4));
    }

    private boolean areEqual(final int value1, final int value2) {
        return value1 == value2;
    }

    private boolean areEqual(final int value1, final int value2, final int value3) {
        return value1 == value2 && value2 == value3;
    }
}
