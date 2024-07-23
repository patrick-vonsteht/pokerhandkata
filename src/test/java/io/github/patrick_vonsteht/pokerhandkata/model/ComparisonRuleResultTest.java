package io.github.patrick_vonsteht.pokerhandkata.model;

import io.github.patrick_vonsteht.pokerhandkata.PokerHandTestHelper;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ComparisonRuleResultTest {

    @Test
    void ThrowsIllegalArgumentExceptionWhenCreatingWinnerRuleResultWithNullWinner() {
        assertThrows(IllegalArgumentException.class, () -> ComparisonRuleResult.winnerRuleResult(null));
    }

    @Test
    void DoesNotThrowOnGetWinnerOnWinnerResult() {
        PokerHand winner = PokerHandTestHelper.create();
        assertDoesNotThrow(() -> ComparisonRuleResult.winnerRuleResult(winner).getWinner());
    }

    @Test
    void ThrowsUnsupportedOperationExceptionOnGetWinnerOnDrawResult() {
        assertThrows(UnsupportedOperationException.class, () -> ComparisonRuleResult.drawRuleResult().getWinner());
    }

    @Test
    void ThrowsUnsupportedOperationExceptionOnGetWinnerOnNoMatchResult() {
        assertThrows(UnsupportedOperationException.class, () -> ComparisonRuleResult.noMatchRuleResult().getWinner());
    }
}
