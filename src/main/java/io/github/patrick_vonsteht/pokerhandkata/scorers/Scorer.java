package io.github.patrick_vonsteht.pokerhandkata.scorers;

import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;

import java.util.stream.Stream;

/**
 * A PokerHandScorer determines the scores for a PokerHand that matches a pattern.
 *
 * PokerHandScorer implementations assume that the hand matches the pattern for which they determine the scores.
 * Running a scorer for a hand that doesn't match its pattern yields an undefined result.
 * Usually, you'll first run a PokerHandMatcher to verify that the PokerHand matches a pattern, and then run the
 * corresponding scorer to get the scores.
 */
public interface Scorer {

    /**
     * Returns a stream of one or more scores for the PokerHand.
     * Multiple scores are returned in order of precedence from highest to lowest.
     * To compare two score lists, compare the counts in stream order, only considering later scores if earlier scores
     * are eaual.
     */
    Stream<Integer> score(PokerHand hand);
}
