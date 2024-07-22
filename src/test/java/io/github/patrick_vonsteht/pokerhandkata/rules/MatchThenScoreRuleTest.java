package io.github.patrick_vonsteht.pokerhandkata.rules;

import io.github.patrick_vonsteht.pokerhandkata.matchers.PokerHandMatcher;
import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;
import io.github.patrick_vonsteht.pokerhandkata.model.RuleResult;
import io.github.patrick_vonsteht.pokerhandkata.model.RuleResultType;
import io.github.patrick_vonsteht.pokerhandkata.scorers.PokerHandScorer;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MatchThenScoreRuleTest {

    private static final PokerHand mockedHand1 = mock(PokerHand.class);
    private static final PokerHand mockedHand2 = mock(PokerHand.class);

    @Test
    void ReturnsNoMatchWhenNoHandMatches() {
        assertNoMatch(false, false, null, null);
    }

    @Test
    void ReturnsHand1WhenOnlyHand1Matches() {
        assertWinner(true, false, null, null, mockedHand1);
    }

    @Test
    void ReturnsHand2WhenOnlyHand2Matches() {
        assertWinner(false, true, null, null, mockedHand2);
    }

    @Test
    void ReturnsHand1WhenBothHandsMatchAndHand1HasHigherFirstScore() {
        assertWinner(true, true, Stream.of(5), Stream.of(4), mockedHand1);
    }

    @Test
    void ReturnsHand2WhenBothHandsMatchAndHand2HasHigherFirstScore() {
        assertWinner(true, true, Stream.of(4), Stream.of(5), mockedHand2);
    }

    @Test
    void ReturnsHand1WhenBothHandsMatchFirstScoreIsEqualAndHand1HasHigherSecondScore() {
        assertWinner(true, true, Stream.of(5, 5), Stream.of(5, 4), mockedHand1);
    }

    @Test
    void ReturnsHand2WhenBothHandsMatchFirstScoreIsEqualAndHand2HasHigherSecondScore() {
        assertWinner(true, true, Stream.of(5, 4), Stream.of(5, 5), mockedHand2);
    }

    @Test
    void ThrowsUnsupportedOperationExceptionWhenBothHandsMatchFirstScoresAreEqualAndHand1HasMoreScores() {
        assertUnsupportedOperationException(true, true, Stream.of(5, 4, 3), Stream.of(5, 4));
    }

    @Test
    void ThrowsUnsupportedOperationExceptionWhenBothHandsMatchFirstScoresAreEqualAndHand2HasMoreScores() {
        assertUnsupportedOperationException(true, true, Stream.of(5, 4), Stream.of(5, 4, 3));
    }

    private void assertDraw(final boolean resultForHand1, final boolean resultForHand2,
                            final Stream<Integer> scoresForHand1, final Stream<Integer> scoresForHand2) {
        assertResult(resultForHand1, resultForHand2, scoresForHand1, scoresForHand2, RuleResultType.DRAW_MATCH, null);
    }

    private void assertNoMatch(final boolean resultForHand1, final boolean resultForHand2,
                               final Stream<Integer> scoresForHand1, final Stream<Integer> scoresForHand2) {
        assertResult(resultForHand1, resultForHand2, scoresForHand1, scoresForHand2, RuleResultType.NO_MATCH, null);
    }

    private void assertWinner(final boolean resultForHand1, final boolean resultForHand2,
                              final Stream<Integer> scoresForHand1, final Stream<Integer> scoresForHand2,
                              final PokerHand expectedWinner) {
        assertResult(resultForHand1, resultForHand2, scoresForHand1, scoresForHand2, RuleResultType.WINNER_MATCH, expectedWinner);
    }

    private void assertResult(final boolean resultForHand1, final boolean resultForHand2,
                             final Stream<Integer> scoresForHand1, final Stream<Integer> scoresForHand2,
                             final RuleResultType expectedResultType, final PokerHand expectedWinner) {

        var rule = createPokerHandComparisonRule(resultForHand1, resultForHand2, scoresForHand1, scoresForHand2);

        RuleResult result = rule.compare(mockedHand1, mockedHand2);

        assertEquals(expectedResultType, result.getType());

        if (expectedWinner != null) {
            assertEquals(expectedWinner, result.getWinner());
        }
    }

    private void assertUnsupportedOperationException(final boolean resultForHand1, final boolean resultForHand2,
                              final Stream<Integer> scoresForHand1, final Stream<Integer> scoresForHand2) {
        var rule = createPokerHandComparisonRule(resultForHand1, resultForHand2, scoresForHand1, scoresForHand2);

        assertThrows(UnsupportedOperationException.class, () -> rule.compare(mockedHand1, mockedHand2));
    }

    private static PokerHandComparisonRule createPokerHandComparisonRule(boolean resultForHand1, boolean resultForHand2,
                                                                         Stream<Integer> scoresForHand1,
                                                                         Stream<Integer> scoresForHand2) {
        PokerHandMatcher mockedMatcher = mock(PokerHandMatcher.class);
        when(mockedMatcher.matches(mockedHand1)).thenReturn(resultForHand1);
        when(mockedMatcher.matches(mockedHand2)).thenReturn(resultForHand2);

        PokerHandScorer mockedScorer = mock(PokerHandScorer.class);
        when(mockedScorer.score(mockedHand1)).thenReturn(scoresForHand1);
        when(mockedScorer.score(mockedHand2)).thenReturn(scoresForHand2);

        PokerHandComparisonRule rule = new MatchThenScoreRule(mockedMatcher, mockedScorer);
        return rule;
    }
}