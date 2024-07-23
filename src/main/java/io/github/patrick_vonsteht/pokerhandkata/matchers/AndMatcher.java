package io.github.patrick_vonsteht.pokerhandkata.matchers;

import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;

import java.util.Arrays;
import java.util.List;

/**
 * AndMatcher matches a hand when all of its child matchers match the hand.
 */
public class AndMatcher implements PokerHandMatcher {

    private final List<PokerHandMatcher> matchers;

    public AndMatcher(final PokerHandMatcher... matchers) {
        this.matchers = Arrays.stream(matchers).toList();
    }

    @Override
    public boolean matches(final PokerHand hand) {
        return matchers.stream().allMatch(matcher -> matcher.matches(hand));
    }
}
