package io.github.patrick_vonsteht.pokerhandkata.model;

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

    public PokerHand getWinner() {
        return winner;
    }
}
