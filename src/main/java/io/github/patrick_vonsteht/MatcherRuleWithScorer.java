package io.github.patrick_vonsteht;

import java.util.Iterator;

public class MatcherRuleWithScorer implements PokerHandComparisonRule {
    private final PokerHandMatcher matcher;
    private final PokerHandScorer scorer;

    public MatcherRuleWithScorer(final PokerHandMatcher matcher, final PokerHandScorer scorer) {
        this.matcher = matcher;
        this.scorer = scorer;
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

        final Iterator<Integer> scores1 = scorer.score(hand1).iterator();
        final Iterator<Integer> scores2 = scorer.score(hand2).iterator();

        while (scores1.hasNext() && scores2.hasNext()) {
            final int score1 = scores1.next();
            final int score2 = scores2.next();

            if (score1 > score2) {
                return RuleResult.winnerRuleResult(hand1);
            }

            if (score1 < score2) {
                return RuleResult.winnerRuleResult(hand2);
            }
        }

        return RuleResult.drawRuleResult();
    }
}
