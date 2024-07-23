package io.github.patrick_vonsteht.pokerhandkata;

import io.github.patrick_vonsteht.pokerhandkata.model.*;
import io.github.patrick_vonsteht.pokerhandkata.rules.ComparisonRuleFactory;
import io.github.patrick_vonsteht.pokerhandkata.ui.PokerJudgeResultPrinter;

public final class PokerHandKata {
    private static PokerJudge pokerJudge;
    private static PokerJudgeResultPrinter printer;

    // Enter your hand values here :)
    private static final PokerHand HAND_1 = PokerHand.fromCards(
            new Card(CardSuit.HEARTS, CardValue.QUEEN),
            new Card(CardSuit.HEARTS, CardValue.TEN),
            new Card(CardSuit.CLUBS, CardValue.JACK),
            new Card(CardSuit.CLUBS, CardValue.FIVE),
            new Card(CardSuit.CLUBS, CardValue.SIX)
    );

    private static final PokerHand HAND_2 = PokerHand.fromCards(
            new Card(CardSuit.CLUBS, CardValue.THREE),
            new Card(CardSuit.DIAMONDS, CardValue.TEN),
            new Card(CardSuit.CLUBS, CardValue.JACK),
            new Card(CardSuit.SPADES, CardValue.FIVE),
            new Card(CardSuit.CLUBS, CardValue.SIX)
    );

    private PokerHandKata() {}

    public static void main(String[] args) {
        setup();
        run();
    }

    private static void setup() {
        final ComparisonRuleFactory ruleFactory = new ComparisonRuleFactory();
        final PokerJudgeFactory judgeFactory = new PokerJudgeFactory(ruleFactory);
        pokerJudge = judgeFactory.createStandardPokerJudge();
        printer = new PokerJudgeResultPrinter();
    }

    private static void run() {
        final PokerJudgeResult result = pokerJudge.judge(HAND_1, HAND_2);
        printer.print(result, HAND_1, HAND_2);
    }
}