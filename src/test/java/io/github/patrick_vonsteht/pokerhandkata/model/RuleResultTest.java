package io.github.patrick_vonsteht.pokerhandkata.model;

import io.github.patrick_vonsteht.pokerhandkata.PokerHandTestHelper;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class RuleResultTest {

    @Test
    void ThrowsIllegalArgumentExceptionWhenCreatingWinnerRuleResultWithNullWinner() {
        assertThrows(IllegalArgumentException.class, () -> RuleResult.winnerRuleResult(null));
    }

    @Test
    void DoesNotThrowOnGetWinnerOnWinnerResult() {
        PokerHand winner = PokerHandTestHelper.create();
        assertDoesNotThrow(() -> RuleResult.winnerRuleResult(winner).getWinner());
    }

    @Test
    void ThrowsUnsupportedOperationExceptionOnGetWinnerOnDrawResult() {
        assertThrows(UnsupportedOperationException.class, () -> RuleResult.drawRuleResult().getWinner());
    }

    @Test
    void ThrowsUnsupportedOperationExceptionOnGetWinnerOnNoMatchResult() {
        assertThrows(UnsupportedOperationException.class, () -> RuleResult.noMatchRuleResult().getWinner());
    }
}
