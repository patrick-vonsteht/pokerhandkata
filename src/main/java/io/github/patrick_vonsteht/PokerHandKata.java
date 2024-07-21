package io.github.patrick_vonsteht;

public final class PokerHandKata {
    private static PokerJudge pokerJudge;
    private static PokerJudgeResultPrinter printer;

    private static PokerHand hand1 = new PokerHand(
            new Card(CardSuit.CLUBS, CardValue.TWO),
            new Card(CardSuit.CLUBS, CardValue.TEN),
            new Card(CardSuit.CLUBS, CardValue.JACK),
            new Card(CardSuit.CLUBS, CardValue.FIVE),
            new Card(CardSuit.HEARTS, CardValue.SIX)
    );

    private static PokerHand hand2 = new PokerHand(
            new Card(CardSuit.DIAMONDS, CardValue.NINE),
            new Card(CardSuit.CLUBS, CardValue.FOUR),
            new Card(CardSuit.CLUBS, CardValue.JACK),
            new Card(CardSuit.CLUBS, CardValue.FIVE),
            new Card(CardSuit.CLUBS, CardValue.SIX)
    );

    private PokerHandKata() {}

    public static void main(String[] args) {
        setup();
        run();
    }

    private static void setup() {
        final PokerHandMatcher highCardMatcher = new XOfAKindMatcher(1);
        final PokerHandScorer highCardScorer = new XOfAKindScorer(1);
        final PokerHandComparisonRule highCardRule = new MatcherRuleWithScorer(highCardMatcher, highCardScorer);

        final StraightMatcher straightMatcher = new StraightMatcher();
        final PokerHandComparisonRule straightRule = new MatcherRuleWithSecondaryRule(straightMatcher, highCardRule);

        final FlushMatcher flushMatcher = new FlushMatcher();
        final PokerHandComparisonRule flushRule = new MatcherRuleWithSecondaryRule(flushMatcher, highCardRule);

        final StraightFlushMatcher straightFlushMatcher = new StraightFlushMatcher(straightMatcher, flushMatcher); // NOPMD Name is fine for setup code
        final PokerHandComparisonRule straightFlushRule = new MatcherRuleWithSecondaryRule(straightFlushMatcher, highCardRule);

        pokerJudge = new PokerJudge(straightFlushRule, flushRule, straightRule, highCardRule);
        printer = new PokerJudgeResultPrinter();
    }

    private static void run() {
        final PokerJudgeResult result = pokerJudge.judge(hand1, hand2);
        printer.print(result, hand1, hand2);
    }
}