package io.github.patrick_vonsteht.pokerhandkata.matchers;

import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AndMatcherTest {

    private static final PokerHand mockedHand = mock(PokerHand.class);

    private static final PokerHandMatcher mockedMatchingRule = mock(PokerHandMatcher.class);
    private static final PokerHandMatcher mockedNonMatchingRule = mock(PokerHandMatcher.class);

    @BeforeAll
    public static void setupMocks() {
        when(mockedMatchingRule.matches(mockedHand)).thenReturn(true);
        when(mockedNonMatchingRule.matches(mockedHand)).thenReturn(false);
    }

    @Test
    void AndMatcherWithoutRulesMatches() {
        assertAndMatcherMatches();
    }

    @Test
    void AndMatcherWithMatchingRuleMatches() {
        assertAndMatcherMatches(mockedMatchingRule);
    }

    @Test
    void AndMatcherWithNonMatchingRuleDoesNotMatch() {
        assertAndMatcherDoesNotMatch(mockedNonMatchingRule);
    }

    @Test
    void AndMatcherWithTwoMatchingRulesMatches() {
        assertAndMatcherMatches(mockedMatchingRule, mockedMatchingRule);
    }

    @Test
    void AndMatcherWithTwoNonMatchingRulesDoesNotMatch() {
        assertAndMatcherDoesNotMatch(mockedNonMatchingRule, mockedNonMatchingRule);
    }

    @Test
    void AndMatcherWithOneMatchingAndOneNonMatchingRuleDoesNotMatch() {
        assertAndMatcherDoesNotMatch(mockedMatchingRule, mockedNonMatchingRule);
    }

    @Test
    void AndMatcherWithOneNonMatchingAndOneMatchingRuleDoesNotMatch() {
        assertAndMatcherDoesNotMatch(mockedNonMatchingRule, mockedMatchingRule);
    }

    void assertAndMatcherMatches(PokerHandMatcher... matchers) {
        PokerHandMatcher andMatcher = new AndMatcher(matchers);
        assertTrue(andMatcher.matches(mockedHand));
    }

    void assertAndMatcherDoesNotMatch(PokerHandMatcher... matchers) {
        PokerHandMatcher andMatcher = new AndMatcher(matchers);
        assertFalse(andMatcher.matches(mockedHand));
    }
}
