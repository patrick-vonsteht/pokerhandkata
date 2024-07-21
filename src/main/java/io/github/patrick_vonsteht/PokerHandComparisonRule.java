package io.github.patrick_vonsteht;

public interface PokerHandComparisonRule {
    RuleResult compare(PokerHand hand1, PokerHand hand2);
}