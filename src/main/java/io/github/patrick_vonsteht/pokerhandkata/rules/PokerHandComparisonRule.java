package io.github.patrick_vonsteht.pokerhandkata.rules;

import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;
import io.github.patrick_vonsteht.pokerhandkata.model.RuleResult;

public interface PokerHandComparisonRule {
    RuleResult compare(PokerHand hand1, PokerHand hand2);
}