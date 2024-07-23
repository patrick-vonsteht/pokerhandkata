package io.github.patrick_vonsteht.pokerhandkata.rules;

import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;
import io.github.patrick_vonsteht.pokerhandkata.matchers.Matcher;
import io.github.patrick_vonsteht.pokerhandkata.model.ComparisonRuleResult;
import io.github.patrick_vonsteht.pokerhandkata.scorers.Scorer;

import java.util.Iterator;
import java.util.Optional;

/**
 * MatchThenScoreRule compares two PokerHands with a matcher and a scorer.
 *
 * The MatchThenScoreRule can yields the following results:
 * * If the matcher matches none of the PokerHands, the result is NO_MATCH
 * * If the matcher matches one of the PokerHands, the result is WINNER_MATCH, with the matching PokerHand.
 * * If the matcher matches both of the PokerHands, the result depends on the scorer result:
 *   * If the scorer results in the same scores for both PokerHands, the result is DRAW_MATCH
 *   * Otherwise the result is WINNER_MATCH, with the PokerHand which has the higher score in the first score that is
 *     not equal for both PokerHands.
 */
public class MatchThenScoreRule implements ComparisonRule {
    private final Matcher matcher;
    private final Scorer scorer;

    public MatchThenScoreRule(final Matcher matcher, final Scorer scorer) {
        this.matcher = matcher;
        this.scorer = scorer;
    }

    @Override
    public ComparisonRuleResult compare(final PokerHand hand1, final PokerHand hand2) {

        final Optional<ComparisonRuleResult> matchingResult = compareByMatching(hand1, hand2);

        if (matchingResult.isPresent()) {
            return matchingResult.get();
        }

        final Optional<ComparisonRuleResult> scoringResult = compareByScoring(hand1, hand2);

        if (scoringResult.isPresent()) {
            return scoringResult.get();
        }

        return ComparisonRuleResult.drawRuleResult();
    }

    private Optional<ComparisonRuleResult> compareByMatching(final PokerHand hand1, final PokerHand hand2) {
        final boolean hand1Matches = matcher.matches(hand1);
        final boolean hand2Matches = matcher.matches(hand2);

        final boolean noHandMatches = !hand1Matches && !hand2Matches;
        final boolean onlyHand1Matches = hand1Matches && !hand2Matches;
        final boolean onlyHand2Matches = !hand1Matches && hand2Matches;

        if (noHandMatches) {
            return Optional.of(ComparisonRuleResult.noMatchRuleResult());
        }

        if (onlyHand1Matches) {
            return Optional.of(ComparisonRuleResult.winnerRuleResult(hand1));
        }

        if (onlyHand2Matches) {
            return Optional.of(ComparisonRuleResult.winnerRuleResult(hand2));
        }

        return Optional.empty();
    }

    private Optional<ComparisonRuleResult> compareByScoring(final PokerHand hand1, final PokerHand hand2) {
        final Iterator<Integer> scores1 = scorer.score(hand1).iterator();
        final Iterator<Integer> scores2 = scorer.score(hand2).iterator();

        while (scores1.hasNext() && scores2.hasNext()) {
            final int score1 = scores1.next();
            final int score2 = scores2.next();

            if (score1 > score2) {
                return Optional.of(ComparisonRuleResult.winnerRuleResult(hand1));
            }

            if (score1 < score2) {
                return Optional.of(ComparisonRuleResult.winnerRuleResult(hand2));
            }
        }

        if (scores1.hasNext() || scores2.hasNext()) {
            throw new UnsupportedOperationException("Hand1 has a different number of scores than hand2. With " +
                    "standard poker rules there's no way to handle this and this should not happen. This is likely " +
                    "an implementation bug.");
        }

        return Optional.empty();
    }
}
