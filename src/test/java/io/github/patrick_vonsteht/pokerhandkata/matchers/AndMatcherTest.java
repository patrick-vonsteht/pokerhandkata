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
        PokerHandMatcher andMatcher = new AndMatcher();
        assertTrue(andMatcher.matches(mockedHand));
    }

    @Test
    void AndMatcherWithMatchingRuleMatches() {
        PokerHandMatcher andMatcher = new AndMatcher(mockedMatchingRule);
        assertTrue(andMatcher.matches(mockedHand));
    }

    @Test
    void AndMatcherWithNonMatchingRuleDoesNotMatch() {
        PokerHandMatcher andMatcher = new AndMatcher(mockedNonMatchingRule);
        assertFalse(andMatcher.matches(mockedHand));
    }

    @Test
    void AndMatcherWithTwoMatchingRulesMatches() {
        PokerHandMatcher andMatcher = new AndMatcher(mockedMatchingRule, mockedMatchingRule);
        assertTrue(andMatcher.matches(mockedHand));
    }

    @Test
    void AndMatcherWithTwoNonMatchingRulesDoesNotMatch() {
        PokerHandMatcher andMatcher = new AndMatcher(mockedNonMatchingRule, mockedNonMatchingRule);
        assertFalse(andMatcher.matches(mockedHand));
    }

    @Test
    void AndMatcherWithOneMatchingAndOneNonMatchingRuleDoesNotMatch() {
        PokerHandMatcher andMatcher = new AndMatcher(mockedMatchingRule, mockedNonMatchingRule);
        assertFalse(andMatcher.matches(mockedHand));
    }
}
