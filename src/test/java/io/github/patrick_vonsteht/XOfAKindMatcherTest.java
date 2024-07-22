package io.github.patrick_vonsteht;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class XOfAKindMatcherTest {

    @Test
    void SingleOneOfAKindMatcherDoesNotMatchAnyHand() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.TWO),
                new Card(CardSuit.HEARTS, CardValue.FOUR),
                new Card(CardSuit.SPADES, CardValue.QUEEN),
                new Card(CardSuit.CLUBS, CardValue.ACE),
                new Card(CardSuit.HEARTS, CardValue.EIGHT)
        );

        PokerHandMatcher matcher = new XOfAKindMatcher(1, 1, 1);
        assertFalse(matcher.matches(hand));
    }

    @Test
    void MultipleOneOfAKindMatcherMatchesAnyHand() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.TWO),
                new Card(CardSuit.HEARTS, CardValue.FOUR),
                new Card(CardSuit.SPADES, CardValue.QUEEN),
                new Card(CardSuit.CLUBS, CardValue.ACE),
                new Card(CardSuit.HEARTS, CardValue.EIGHT)
        );

        PokerHandMatcher matcher = new XOfAKindMatcher(1, 1, 5);
        assertTrue(matcher.matches(hand));
    }

    @Test
    void SingleTwoOfAKindMatcherMatchesLowTwoOfAKind() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.TWO),
                new Card(CardSuit.HEARTS, CardValue.FOUR),
                new Card(CardSuit.SPADES, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.ACE),
                new Card(CardSuit.HEARTS, CardValue.EIGHT)
        );

        PokerHandMatcher matcher = new XOfAKindMatcher(2, 1, 1);
        assertTrue(matcher.matches(hand));
    }

    @Test
    void SingleTwoOfAKindMatcherMatchesMiddleTwoOfAKind() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.TWO),
                new Card(CardSuit.HEARTS, CardValue.FOUR),
                new Card(CardSuit.SPADES, CardValue.FOUR),
                new Card(CardSuit.CLUBS, CardValue.ACE),
                new Card(CardSuit.HEARTS, CardValue.EIGHT)
        );

        PokerHandMatcher matcher = new XOfAKindMatcher(2, 1, 1);
        assertTrue(matcher.matches(hand));
    }

    @Test
    void SingleTwoOfAKindMatcherMatchesHighTwoOfAKind() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.TWO),
                new Card(CardSuit.HEARTS, CardValue.FOUR),
                new Card(CardSuit.SPADES, CardValue.ACE),
                new Card(CardSuit.CLUBS, CardValue.ACE),
                new Card(CardSuit.HEARTS, CardValue.EIGHT)
        );

        PokerHandMatcher matcher = new XOfAKindMatcher(2, 1, 1);
        assertTrue(matcher.matches(hand));
    }

    @Test
    void SingleTwoOfAKindMatcherDoesNotMatchNonTwoOfAKind() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.TEN),
                new Card(CardSuit.HEARTS, CardValue.FOUR),
                new Card(CardSuit.SPADES, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.JACK),
                new Card(CardSuit.CLUBS, CardValue.QUEEN)
        );

        PokerHandMatcher matcher = new XOfAKindMatcher(2, 1, 1);
        assertFalse(matcher.matches(hand));
    }

    @Test
    void SingleTwoOfAKindMatcherDoesNotMatchTwoTwoOfAKind() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.TEN),
                new Card(CardSuit.HEARTS, CardValue.FOUR),
                new Card(CardSuit.SPADES, CardValue.TEN),
                new Card(CardSuit.CLUBS, CardValue.JACK),
                new Card(CardSuit.CLUBS, CardValue.JACK)
        );

        PokerHandMatcher matcher = new XOfAKindMatcher(2, 1, 1);
        assertFalse(matcher.matches(hand));
    }

    @Test
    void TwoTwoOfAKindMatcherMatchesTwoTwoOfAKind() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.TEN),
                new Card(CardSuit.HEARTS, CardValue.FOUR),
                new Card(CardSuit.SPADES, CardValue.TEN),
                new Card(CardSuit.CLUBS, CardValue.JACK),
                new Card(CardSuit.CLUBS, CardValue.JACK)
        );

        PokerHandMatcher matcher = new XOfAKindMatcher(2, 2, 2);
        assertTrue(matcher.matches(hand));
    }

    @Test
    void ThreeOfAKindMatcherMatchesLowThreeOfAKind() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.TWO),
                new Card(CardSuit.HEARTS, CardValue.FOUR),
                new Card(CardSuit.SPADES, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.ACE),
                new Card(CardSuit.HEARTS, CardValue.TWO)
        );

        PokerHandMatcher matcher = new XOfAKindMatcher(3, 1, 1);
        assertTrue(matcher.matches(hand));
    }

    @Test
    void ThreeOfAKindMatcherMatchesMiddleThreeOfAKind() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.TWO),
                new Card(CardSuit.HEARTS, CardValue.FOUR),
                new Card(CardSuit.SPADES, CardValue.FOUR),
                new Card(CardSuit.CLUBS, CardValue.ACE),
                new Card(CardSuit.HEARTS, CardValue.FOUR)
        );

        PokerHandMatcher matcher = new XOfAKindMatcher(3, 1, 1);
        assertTrue(matcher.matches(hand));
    }

    @Test
    void ThreeOfAKindMatcherMatchesHighThreeOfAKind() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.ACE),
                new Card(CardSuit.HEARTS, CardValue.FOUR),
                new Card(CardSuit.SPADES, CardValue.ACE),
                new Card(CardSuit.CLUBS, CardValue.ACE),
                new Card(CardSuit.HEARTS, CardValue.EIGHT)
        );

        PokerHandMatcher matcher = new XOfAKindMatcher(3, 1, 1);
        assertTrue(matcher.matches(hand));
    }

    @Test
    void ThreeOfAKindMatcherDoesNotMatchTwoOfAKind() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.FOUR),
                new Card(CardSuit.HEARTS, CardValue.FOUR),
                new Card(CardSuit.SPADES, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.SIX),
                new Card(CardSuit.CLUBS, CardValue.QUEEN)
        );

        PokerHandMatcher matcher = new XOfAKindMatcher(3, 1, 1);
        assertFalse(matcher.matches(hand));
    }

    @Test
    void FourOfAKindMatcherMatchesLowFourOfAKind() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.TWO),
                new Card(CardSuit.HEARTS, CardValue.FOUR),
                new Card(CardSuit.SPADES, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.TWO)
        );

        PokerHandMatcher matcher = new XOfAKindMatcher(4, 1, 1);
        assertTrue(matcher.matches(hand));
    }

    @Test
    void FourOfAKindMatcherMatchesHighFourOfAKind() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.FOUR),
                new Card(CardSuit.HEARTS, CardValue.FOUR),
                new Card(CardSuit.SPADES, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.FOUR),
                new Card(CardSuit.CLUBS, CardValue.FOUR)
        );

        PokerHandMatcher matcher = new XOfAKindMatcher(4, 1, 1);
        assertTrue(matcher.matches(hand));
    }

    @Test
    void FourOfAKindMatcherDoesNotMatchThreeOfAKind() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.FOUR),
                new Card(CardSuit.HEARTS, CardValue.FOUR),
                new Card(CardSuit.SPADES, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.FOUR),
                new Card(CardSuit.CLUBS, CardValue.QUEEN)
        );

        PokerHandMatcher matcher = new XOfAKindMatcher(4, 1, 1);
        assertFalse(matcher.matches(hand));
    }

    @Test
    void FiveOfAKindMatcherMatchesFiveOfAKind() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.THREE),
                new Card(CardSuit.HEARTS, CardValue.THREE),
                new Card(CardSuit.SPADES, CardValue.THREE),
                new Card(CardSuit.CLUBS, CardValue.THREE),
                new Card(CardSuit.CLUBS, CardValue.THREE)
        );

        PokerHandMatcher matcher = new XOfAKindMatcher(5, 1, 1);
        assertTrue(matcher.matches(hand));
    }

    @Test
    void FiveOfAKindMatcherDoesNotMatchFourOfAKind() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.FOUR),
                new Card(CardSuit.HEARTS, CardValue.FOUR),
                new Card(CardSuit.SPADES, CardValue.FOUR),
                new Card(CardSuit.CLUBS, CardValue.FOUR),
                new Card(CardSuit.CLUBS, CardValue.QUEEN)
        );

        PokerHandMatcher matcher = new XOfAKindMatcher(5, 1, 1);
        assertFalse(matcher.matches(hand));
    }
}