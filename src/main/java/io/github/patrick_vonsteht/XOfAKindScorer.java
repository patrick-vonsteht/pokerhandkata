package io.github.patrick_vonsteht;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class XOfAKindScorer implements PokerHandScorer {
    private final static String EXCEPTION_STRING =
            "XOfAKindScorer(%s) was called with a hand that does not contain %s of a kind.";

    private final int numberOfSameCards;

    public XOfAKindScorer(final int numberOfSameCards) {
        this.numberOfSameCards = numberOfSameCards;
    }

    @Override
    public Stream<Integer> score(final PokerHand hand) {

        final Map<Integer, List<Card>> cardsByValue = hand.stream()
                .collect(Collectors.groupingBy(Card::getNumericValue));

        final List<Integer> values = new ArrayList<>();
        for (final Map.Entry<Integer, List<Card>> card : cardsByValue.entrySet()) {
            if (card.getValue().size() == numberOfSameCards) {
                values.add(card.getKey());
            }
        }

        if (values.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format(EXCEPTION_STRING, numberOfSameCards, numberOfSameCards));
        }

        return values.stream().sorted(Comparator.reverseOrder());
    }
}
