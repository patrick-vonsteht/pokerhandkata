package io.github.patrick_vonsteht;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwoPairMatcherTest {

    @Test
    void TwoPairMatcherMatchesTwoPairs() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.TWO),
                new Card(CardSuit.HEARTS, CardValue.FOUR),
                new Card(CardSuit.SPADES, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.FOUR),
                new Card(CardSuit.CLUBS, CardValue.THREE)
        );

        PokerHandMatcher matcher = new TwoPairMatcher();
        assertTrue(matcher.matches(hand));
    }

    @Test
    void TwoPairMatcherMatchesFullHouse() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.TWO),
                new Card(CardSuit.HEARTS, CardValue.FOUR),
                new Card(CardSuit.SPADES, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.FOUR),
                new Card(CardSuit.CLUBS, CardValue.FOUR)
        );

        PokerHandMatcher matcher = new TwoPairMatcher();
        assertTrue(matcher.matches(hand));
    }

    @Test
    void TwoPairMatcherDoesNotMatchOnePair() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.TWO),
                new Card(CardSuit.HEARTS, CardValue.FOUR),
                new Card(CardSuit.SPADES, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.FIVE),
                new Card(CardSuit.CLUBS, CardValue.THREE)
        );

        PokerHandMatcher matcher = new TwoPairMatcher();
        assertFalse(matcher.matches(hand));
    }

    @Test
    void TwoPairMatcherDoesNotMatchOtherHand() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.QUEEN),
                new Card(CardSuit.HEARTS, CardValue.FOUR),
                new Card(CardSuit.SPADES, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.FIVE),
                new Card(CardSuit.CLUBS, CardValue.THREE)
        );

        PokerHandMatcher matcher = new TwoPairMatcher();
        assertFalse(matcher.matches(hand));
    }
}