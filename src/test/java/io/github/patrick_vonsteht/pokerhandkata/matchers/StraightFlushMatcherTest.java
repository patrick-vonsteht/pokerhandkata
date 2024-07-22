package io.github.patrick_vonsteht.pokerhandkata.matchers;

import io.github.patrick_vonsteht.pokerhandkata.model.Card;
import io.github.patrick_vonsteht.pokerhandkata.model.CardSuit;
import io.github.patrick_vonsteht.pokerhandkata.model.CardValue;
import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StraightFlushMatcherTest {

    @Test
    void StraightFlushMatcherMatchesStraightFlush() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.CLUBS, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.FOUR),
                new Card(CardSuit.CLUBS, CardValue.THREE),
                new Card(CardSuit.CLUBS, CardValue.FIVE),
                new Card(CardSuit.CLUBS, CardValue.SIX)
        );

        assertStraightFlushMatcherResult(hand, true);
    }

    @Test
    void StraightFlushMatcherDoesNotMatchNonStraightFlush() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.CLUBS, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.QUEEN),
                new Card(CardSuit.CLUBS, CardValue.FOUR),
                new Card(CardSuit.CLUBS, CardValue.FIVE),
                new Card(CardSuit.CLUBS, CardValue.SIX)
        );

        assertStraightFlushMatcherResult(hand, false);
    }

    @Test
    void StraightFlushMatcherDoesNotMatchNonFlushStraight() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.CLUBS, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.THREE),
                new Card(CardSuit.DIAMONDS, CardValue.FOUR),
                new Card(CardSuit.CLUBS, CardValue.FIVE),
                new Card(CardSuit.CLUBS, CardValue.SIX)
        );

        assertStraightFlushMatcherResult(hand, false);
    }

    @Test
    void StraightFlushMatcherDoesNotMatchOtherHand() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.CLUBS, CardValue.TWO),
                new Card(CardSuit.DIAMONDS, CardValue.QUEEN),
                new Card(CardSuit.CLUBS, CardValue.FOUR),
                new Card(CardSuit.SPADES, CardValue.FIVE),
                new Card(CardSuit.CLUBS, CardValue.SIX)
        );

        assertStraightFlushMatcherResult(hand, false);
    }

    private void assertStraightFlushMatcherResult(PokerHand hand, boolean expectedResult) {
        final PokerHandMatcher straightMatcher = new StraightMatcher();
        final PokerHandMatcher flushMatcher = new FlushMatcher();
        final PokerHandMatcher straightFlushMatcher = new AndMatcher(straightMatcher, flushMatcher);

        assertEquals(expectedResult, straightFlushMatcher.matches(hand));
    }
}