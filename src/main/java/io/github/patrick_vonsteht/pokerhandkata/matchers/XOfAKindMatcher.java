package io.github.patrick_vonsteht.pokerhandkata.matchers;

import io.github.patrick_vonsteht.pokerhandkata.model.Card;
import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;

import java.util.function.Function;
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
    private final Function<Card, Integer> matchProperty;

    public XOfAKindMatcher(final int matchLength, final int minMatches, final int maxMatches) {
        this.matchLength = matchLength;
        this.minMatches = minMatches;
        this.maxMatches = maxMatches;
        this.matchProperty = Card::numericValue;
    }

    public XOfAKindMatcher(final int matchLength, final int minMatches, final int maxMatches,
                           final Function<Card, Integer> matchProperty) {
        this.matchLength = matchLength;
        this.minMatches = minMatches;
        this.maxMatches = maxMatches;
        this.matchProperty = matchProperty;
    }

    @Override
    public boolean matches(final PokerHand hand) {
        final long numMatches = hand.stream()
                .collect(Collectors.groupingBy(matchProperty, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == matchLength)
                .count();

        return numMatches >= minMatches && numMatches <= maxMatches;
    }
}
