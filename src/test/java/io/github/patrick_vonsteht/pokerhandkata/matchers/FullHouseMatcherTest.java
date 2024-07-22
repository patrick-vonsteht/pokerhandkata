package io.github.patrick_vonsteht.pokerhandkata.matchers;

import io.github.patrick_vonsteht.pokerhandkata.model.Card;
import io.github.patrick_vonsteht.pokerhandkata.model.CardSuit;
import io.github.patrick_vonsteht.pokerhandkata.model.CardValue;
import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FullHouseMatcherTest {

    @Test
    void FullHouseMatcherMatchesLowFullHouse() {
        assertFullHouseMatcherResult(
                List.of(CardValue.TWO, CardValue.FOUR, CardValue.TWO, CardValue.TWO, CardValue.FOUR),
                true);
    }

    @Test
    void FullHouseMatcherMatchesHighFullHouse() {
        assertFullHouseMatcherResult(
                List.of(CardValue.TWO, CardValue.FOUR, CardValue.TWO, CardValue.FOUR, CardValue.FOUR),
                true);
    }

    @Test
    void FullHouseMatcherDoesNotMatchNonFullHouseThreeOfAKind() {
        assertFullHouseMatcherResult(
                List.of(CardValue.THREE, CardValue.FOUR, CardValue.FOUR, CardValue.FOUR, CardValue.FIVE),
                false);
    }

    @Test
    void FullHouseMatcherDoesNotMatchNonFullHouseTwoOfAKind() {
        assertFullHouseMatcherResult(
                List.of(CardValue.JACK, CardValue.JACK, CardValue.THREE, CardValue.FOUR, CardValue.FIVE),
                false);
    }

    @Test
    void FullHouseMatcherDoesNotMatchNonFullHouse() {
        assertFullHouseMatcherResult(
                List.of(CardValue.QUEEN, CardValue.JACK, CardValue.THREE, CardValue.NINE, CardValue.FIVE),
                false);
    }

    private void assertFullHouseMatcherResult(List<CardValue> handValues, boolean expectedResult) {
        final PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, handValues.get(0)),
                new Card(CardSuit.CLUBS, handValues.get(1)),
                new Card(CardSuit.SPADES, handValues.get(2)),
                new Card(CardSuit.CLUBS, handValues.get(3)),
                new Card(CardSuit.HEARTS, handValues.get(4))
        );

        final PokerHandMatcher threeOfAKindMatcher = new XOfAKindMatcher(3, 1, 1);
        final PokerHandMatcher twoOfAKindMatcher = new XOfAKindMatcher(2, 1, 1);
        final PokerHandMatcher fullHouseMatcher = new AndMatcher(threeOfAKindMatcher, twoOfAKindMatcher);

        assertEquals(expectedResult, fullHouseMatcher.matches(hand));
    }
}