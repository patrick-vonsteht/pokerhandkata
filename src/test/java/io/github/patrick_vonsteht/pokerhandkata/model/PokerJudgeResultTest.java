package io.github.patrick_vonsteht.pokerhandkata.model;

import io.github.patrick_vonsteht.pokerhandkata.PokerHandTestHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PokerJudgeResultTest {

    @Test
    void ThrowsIllegalArgumentExceptionWhenCreatingWinnerPokerJudgeResultWithNullWinner() {
        assertThrows(IllegalArgumentException.class, () -> PokerJudgeResult.winnerResult(null));
    }

    @Test
    void DoesNotThrowOnGetWinnerOnWinnerResult() {
        PokerHand winner = PokerHandTestHelper.create();
        assertDoesNotThrow(() -> PokerJudgeResult.winnerResult(winner).getWinner());
    }

    @Test
    void ThrowsUnsupportedOperationExceptionOnGetWinnerOnDrawResult() {
        assertThrows(UnsupportedOperationException.class, () -> PokerJudgeResult.drawResult().getWinner());
    }
}
