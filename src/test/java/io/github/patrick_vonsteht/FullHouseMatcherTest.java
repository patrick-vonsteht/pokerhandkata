package io.github.patrick_vonsteht;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FullHouseMatcherTest {

    @Test
    void FullHouseMatcherMatchesLowFullHouse() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.TWO),
                new Card(CardSuit.HEARTS, CardValue.FOUR),
                new Card(CardSuit.SPADES, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.FOUR)
        );

        PokerHandMatcher matcher = new FullHouseMatcher();
        assertTrue(matcher.matches(hand));
    }

    @Test
    void FulLHouseMatcherMatchesHighFullHouse() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.TWO),
                new Card(CardSuit.HEARTS, CardValue.FOUR),
                new Card(CardSuit.SPADES, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.FOUR),
                new Card(CardSuit.CLUBS, CardValue.FOUR)
        );

        PokerHandMatcher matcher = new FullHouseMatcher();
        assertTrue(matcher.matches(hand));
    }

    @Test
    void FullHouseMatcherDoesNotMatchNonFullHouseThreeOfAKind() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.THREE),
                new Card(CardSuit.HEARTS, CardValue.FOUR),
                new Card(CardSuit.SPADES, CardValue.FOUR),
                new Card(CardSuit.CLUBS, CardValue.FOUR),
                new Card(CardSuit.CLUBS, CardValue.FIVE)
        );

        PokerHandMatcher matcher = new FullHouseMatcher();
        assertFalse(matcher.matches(hand));
    }

    @Test
    void FullHouseMatcherDoesNotMatchNonFullHouseTwoOfAKind() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.JACK),
                new Card(CardSuit.HEARTS, CardValue.JACK),
                new Card(CardSuit.SPADES, CardValue.THREE),
                new Card(CardSuit.CLUBS, CardValue.FOUR),
                new Card(CardSuit.CLUBS, CardValue.FIVE)
        );

        PokerHandMatcher matcher = new FullHouseMatcher();
        assertFalse(matcher.matches(hand));
    }

    @Test
    void FullHouseMatcherDoesNotMatchNonFullHouse() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.QUEEN),
                new Card(CardSuit.HEARTS, CardValue.JACK),
                new Card(CardSuit.SPADES, CardValue.THREE),
                new Card(CardSuit.CLUBS, CardValue.NINE),
                new Card(CardSuit.CLUBS, CardValue.FIVE)
        );

        PokerHandMatcher matcher = new FullHouseMatcher();
        assertFalse(matcher.matches(hand));
    }
}