package io.github.patrick_vonsteht.pokerhandkata.scorers;

import io.github.patrick_vonsteht.pokerhandkata.model.Card;
import io.github.patrick_vonsteht.pokerhandkata.model.CardSuit;
import io.github.patrick_vonsteht.pokerhandkata.model.CardValue;
import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class XOfAKindScorerTest {

    @Test
    void MultipleOneOfAKindScorerReturnsCardValuesFromHighestToLowest() {
        assertScorerResult(1,1,5,
                List.of(CardValue.TWO, CardValue.FOUR, CardValue.JACK, CardValue.KING, CardValue.SIX),
                List.of(CardValue.KING, CardValue.JACK, CardValue.SIX, CardValue.FOUR, CardValue.TWO)
        );
    }

    @Test
    void SingleTwoOfAKindScorerReturnsValueOfMatchThenValuesOfOtherCardsFromHighestToLowest() {
        assertScorerResult(2,1,1,
                List.of(CardValue.TWO, CardValue.JACK, CardValue.JACK, CardValue.KING, CardValue.SIX),
                List.of(CardValue.JACK, CardValue.KING, CardValue.SIX, CardValue.TWO)
        );
    }

    @Test
    void TwoTwoOfAKindScorerReturnsValueOfMatchesThenValuesOfOtherCardFromHighestToLowest() {
        assertScorerResult(2,2,2,
                List.of(CardValue.SIX, CardValue.JACK, CardValue.JACK, CardValue.KING, CardValue.SIX),
                List.of(CardValue.JACK, CardValue.SIX, CardValue.KING)
        );
    }

    @Test
    void MultipleTwoOfAKindScorerReturnsValueOfSingleMatchThenValuesOfOtherCardFromHighestToLowest() {
        assertScorerResult(2,1,2,
                List.of(CardValue.TWO, CardValue.JACK, CardValue.JACK, CardValue.KING, CardValue.SIX),
                List.of(CardValue.JACK, CardValue.KING, CardValue.SIX, CardValue.TWO)
        );
    }

    @Test
    void MultipleTwoOfAKindScorerReturnsValueOfTwoMatchesThenValuesOfOtherCardFromHighestToLowest() {
        assertScorerResult(2,1,2,
                List.of(CardValue.SIX, CardValue.JACK, CardValue.JACK, CardValue.KING, CardValue.SIX),
                List.of(CardValue.JACK, CardValue.SIX, CardValue.KING)
        );
    }

    @Test
    void ThreeOfAKindScorerReturnsValueOfMatchThenValuesOfOtherCardsFromHighestToLowest() {
        assertScorerResult(3,1,1,
                List.of(CardValue.TWO, CardValue.JACK, CardValue.JACK, CardValue.KING, CardValue.JACK),
                List.of(CardValue.JACK, CardValue.KING, CardValue.TWO)
        );
    }

    @Test
    void FourOfAKindScorerReturnsValueOfMatchThenValuesOfOtherCardsFromHighestToLowest() {
        assertScorerResult(4,1,1,
                List.of(CardValue.JACK, CardValue.JACK, CardValue.JACK, CardValue.KING, CardValue.JACK),
                List.of(CardValue.JACK, CardValue.KING)
        );
    }

    @Test
    void FiveOfAKindScorerReturnsValueOfMatchFromHighestToLowest() {
        assertScorerResult(5,1,1,
                List.of(CardValue.JACK, CardValue.JACK, CardValue.JACK, CardValue.JACK, CardValue.JACK),
                List.of(CardValue.JACK)
        );
    }

    @Test
    void XOfAKindScorerThrowsExceptionWhenTooManyMatches() {
        final PokerHand hand = PokerHand.fromCards(
                new Card(CardSuit.DIAMONDS, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.FOUR),
                new Card(CardSuit.SPADES, CardValue.JACK),
                new Card(CardSuit.CLUBS, CardValue.KING),
                new Card(CardSuit.HEARTS, CardValue.SIX)
        );

        Scorer scorer = new XOfAKindScorer(1, 1, 1);
        assertThrows(IllegalArgumentException.class, () -> scorer.score(hand));
    }

    @Test
    void XOfAKindScorerThrowsExceptionWhenTooFewMatches() {
        final PokerHand hand = PokerHand.fromCards(
                new Card(CardSuit.DIAMONDS, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.FOUR),
                new Card(CardSuit.SPADES, CardValue.JACK),
                new Card(CardSuit.CLUBS, CardValue.KING),
                new Card(CardSuit.HEARTS, CardValue.SIX)
        );

        Scorer scorer = new XOfAKindScorer(2, 1, 1);
        assertThrows(IllegalArgumentException.class, () -> scorer.score(hand));
    }

    private void assertScorerResult(int matchLength, int minMatches, int maxMatches, List<CardValue> handValues,
                                    List<CardValue> expectedScores) {
        final PokerHand hand = PokerHand.fromCards(
                new Card(CardSuit.DIAMONDS, handValues.get(0)),
                new Card(CardSuit.CLUBS, handValues.get(1)),
                new Card(CardSuit.SPADES, handValues.get(2)),
                new Card(CardSuit.CLUBS, handValues.get(3)),
                new Card(CardSuit.HEARTS, handValues.get(4))
        );

        List<Integer> expectedScoreValues = expectedScores.stream().map(CardValue::numericValue).toList();

        Scorer scorer = new XOfAKindScorer(matchLength, minMatches, maxMatches);
        List<Integer> actualScores = scorer.score(hand).toList();

        assertIterableEquals(expectedScoreValues, actualScores);
    }
}