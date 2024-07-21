package io.github.patrick_vonsteht;

public class FlushRule implements PokerHandComparisonRule {

    private final PokerHandComparisonRule highCardRule;
    private final PokerHandMatcher flushMatcher;

    public FlushRule(final PokerHandMatcher flushMatcher, final PokerHandComparisonRule highCardRule) {
        this.flushMatcher = flushMatcher;
        this.highCardRule = highCardRule;
    }

    @Override
    public RuleResult compare(final PokerHand hand1, final PokerHand hand2) {
        final boolean hand1IsFlush = flushMatcher.matches(hand1);
        final boolean hand2IsFlush = flushMatcher.matches(hand2);

        final boolean noHandMatches = !hand1IsFlush && !hand2IsFlush;
        final boolean onlyHand1Matches = hand1IsFlush && !hand2IsFlush;
        final boolean onlyHand2Matches = !hand1IsFlush && hand2IsFlush;

        if (noHandMatches) {
            return RuleResult.noMatchRuleResult();
        }

        if (onlyHand1Matches) {
            return RuleResult.winnerRuleResult(hand1);
        }

        if (onlyHand2Matches) {
            return RuleResult.winnerRuleResult(hand2);
        }

        return highCardRule.compare(hand1, hand2);
    }
}
