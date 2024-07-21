package io.github.patrick_vonsteht;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HighCardScorerTest {

    @Test
    void HighCardScorerReturnsCardValuesfromHighestToLowest() {
        final PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.FOUR),
                new Card(CardSuit.SPADES, CardValue.JACK),
                new Card(CardSuit.CLUBS, CardValue.KING),
                new Card(CardSuit.HEARTS, CardValue.SIX)
        );

        List<Integer> expectedScores = List.of(
                CardValue.KING.getNumericValue(),
                CardValue.JACK.getNumericValue(),
                CardValue.SIX.getNumericValue(),
                CardValue.FOUR.getNumericValue(),
                CardValue.TWO.getNumericValue()
        );

        PokerHandScorer scorer = new XOfAKindScorer(1);
        List<Integer> actualScores = scorer.score(hand).toList();

        assertIterableEquals(expectedScores, actualScores);
    }

}