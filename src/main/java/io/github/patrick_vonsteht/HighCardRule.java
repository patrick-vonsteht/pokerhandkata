package io.github.patrick_vonsteht;

import java.util.Iterator;

public class HighCardRule implements PokerHandComparisonRule {

    private final PokerHandScorer highCardScorer;

    public HighCardRule(final PokerHandScorer highCardScorer) {
        this.highCardScorer = highCardScorer;
    }

    @Override
    public RuleResult compare(final PokerHand hand1, final PokerHand hand2) {
        final Iterator<Integer> scores1 = highCardScorer.score(hand1).iterator();
        final Iterator<Integer> scores2 = highCardScorer.score(hand2).iterator();

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
