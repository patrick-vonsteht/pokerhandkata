package io.github.patrick_vonsteht.pokerhandkata.matchers;

import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AndMatcherTest {
    private static final PokerHand mockedHand = mock(PokerHand.class);

    private static final Matcher mockedMatchingRule = mock(Matcher.class);
    private static final Matcher mockedNonMatchingRule = mock(Matcher.class);

    @BeforeAll
    public static void setupMocks() {
        when(mockedMatchingRule.matches(mockedHand)).thenReturn(true);
        when(mockedNonMatchingRule.matches(mockedHand)).thenReturn(false);
    }

    @Test
    void MatchesWithoutRules() {
        assertMatches();
    }

    @Test
    void MatchesWithOneMatchingRule() {
        assertMatches(mockedMatchingRule);
    }

    @Test
    void DoesNotMatchWithOneNonMatchingRule() {
        assertDoesNotMatch(mockedNonMatchingRule);
    }

    @Test
    void MatchesWithTwoMatchingRules() {
        assertMatches(mockedMatchingRule, mockedMatchingRule);
    }

    @Test
    void DoesNotMatchWithTwoNonMatchingRules() {
        assertDoesNotMatch(mockedNonMatchingRule, mockedNonMatchingRule);
    }

    @Test
    void DoesNotMatchWithOneMatchingAndOneNonMatchingRule() {
        assertDoesNotMatch(mockedMatchingRule, mockedNonMatchingRule);
    }

    @Test
    void DoesNotMatchWithOneNonMatchingAndOneMatchingRule() {
        assertDoesNotMatch(mockedNonMatchingRule, mockedMatchingRule);
    }

    void assertMatches(Matcher... matchers) {
        Matcher andMatcher = new AndMatcher(matchers);
        assertTrue(andMatcher.matches(mockedHand));
    }

    void assertDoesNotMatch(Matcher... matchers) {
        Matcher andMatcher = new AndMatcher(matchers);
        assertFalse(andMatcher.matches(mockedHand));
    }
}
