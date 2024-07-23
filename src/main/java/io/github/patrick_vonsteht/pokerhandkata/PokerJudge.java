package io.github.patrick_vonsteht.pokerhandkata;

import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;
import io.github.patrick_vonsteht.pokerhandkata.model.PokerJudgeResult;
import io.github.patrick_vonsteht.pokerhandkata.rules.ComparisonRule;
import io.github.patrick_vonsteht.pokerhandkata.model.ComparisonRuleResult;
import io.github.patrick_vonsteht.pokerhandkata.model.ComparisonRuleResultType;

import java.util.Arrays;
import java.util.List;

/**
 * PokerJudge compares two PokerHands according to an ordered set of PokerHandComparisonRules.
 * The first PokerHandComparisonRule that matches with either a win or a draw determines the comparison result.
 * If no rule matches, this counts as a draw.
 */
public class PokerJudge {
    private final List<ComparisonRule> rules;

    public PokerJudge(final ComparisonRule... rulesInDescendingPriorityOrder) { // NOPMD Long parameter name is needed to convey semantics.
        this.rules = Arrays.stream(rulesInDescendingPriorityOrder).toList();
    }

    /**
     *  Compares the PokerHands and returns one of the following results:
     *  * Draw Match: The PokerHands have the same rank according to the PokerJudge's rules.
     *  * Winner Match: One of the PokerHands ranks higher according to the PokerJudge's rules.
     */
    public PokerJudgeResult judge(final PokerHand hand1, final PokerHand hand2) {
        for (final ComparisonRule rule : rules) {

            final ComparisonRuleResult result = rule.compare(hand1, hand2);

            if (result.getType() == ComparisonRuleResultType.WINNER_MATCH) {
                return PokerJudgeResult.winnerResult(result.getWinner());
            }

            if (result.getType() == ComparisonRuleResultType.DRAW_MATCH) {
                return PokerJudgeResult.drawResult();
            }
        }

        return PokerJudgeResult.drawResult();
    }
}
