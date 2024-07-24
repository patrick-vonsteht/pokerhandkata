# Poker Hands Kata

This repository contains a Java solution to the Poker Hands Kata.

## Problem Description

Determine which poker hand wins according to the following poker rules:

* A poker deck contains 52 cards - each card has a suit which is one of clubs, diamonds, hearts, or spades (denoted C, D, H, and S). Each card also has a value which is one of 2, 3, 4, 5, 6, 7, 8, 9, 10, jack, queen, king, ace (denoted 2, 3, 4, 5, 6, 7, 8, 9, T, J, Q, K, A). For scoring purposes, the suits are unordered while the values are ordered as given above, with 2 being the lowest and ace the highest value.
* A poker hand consists of 5 cards dealt from the deck. Poker hands are ranked by the following partial order from lowest to highest.
* High Card: Hands which do not fit any higher category are ranked by the value of their highest card. If the highest cards have the same value, the hands are ranked by the next highest, and so on.
* Pair: 2 of the 5 cards in the hand have the same value. Hands which both contain a pair are ranked by the value of the cards forming the pair. If these values are the same, the hands are ranked by the values of the cards not forming the pair, in decreasing order.
* Two Pairs: The hand contains 2 different pairs. Hands which both contain 2 pairs are ranked by the value of their highest pair. Hands with the same highest pair are ranked by the value of their other pair. If these values are the same the hands are ranked by the value of the remaining card.
* Three of a Kind: Three of the cards in the hand have the same value. Hands which both contain three of a kind are ranked by the value of the 3 cards.
* Straight: Hand contains 5 cards with consecutive values. Hands which both contain a straight are ranked by their highest card.
* Flush: Hand contains 5 cards of the same suit. Hands which are both flushes are ranked using the rules for High Card.
* Full House: 3 cards of the same value, with the remaining 2 cards forming a pair. Ranked by the value of the 3 cards.
* Four of a kind: 4 cards with the same value. Ranked by the value of the 4 cards.
* Straight flush: 5 cards of the same suit with consecutive values. Ranked by the highest card in the hand.

## Solution Approach

My solution divides the problem into the following responsibilities:
* Matchers: Determine whether a single PokerHand matches one of the poker rules.
* Scorers: Determine the score(s) that a single PokerHand gets according to the poker rule that it matches.
* ComparisonRules: Compare two PokerHands according to a single poker rule.
* PokerJudge: Compares two PokerHands according to the precedence-ordered set of poker rules.

The solution employs a procedural approach by defining a set of data objects and a separate set of algorithm classes 
that act on these objects. This separation allows for the addition or modification of poker rules without affecting 
unrelated existing classes and data structures. The result is a highly flexible system that could easily be extended with a completely 
different rule set.

Matchers and scorers have been generalized and combined in different ways to implement the poker rules with a very small
set of matcher and scorer implementations. Wrapping the creation of the ComparisonRules in a factory class hides this 
implementation detail and re-establishes meaningful names for the construction of the rules.

Constructor dependency injection makes it possible to test all parts of the solution in isolation. Due to the small
size of the codebase I decided against the additional complexity of introducing a dependency injection library.

## Build and Run
This program builds with [Maven](https://maven.apache.org/). 
Execute the following commands in the repository root folder to build the program with the 
[Maven Wrapper](https://maven.apache.org/wrapper/) which ensures that you run the build with the same Maven version 
that I tested with:
```
./mvnw install 
java -jar target/pokerhandkata-1.0.0-jar-with-dependencies.jar 
```

If you don't trust the script, you can also install and execute Maven directly. Please note that the used plugin 
versions require at least Maven 3.6.3:
```
mvn install
java -jar target/pokerhandkata-1.0.0-jar-with-dependencies.jar 
```

There's also a binary release available in the releases of this repository.

## Configure
The hands to compare are defined as static variables in the PokerHandKata class, which is the main entry point of the
program (`src/main/java/io/github/patrick_vonsteht/pokerhandkata/PokerHandKata.java`):
```
public final class PokerHandKata {
   
   ...

    // Enter your hand values here :)
    private static final PokerHand HAND_1 = PokerHand.fromCards(
            new Card(CardSuit.HEARTS, CardValue.QUEEN),
            new Card(CardSuit.HEARTS, CardValue.TEN),
            new Card(CardSuit.CLUBS, CardValue.JACK),
            new Card(CardSuit.CLUBS, CardValue.FIVE),
            new Card(CardSuit.CLUBS, CardValue.SIX)
    );

    private static final PokerHand HAND_2 = PokerHand.fromCards(
            new Card(CardSuit.CLUBS, CardValue.THREE),
            new Card(CardSuit.DIAMONDS, CardValue.TEN),
            new Card(CardSuit.CLUBS, CardValue.JACK),
            new Card(CardSuit.SPADES, CardValue.FIVE),
            new Card(CardSuit.CLUBS, CardValue.SIX)
    );
    
    ...
    
```

Alternatively, you can also add additional hand combinations to the PokerJudgeIntegrationTest 
(`src/test/java/io/github/patrick_vonsteht/pokerhandkata/PokerJudgeIntegrationTest.java`).

```
class PokerJudgeIntegrationTest {

    ...
    
    @Test
    void StraightFlushWinsAgainstFourOfAKind() {
        PokerHand fourOfAKindHand = PokerHand.fromCards(
                new Card(CardSuit.CLUBS, CardValue.TEN),
                new Card(CardSuit.DIAMONDS, CardValue.TEN),
                new Card(CardSuit.SPADES, CardValue.TEN),
                new Card(CardSuit.DIAMONDS, CardValue.SEVEN),
                new Card(CardSuit.HEARTS, CardValue.TEN)
        );

        PokerHand straightFlushHand = PokerHand.fromCards(
                new Card(CardSuit.DIAMONDS, CardValue.FOUR),
                new Card(CardSuit.DIAMONDS, CardValue.FIVE),
                new Card(CardSuit.DIAMONDS, CardValue.SIX),
                new Card(CardSuit.DIAMONDS, CardValue.SEVEN),
                new Card(CardSuit.DIAMONDS, CardValue.EIGHT)
        );

        PokerJudgeResult result = pokerJudge.judge(fourOfAKindHand, straightFlushHand);

        assertEquals(PokerJudgeResultType.WINNER, result.getType());
        assertEquals(straightFlushHand, result.getWinner());
    }
    
    ...
```

## Static Code Analysis
The build invokes multiple static code analyses. The build fails on less than 80% C0 or C1 JaCoCo test coverage, and on
any issue reported by PMD or SpotBugs. To view the reports of these tools, see:
* [JaCoCo](https://www.jacoco.org/jacoco/) Test Coverage Report: `target/site/jacoco/index.html`
* [PMD](https://pmd.github.io/) Source Code Static Code Analysis Report: `target/site/pmd.html`
* [SpotBugs](https://spotbugs.github.io/) Byte Code Static Code Analysis Report: `target/spotbugs.html`


## Bonus Exercises
You can use this kata as the basis for refactoring katas. Here are some ideas:
* Extend the code to support both scoring poker hands and [Yahtzee](https://en.wikipedia.org/wiki/Yahtzee) dice throws. 
  Try to reuse as much of the existing code as possible, generalizing classes where needed. How can you cleanly handle
  both poker hands with suit and value and dice with just a value?
* Refactor the code to use a dependency injection library. Explore what the advantages and disadvantages of using the
  library are in this case.
* Extend the code to judge situations with two cards on each hand, and five community cards that can be used by both 
  hands. Judge each hand by the highest five card combination that can be formed from the seven available cards.