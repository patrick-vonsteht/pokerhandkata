package io.github.patrick_vonsteht.pokerhandkata.model;

/**
 * A PokerJudgeResult represents the result of comparing two poker hands according to the rules of a PokerJudge.
 */
public final class PokerJudgeResult {
    private final PokerJudgeResultType type;
    private final PokerHand winner;

    public static PokerJudgeResult drawResult() {
        return new PokerJudgeResult(PokerJudgeResultType.DRAW);
    }

    public static PokerJudgeResult winnerResult(final PokerHand winner) {
        if (winner == null) {
            throw new IllegalArgumentException("Winner must not be null on a winnerResult.");
        }

        return new PokerJudgeResult(PokerJudgeResultType.WINNER, winner);
    }

    private PokerJudgeResult(final PokerJudgeResultType type, final PokerHand winner) {
        this.type = type;
        this.winner = winner;
    }

    private PokerJudgeResult(final PokerJudgeResultType type) {
        this.type = type;
        this.winner = null;
    }

    public PokerJudgeResultType getType() {
        return type;
    }

    /**
     * Returns the winner of the comparison. This method is only supported on PokerJudgeResults of type WINNER.
     */
    public PokerHand getWinner() {
        if (this.type == PokerJudgeResultType.DRAW) {
            throw new UnsupportedOperationException("Invalid call to PokerJudgeResults.getWinner(). Only " +
                    "PokerJudgeResults with type WINNER support getWinner.");
        }

        return winner;
    }
}
