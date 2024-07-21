package io.github.patrick_vonsteht;

import java.util.stream.Stream;

public interface PokerHandScorer {
    Stream<Integer> score(PokerHand hand);
}
