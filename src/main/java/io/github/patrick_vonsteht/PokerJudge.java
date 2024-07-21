package io.github.patrick_vonsteht;

import java.util.Arrays;
import java.util.List;

public class PokerJudge {
    private final List<PokerHandComparisonRule> rules;

    public PokerJudge(final PokerHandComparisonRule... rulesInDescendingPriorityOrder) { // NOPMD Long parameter name is needed to convey semantics.
        this.rules = Arrays.stream(rulesInDescendingPriorityOrder).toList();
    }

    public PokerJudgeResult judge(final PokerHand hand1, final PokerHand hand2) {
        for (final PokerHandComparisonRule rule : rules) {

            final RuleResult result = rule.compare(hand1, hand2);

            if (result.getType() == RuleResultType.WINNER_MATCH) {
                return PokerJudgeResult.winnerRuleResult(result.getWinner());
            }

            if (result.getType() == RuleResultType.DRAW_MATCH) {
                return PokerJudgeResult.drawRuleResult();
            }
        }

        return PokerJudgeResult.drawRuleResult();
    }
}
