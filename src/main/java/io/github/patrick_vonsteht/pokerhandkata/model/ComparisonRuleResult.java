package io.github.patrick_vonsteht.pokerhandkata.model;

/**
 * A ComparisonRuleResult represents the result of comparing two poker hands according to a single PokerHandComparisonRule.
 */
public final class ComparisonRuleResult {
    private final ComparisonRuleResultType type;
    private final PokerHand winner;

    public static ComparisonRuleResult noMatchRuleResult() {
        return new ComparisonRuleResult(ComparisonRuleResultType.NO_MATCH);
    }

    public static ComparisonRuleResult drawRuleResult() {
        return new ComparisonRuleResult(ComparisonRuleResultType.DRAW_MATCH);
    }

    public static ComparisonRuleResult winnerRuleResult(final PokerHand winner) {
        if (winner == null) {
            throw new IllegalArgumentException("Winner must not be null on a winnerRuleResult.");
        }

        return new ComparisonRuleResult(ComparisonRuleResultType.WINNER_MATCH, winner);
    }

    private ComparisonRuleResult(final ComparisonRuleResultType type, final PokerHand winner) {
        this.type = type;
        this.winner = winner;
    }

    private ComparisonRuleResult(final ComparisonRuleResultType type) {
        this.type = type;
        this.winner = null;
    }

    public ComparisonRuleResultType getType() {
        return type;
    }

    /**
     * Returns the winner of the comparison. This method is only supported on RuleResults of type WINNER_MATCH.
     */
    public PokerHand getWinner() {
        if (this.type == ComparisonRuleResultType.NO_MATCH || this.type == ComparisonRuleResultType.DRAW_MATCH) {
            throw new UnsupportedOperationException("Invalid call to ComparisonRuleResult.getWinner(). Only " +
                    "RuleResults with type WINNER_MATCH support getWinner.");
        }

        return winner;
    }
}
