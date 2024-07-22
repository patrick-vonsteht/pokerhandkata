package io.github.patrick_vonsteht.pokerhandkata.matchers;

import io.github.patrick_vonsteht.pokerhandkata.model.Card;
import io.github.patrick_vonsteht.pokerhandkata.model.CardSuit;
import io.github.patrick_vonsteht.pokerhandkata.model.CardValue;
import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StraightMatcherTest {

    @Test
    void StraightMatcherMatchesLowestStraight() {
        PokerHand hand = new PokerHand(
            new Card(CardSuit.DIAMONDS, CardValue.TWO),
            new Card(CardSuit.HEARTS, CardValue.THREE),
            new Card(CardSuit.SPADES, CardValue.FIVE),
            new Card(CardSuit.CLUBS, CardValue.FOUR),
            new Card(CardSuit.CLUBS, CardValue.SIX)
        );

        StraightMatcher matcher = new StraightMatcher();
        assertTrue(matcher.matches(hand));
    }

    @Test
    void StraightMatcherMatchesMiddleStraight() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.FIVE),
                new Card(CardSuit.HEARTS, CardValue.SEVEN),
                new Card(CardSuit.SPADES, CardValue.SIX),
                new Card(CardSuit.CLUBS, CardValue.EIGHT),
                new Card(CardSuit.CLUBS, CardValue.NINE)
        );

        StraightMatcher matcher = new StraightMatcher();
        assertTrue(matcher.matches(hand));
    }

    @Test
    void StraightMatcherMatchesHighestStraight() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.ACE),
                new Card(CardSuit.HEARTS, CardValue.QUEEN),
                new Card(CardSuit.SPADES, CardValue.KING),
                new Card(CardSuit.CLUBS, CardValue.JACK),
                new Card(CardSuit.CLUBS, CardValue.TEN)
        );

        StraightMatcher matcher = new StraightMatcher();
        assertTrue(matcher.matches(hand));
    }

    @Test
    void StraightMatcherDoesNotMatchNonStraight() {
        PokerHand hand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.TWO),
                new Card(CardSuit.HEARTS, CardValue.QUEEN),
                new Card(CardSuit.SPADES, CardValue.SIX),
                new Card(CardSuit.CLUBS, CardValue.JACK),
                new Card(CardSuit.CLUBS, CardValue.NINE)
        );

        StraightMatcher matcher = new StraightMatcher();
        assertFalse(matcher.matches(hand));
    }
}
