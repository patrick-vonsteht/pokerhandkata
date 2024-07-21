package io.github.patrick_vonsteht;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TwoPairMatcher implements PokerHandMatcher {

    @Override
    public boolean matches(final PokerHand hand) {
        final Map<Integer, List<Card>> groups = hand.stream().collect(Collectors.groupingBy(Card::getNumericValue));

        int countOfPairs = 0;
        for (final Map.Entry<Integer, List<Card>> entry : groups.entrySet()) {
            if (isPair(entry)) {
                countOfPairs++;
            }
        }

        return countOfPairs == 2;
    }

    private boolean isPair(final Map.Entry<Integer, List<Card>> entry) {
        return entry.getValue().size() >= 2;
    }
}
