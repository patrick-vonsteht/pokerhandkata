package io.github.patrick_vonsteht.pokerhandkata.matchers;

import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;

public interface PokerHandMatcher {
    boolean matches(PokerHand hand);
}
