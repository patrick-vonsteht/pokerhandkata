package io.github.patrick_vonsteht;

public class StraightFlushMatcher implements PokerHandMatcher {

    private final PokerHandMatcher straightMatcher;
    private final PokerHandMatcher flushMatcher;

    public StraightFlushMatcher(final PokerHandMatcher straightMatcher, final PokerHandMatcher flushMatcher) {
        this.straightMatcher = straightMatcher;
        this.flushMatcher = flushMatcher;
    }

    @Override
    public boolean matches(final PokerHand hand) {
        return straightMatcher.matches(hand) && flushMatcher.matches(hand);
    }
}
