package io.github.patrick_vonsteht.pokerhandkata.matchers;

import io.github.patrick_vonsteht.pokerhandkata.model.Card;
import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;

import java.util.stream.Collectors;

/**
 * XOfAKindMatcher matches hands with which contain at least <minMatches> and at most <maxMatches> matches of
 * <matchLength> cards of the same value.
 *
 * Example:
 * * XOfAKindMatcher(2,1,1) matches any hand that has exactly one pair.
 * * XOfAKindMatcher(2,1,2) matches any hand that has one or two pairs.
 * * XOfAKindMatcher(2,2,2) matches any hand that has exactly two pairs.
 */
public class XOfAKindMatcher implements Matcher {

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
                .collect(Collectors.groupingBy(Card::numericValue, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == matchLength)
                .count();

        return numMatches >= minMatches && numMatches <= maxMatches;
    }
}
