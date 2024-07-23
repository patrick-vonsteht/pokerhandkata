package io.github.patrick_vonsteht.pokerhandkata;

import io.github.patrick_vonsteht.pokerhandkata.rules.ComparisonRuleFactory;

public class PokerJudgeFactory {

    private final ComparisonRuleFactory ruleFactory;

    public PokerJudgeFactory(final ComparisonRuleFactory ruleFactory) {
        this.ruleFactory = ruleFactory;
    }

    public PokerJudge createStandardPokerJudge() {
        return new PokerJudge(
                ruleFactory.createStraightFlushRule(),
                ruleFactory.createFourOfAKindRule(),
                ruleFactory.createFullHouseRule(),
                ruleFactory.createFlushRule(),
                ruleFactory.createStraightRule(),
                ruleFactory.createThreeOfAKindRule(),
                ruleFactory.createTwoPairsRule(),
                ruleFactory.createPairRule(),
                ruleFactory.createHighCardRule()
        );
    }
}
