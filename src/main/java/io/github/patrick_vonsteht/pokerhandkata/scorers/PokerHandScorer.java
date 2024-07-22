package io.github.patrick_vonsteht.pokerhandkata.scorers;

import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;

import java.util.stream.Stream;

public interface PokerHandScorer {
    Stream<Integer> score(PokerHand hand);
}
