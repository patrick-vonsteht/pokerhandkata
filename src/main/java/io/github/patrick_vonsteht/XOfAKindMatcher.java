package io.github.patrick_vonsteht;

import java.util.stream.Collectors;

public class XOfAKindMatcher implements PokerHandMatcher {

    private final int matchLength;
    private final int minMatches;
    private final int maxMatches;

    public XOfAKindMatcher(final int matchLength, final int minMatches, final int maxMatches) {
        this.matchLength = matchLength;
        this.minMatches = minMatches;
        this.maxMatches = maxMatches;
    }

    @Override
    public boolean matches(final PokerHand hand) {
        final long numMatches = hand.stream()
                .collect(Collectors.groupingBy(Card::getNumericValue, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == matchLength)
                .count();

        return numMatches >= minMatches && numMatches <= maxMatches;
    }
}
