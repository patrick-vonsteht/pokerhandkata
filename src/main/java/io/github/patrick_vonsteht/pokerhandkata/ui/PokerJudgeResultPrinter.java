package io.github.patrick_vonsteht.pokerhandkata.ui;

import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;
import io.github.patrick_vonsteht.pokerhandkata.model.PokerJudgeResult;
import io.github.patrick_vonsteht.pokerhandkata.model.PokerJudgeResultType;

/**
 * PokerJudgeResultPrinter prints a PokerJudgeResult to the terminal.
 */
public class PokerJudgeResultPrinter {
    public void print(final PokerJudgeResult result, final PokerHand hand1, final PokerHand hand2) {
        if (result.getType() == PokerJudgeResultType.WINNER) {
            if (result.getWinner().equals(hand1)) {
                System.out.println("Hand1 has won!");
            } else {
                System.out.println("Hand2 has won!");
            }
        } else {
            System.out.println("Draw between hand1 and hand2!");
        }
    }
}
