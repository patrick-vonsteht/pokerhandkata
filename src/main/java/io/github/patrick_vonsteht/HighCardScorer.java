package io.github.patrick_vonsteht;

import java.util.Collections;
import java.util.stream.Stream;

public class HighCardScorer implements PokerHandScorer {
    @Override
    public Stream<Integer> score(final PokerHand hand) {
        return hand.stream().map(Card::getNumericValue).sorted(Collections.reverseOrder());
    }
}
