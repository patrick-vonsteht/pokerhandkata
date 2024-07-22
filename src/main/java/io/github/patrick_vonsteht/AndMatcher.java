package io.github.patrick_vonsteht;

import java.util.Arrays;
import java.util.List;

public class AndMatcher implements PokerHandMatcher {

    private final List<PokerHandMatcher> matchers;

    public AndMatcher(PokerHandMatcher... matchers) {
        this.matchers = Arrays.stream(matchers).toList();
    }

    @Override
    public boolean matches(PokerHand hand) {
        return matchers.stream().allMatch(matcher -> matcher.matches(hand));
    }
}
