package io.github.patrick_vonsteht.pokerhandkata.model;

public enum ComparisonRuleResultType {
    /**
     * The rule didn't match any hand.
     */
    NO_MATCH,

    /**
     * The rule determined a winning hand.
     */
    WINNER_MATCH,

    /**
     * The rule determined a draw between the hands.
     */
    DRAW_MATCH;
}
