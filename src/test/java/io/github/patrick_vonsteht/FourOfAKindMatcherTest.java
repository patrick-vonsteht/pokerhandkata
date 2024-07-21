package io.github.patrick_vonsteht;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FourOfAKindMatcherTest {

    @Test
    void FourOfAKindMatcherMatchesLowFourOfAKind() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.TWO),
                new Card(CardSuit.HEARTS, CardValue.FOUR),
                new Card(CardSuit.SPADES, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.TWO)
        );

        PokerHandMatcher matcher = new XOfAKindMatcher(4);
        assertTrue(matcher.matches(hand));
    }

    @Test
    void ForOfAKindMatcherMatchesHighFourOfAKind() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.FOUR),
                new Card(CardSuit.HEARTS, CardValue.FOUR),
                new Card(CardSuit.SPADES, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.FOUR),
                new Card(CardSuit.CLUBS, CardValue.FOUR)
        );

        PokerHandMatcher matcher = new XOfAKindMatcher(4);
        assertTrue(matcher.matches(hand));
    }

    @Test
    void FourOfAKindMatcherDoesNotMatchNonFourOfAKind() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.THREE),
                new Card(CardSuit.HEARTS, CardValue.FOUR),
                new Card(CardSuit.SPADES, CardValue.FOUR),
                new Card(CardSuit.CLUBS, CardValue.FOUR),
                new Card(CardSuit.CLUBS, CardValue.FIVE)
        );

        PokerHandMatcher matcher = new XOfAKindMatcher(4);
        assertFalse(matcher.matches(hand));
    }

    @Test
    void FourOfAKindMatcherDoesNotMatchNonFourOfAKind2() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.THREE),
                new Card(CardSuit.HEARTS, CardValue.FOUR),
                new Card(CardSuit.SPADES, CardValue.NINE),
                new Card(CardSuit.CLUBS, CardValue.FOUR),
                new Card(CardSuit.CLUBS, CardValue.FIVE)
        );

        PokerHandMatcher matcher = new XOfAKindMatcher(4);
        assertFalse(matcher.matches(hand));
    }
}