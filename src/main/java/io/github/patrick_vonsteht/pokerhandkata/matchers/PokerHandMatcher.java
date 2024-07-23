package io.github.patrick_vonsteht.pokerhandkata.matchers;

import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;

/**
 * A PokerHandMatcher checks if a PokerHand matches a pattern.
 * For example, a PokerHandMatcher could check if a PokerHand contains three cards of the same value.
 */
public interface PokerHandMatcher {

    /**
     * Returns whether the PokerHand fulfills the matcher's conditions
     */
    boolean matches(PokerHand hand);
}
