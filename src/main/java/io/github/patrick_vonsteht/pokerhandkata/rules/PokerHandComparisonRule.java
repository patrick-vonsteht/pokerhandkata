package io.github.patrick_vonsteht.pokerhandkata.rules;

import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;
import io.github.patrick_vonsteht.pokerhandkata.model.RuleResult;

/**
 * A PokerHandComparisonRule compares to PokerHands.
 */
public interface PokerHandComparisonRule {

    /**
     *  Compares the PokerHands and returns one of the following results:
     *  * No Match: The PokerHandComparisonRule is not applicable to the given PokerHands.
     *  * Draw Match: The PokerHands have the same rank according to the PokerHandComparisonRule.
     *  * Winner Match: One of the PokerHands ranks higher according to the PokerHandComparisonRule.
     */
    RuleResult compare(PokerHand hand1, PokerHand hand2);
}