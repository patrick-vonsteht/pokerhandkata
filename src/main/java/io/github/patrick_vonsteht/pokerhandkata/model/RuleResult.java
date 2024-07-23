package io.github.patrick_vonsteht.pokerhandkata.model;

/**
 * A RuleResult represents the result of comparing two poker hands according to a single PokerHandComparisonRule.
 */
public final class RuleResult {
    private final RuleResultType type;
    private final PokerHand winner;

    public static RuleResult noMatchRuleResult() {
        return new RuleResult(RuleResultType.NO_MATCH);
    }

    public static RuleResult drawRuleResult() {
        return new RuleResult(RuleResultType.DRAW_MATCH);
    }

    public static RuleResult winnerRuleResult(final PokerHand winner) {
        if (winner == null) {
            throw new IllegalArgumentException("Winner must not be null on a winnerRuleResult.");
        }

        return new RuleResult(RuleResultType.WINNER_MATCH, winner);
    }

    private RuleResult(final RuleResultType type, final PokerHand winner) {
        this.type = type;
        this.winner = winner;
    }

    private RuleResult(final RuleResultType type) {
        this.type = type;
        this.winner = null;
    }

    public RuleResultType getType() {
        return type;
    }

    /**
     * Returns the winner of the comparison. This method is only supported on RuleResults of type WINNER_MATCH.
     */
    public PokerHand getWinner() {
        if (this.type == RuleResultType.NO_MATCH || this.type == RuleResultType.DRAW_MATCH) {
            throw new UnsupportedOperationException("Invalid call to RuleResult.getWinner(). Only RuleResults with " +
                    "type WINNER_MATCH support getWinner.");
        }

        return winner;
    }
}
