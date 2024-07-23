package io.github.patrick_vonsteht.pokerhandkata.rules;

import io.github.patrick_vonsteht.pokerhandkata.matchers.*;
import io.github.patrick_vonsteht.pokerhandkata.scorers.PokerHandScorer;
import io.github.patrick_vonsteht.pokerhandkata.scorers.XOfAKindScorer;

public class ComparisonRuleFactory {

    /**
     * Creates a Straight Flush Rule with the following meaning:
     * 5 cards of the same suit with consecutive values. Ranked by the highest card in the hand.
     */
    public PokerHandComparisonRule createStraightFlushRule() {
        final PokerHandMatcher straightMatcher = new StraightMatcher();
        final PokerHandMatcher flushMatcher = new FlushMatcher();
        final PokerHandMatcher straightFlushMatcher = new AndMatcher(straightMatcher, flushMatcher);

        // The XOfAKindScorer(1,1,5) scorer returns all card values in descending order. However, the additional scores
        // are irrelevant because, in a straight, the highest value determines all other values. As a result, all
        // scores will be compared, but the result will be the same as if only the highest card was compared.
        final PokerHandScorer highCardScorer = new XOfAKindScorer(1, 1, 5);

        return new MatchThenScoreRule(straightFlushMatcher, highCardScorer);
    }

    /**
     * Creates a Four of a Kind Rule with the following meaning:
     * 4 cards with the same value. Ranked by the value of the 4 cards.
     */
    public PokerHandComparisonRule createFourOfAKindRule() {
        final PokerHandMatcher fourOfAKindMatcher = new XOfAKindMatcher(4, 1, 1);

        // The XOfAKindScorer(4,1,1) scorer calculates the value of the four cards, followed by the values of the
        // remaining card. However, the additional score is irrelevant because there are only four cards of each value
        // in a standard deck. As a result, the first four cards will always be distinct, and the subsequent scores
        // won’t be considered.
        final PokerHandScorer fourOfAKindScorer = new XOfAKindScorer(4, 1, 1);

        return new MatchThenScoreRule(fourOfAKindMatcher, fourOfAKindScorer);
    }

    /**
     * Creates a Full House Rule with the following meaning:
     * 3 cards of the same value, with the remaining 2 cards forming a pair. Ranked by the value of the 3 cards.
     */
    public PokerHandComparisonRule createFullHouseRule() {
        final PokerHandMatcher threeOfAKindMatcher = new XOfAKindMatcher(3, 1, 1);
        final PokerHandMatcher pairMatcher = new XOfAKindMatcher(2, 1, 1);
        final PokerHandMatcher fullHouseMatcher = new AndMatcher(threeOfAKindMatcher, pairMatcher);

        // The XOfAKindScorer(3,1,1) scorer calculates the value of the three cards, followed by the descending values
        // of the remaining cards. However, the additional scores are irrelevant because there are only four cards of
        // each value in a standard deck. As a result, the first three cards will always be distinct, and the
        // subsequent scores won’t be considered.
        final PokerHandScorer fullHouseScorer = new XOfAKindScorer(3, 1, 1);

        return new MatchThenScoreRule(fullHouseMatcher, fullHouseScorer);
    }

    /**
     * Creates a Flush Rule with the following meaning:
     * Hand contains 5 cards of the same suit. Hands which are both flushes are ranked using the rules for High Card.
     */
    public PokerHandComparisonRule createFlushRule() {
        final PokerHandMatcher flushMatcher = new FlushMatcher();
        final PokerHandScorer highCardScorer = new XOfAKindScorer(1, 1, 5);
        return new MatchThenScoreRule(flushMatcher, highCardScorer);
    }

    /**
     * Creates a Straight Rule with the following meaning:
     * Hand contains 5 cards with consecutive values. Hands which both contain a straight are ranked by their highest
     * card.
     */
    public PokerHandComparisonRule createStraightRule() {
        final PokerHandMatcher straightMatcher = new StraightMatcher();

        // The XOfAKindScorer(1,1,5) scorer returns all card values in descending order. However, the additional scores
        // are irrelevant because, in a straight, the highest value determines all other values. As a result, all
        // scores will be compared, but the result will be the same as if only the highest card was compared.
        final PokerHandScorer highCardScorer = new XOfAKindScorer(1, 1, 5);

        return new MatchThenScoreRule(straightMatcher, highCardScorer);
    }

    /**
     * Creates a Three of a Kind Rule with the following meaning:
     * Three of the cards in the hand have the same value. Hands which both contain three of a kind are ranked by the
     * value of the 3 cards.
     */
    public PokerHandComparisonRule createThreeOfAKindRule() {
        final PokerHandMatcher threeOfAKindMatcher = new XOfAKindMatcher(3, 1, 1);

        // The XOfAKindScorer(3,1,1) scorer calculates the value of the three cards, followed by the descending values
        // of the remaining cards. However, the additional scores are irrelevant because there are only four cards of
        // each value in a standard deck. As a result, the first three cards will always be distinct, and the
        // subsequent scores won’t be considered.
        final PokerHandScorer threeOfAKindScorer = new XOfAKindScorer(3, 1, 1);

        return new MatchThenScoreRule(threeOfAKindMatcher, threeOfAKindScorer);
    }

    /**
     * Creates a Two Pairs Rule with the following meaning:
     * The hand contains 2 different pairs. Hands which both contain 2 pairs are ranked by the value of their highest
     * pair. Hands with the same highest pair are ranked by the value of their other pair. If these values are the same
     * the hands are ranked by the value of the remaining card.
     */
    public PokerHandComparisonRule createTwoPairsRule() {
        final PokerHandMatcher twoPairMatcher = new XOfAKindMatcher(2, 2, 2);
        final PokerHandScorer twoPairScorer = new XOfAKindScorer(2, 2, 2);
        return new MatchThenScoreRule(twoPairMatcher, twoPairScorer);
    }

    /**
     * Creates a Pair Rule with the following meaning:
     * 2 of the 5 cards in the hand have the same value. Hands which both contain a pair are ranked by the value of the
     * cards forming the pair. If these values are the same, the hands are ranked by the values of the cards not
     * forming the pair, in decreasing order.
     */
    public PokerHandComparisonRule createPairRule() {
        final PokerHandMatcher pairMatcher = new XOfAKindMatcher(2, 1, 1);
        final PokerHandScorer pairScorer = new XOfAKindScorer(2, 1, 1);
        return new MatchThenScoreRule(pairMatcher, pairScorer);
    }

    /**
     * Creates a High Card Rule with the following meaning:
     * Hands which do not fit any higher category are ranked by the value of their highest card. If the highest cards
     * have the same value, the hands are ranked by the next highest, and so on.
     */
    public PokerHandComparisonRule createHighCardRule() {
        // The XOfAKindMatcher(1,1,5) matcher matches any hand.
        final PokerHandMatcher highCardMatcher = new XOfAKindMatcher(1, 1, 5);
        final PokerHandScorer highCardScorer = new XOfAKindScorer(1, 1, 5);
        return new MatchThenScoreRule(highCardMatcher, highCardScorer);
    }
}
