package io.github.patrick_vonsteht;

import java.util.List;

public class XOfAKindMatcher implements PokerHandMatcher {

    private final int numberOfSameCards;

    public XOfAKindMatcher(final int numberOfSameCards) {
        this.numberOfSameCards = numberOfSameCards;
    }

    @Override
    public boolean matches(final PokerHand hand) {
        final List<Integer> values = hand.stream().map(Card::getNumericValue).sorted().toList();

        for (int i=0; i <= values.size() - numberOfSameCards; i++) {
            if (hasXOfAKindStartingAtIndex(values, i)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasXOfAKindStartingAtIndex(final List<Integer> values, final int index) {
        for (int i=0; i < numberOfSameCards - 1; i++) {
            if (!values.get(index+i).equals(values.get(index+i+1))) {
                return false;
            }
        }
        return true;
    }
}
