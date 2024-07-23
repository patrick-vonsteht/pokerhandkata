package io.github.patrick_vonsteht.pokerhandkata;

import io.github.patrick_vonsteht.pokerhandkata.model.*;
import io.github.patrick_vonsteht.pokerhandkata.rules.ComparisonRuleFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class PokerJudgeIntegrationTest {

    private final ComparisonRuleFactory ruleFactory = new ComparisonRuleFactory();
    private final PokerJudgeFactory judgeFactory = new PokerJudgeFactory(ruleFactory);
    private final PokerJudge pokerJudge = judgeFactory.createStandardPokerJudge();

    @Test
    void StraightFlushWinsAgainstFourOfAKind() {
        PokerHand fourOfAKindHand = PokerHand.fromCards(
                new Card(CardSuit.CLUBS, CardValue.TEN),
                new Card(CardSuit.DIAMONDS, CardValue.TEN),
                new Card(CardSuit.SPADES, CardValue.TEN),
                new Card(CardSuit.DIAMONDS, CardValue.SEVEN),
                new Card(CardSuit.HEARTS, CardValue.TEN)
        );

        PokerHand straightFlushHand = PokerHand.fromCards(
                new Card(CardSuit.DIAMONDS, CardValue.FOUR),
                new Card(CardSuit.DIAMONDS, CardValue.FIVE),
                new Card(CardSuit.DIAMONDS, CardValue.SIX),
                new Card(CardSuit.DIAMONDS, CardValue.SEVEN),
                new Card(CardSuit.DIAMONDS, CardValue.EIGHT)
        );

        PokerJudgeResult result = pokerJudge.judge(fourOfAKindHand, straightFlushHand);

        assertEquals(PokerJudgeResultType.WINNER, result.getType());
        assertEquals(straightFlushHand, result.getWinner());
    }

    @Test
    void PairWinsAgainstHighCard() {
        PokerHand pairHand = PokerHand.fromCards(
                new Card(CardSuit.CLUBS, CardValue.ACE),
                new Card(CardSuit.DIAMONDS, CardValue.TEN),
                new Card(CardSuit.SPADES, CardValue.FOUR),
                new Card(CardSuit.DIAMONDS, CardValue.SEVEN),
                new Card(CardSuit.HEARTS, CardValue.FOUR)
        );

        PokerHand highCardHand = PokerHand.fromCards(
                new Card(CardSuit.DIAMONDS, CardValue.TEN),
                new Card(CardSuit.SPADES, CardValue.ACE),
                new Card(CardSuit.DIAMONDS, CardValue.KING),
                new Card(CardSuit.CLUBS, CardValue.NINE),
                new Card(CardSuit.DIAMONDS, CardValue.EIGHT)
        );

        PokerJudgeResult result = pokerJudge.judge(pairHand, highCardHand);

        assertEquals(PokerJudgeResultType.WINNER, result.getType());
        assertEquals(pairHand, result.getWinner());
    }

    @Test
    void FullHouseWinsAgainstTwoPairs() {
        PokerHand twoPairHand = PokerHand.fromCards(
                new Card(CardSuit.CLUBS, CardValue.ACE),
                new Card(CardSuit.DIAMONDS, CardValue.ACE),
                new Card(CardSuit.SPADES, CardValue.FOUR),
                new Card(CardSuit.DIAMONDS, CardValue.SEVEN),
                new Card(CardSuit.HEARTS, CardValue.FOUR)
        );

        PokerHand fullHouseHand = PokerHand.fromCards(
                new Card(CardSuit.DIAMONDS, CardValue.TEN),
                new Card(CardSuit.SPADES, CardValue.TEN),
                new Card(CardSuit.DIAMONDS, CardValue.TEN),
                new Card(CardSuit.CLUBS, CardValue.KING),
                new Card(CardSuit.DIAMONDS, CardValue.KING)
        );

        PokerJudgeResult result = pokerJudge.judge(twoPairHand, fullHouseHand);

        assertEquals(PokerJudgeResultType.WINNER, result.getType());
        assertEquals(fullHouseHand, result.getWinner());
    }

    @Test
    void SameStraightsDraw() {
        PokerHand straightHand = PokerHand.fromCards(
                new Card(CardSuit.CLUBS, CardValue.SEVEN),
                new Card(CardSuit.DIAMONDS, CardValue.EIGHT),
                new Card(CardSuit.SPADES, CardValue.NINE),
                new Card(CardSuit.DIAMONDS, CardValue.TEN),
                new Card(CardSuit.HEARTS, CardValue.JACK)
        );

        PokerJudgeResult result = pokerJudge.judge(straightHand, straightHand);

        assertEquals(PokerJudgeResultType.DRAW, result.getType());
    }
}