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
        final HighCardScorer highCardScorer = new HighCardScorer();
        final PokerHandComparisonRule highCardRule = new HighCardRule(highCardScorer);

        final FlushMatcher flushMatcher = new FlushMatcher();
        final PokerHandComparisonRule flushRule = new FlushRule(flushMatcher, highCardRule);

        pokerJudge = new PokerJudge(flushRule, highCardRule);
        printer = new PokerJudgeResultPrinter();
    }

    private static void run() {
        final PokerJudgeResult result = pokerJudge.judge(hand1, hand2);
        printer.print(result, hand1, hand2);
    }
}