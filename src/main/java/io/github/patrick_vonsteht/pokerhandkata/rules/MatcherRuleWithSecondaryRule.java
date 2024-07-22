package io.github.patrick_vonsteht.pokerhandkata.rules;

import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;
import io.github.patrick_vonsteht.pokerhandkata.matchers.PokerHandMatcher;
import io.github.patrick_vonsteht.pokerhandkata.model.RuleResult;

public class MatcherRuleWithSecondaryRule implements PokerHandComparisonRule {
    private final PokerHandMatcher matcher;
    private final PokerHandComparisonRule secondaryRule;

    public MatcherRuleWithSecondaryRule(final PokerHandMatcher matcher, final PokerHandComparisonRule secondaryRule) {
        this.matcher = matcher;
        this.secondaryRule = secondaryRule;
    }

    @Override
    public RuleResult compare(final PokerHand hand1, final PokerHand hand2) {
        final boolean hand1Matches = matcher.matches(hand1);
        final boolean hand2Matches = matcher.matches(hand2);

        final boolean noHandMatches = !hand1Matches && !hand2Matches;
        final boolean onlyHand1Matches = hand1Matches && !hand2Matches;
        final boolean onlyHand2Matches = !hand1Matches && hand2Matches;

        if (noHandMatches) {
            return RuleResult.noMatchRuleResult();
        }

        if (onlyHand1Matches) {
            return RuleResult.winnerRuleResult(hand1);
        }

        if (onlyHand2Matches) {
            return RuleResult.winnerRuleResult(hand2);
        }

        return secondaryRule.compare(hand1, hand2);
    }
}
