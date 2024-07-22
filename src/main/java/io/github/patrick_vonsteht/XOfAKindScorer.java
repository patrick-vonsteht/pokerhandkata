package io.github.patrick_vonsteht;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class XOfAKindScorer implements PokerHandScorer {
    private final int matchLength;
    private final int minMatches;
    private final int maxMatches;

    public XOfAKindScorer(final int matchLength, final int minMatches, final int maxMatches) {
        this.matchLength = matchLength;
        this.minMatches = minMatches;
        this.maxMatches = maxMatches;
    }

    @Override
    public Stream<Integer> score(final PokerHand hand) {
        final var cardsByMatchAndValue = hand.stream()
                .collect(Collectors.groupingBy(Card::getNumericValue))
                .entrySet()
                .stream()
                .collect(Collectors.teeing(
                        Collectors.filtering(e -> e.getValue().size() == matchLength, Collectors.toList()),
                        Collectors.filtering(e -> e.getValue().size() != matchLength, Collectors.toList()),
                        List::of
                ));

        final List<Map.Entry<Integer, List<Card>>> matchesByValue = cardsByMatchAndValue.getFirst();
        final List<Map.Entry<Integer, List<Card>>> nonMatchesByValue = cardsByMatchAndValue.getLast(); // NOPMD Better readable when defined here

        final int numMatches = matchesByValue.size();
        if (numMatches < minMatches  || numMatches > maxMatches) {
            throw new IllegalArgumentException(String.format("The hand has an invalid number of matches. This " +
                    "XOfAKindScorer expects between %s and %s matches of %s of a kind. Check the hand with an " +
                    "XOfAKindMatcher before passing it to an XOfAKindScorer.", minMatches, maxMatches, matchLength));
        }

        final Stream<Integer> matchScores = matchesByValue.stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .map(Map.Entry::getKey);

        final Stream<Integer> nonMatchScores = nonMatchesByValue.stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .flatMap(e -> e.getValue().stream())
                .map(Card::getNumericValue);

        return Stream.concat(matchScores, nonMatchScores);
    }
}
