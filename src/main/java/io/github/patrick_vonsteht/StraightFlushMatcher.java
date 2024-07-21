package io.github.patrick_vonsteht;

public class StraightFlushMatcher implements PokerHandMatcher {

    PokerHandMatcher straightMatcher;
    PokerHandMatcher flushMatcher;

    public StraightFlushMatcher(PokerHandMatcher straightMatcher, PokerHandMatcher flushMatcher) {
        this.straightMatcher = straightMatcher;
        this.flushMatcher = flushMatcher;
    }

    @Override
    public boolean matches(PokerHand hand) {
        return straightMatcher.matches(hand) && flushMatcher.matches(hand);
    }
}
