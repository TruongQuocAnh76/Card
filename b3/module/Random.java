package b3.module;

import b12.Sort;
import java.util.*;

public class Random {
  public int n;
  private List<Integer> defaultCardDeck;
  private List<Card> deck;

  public Random(int n) {
    this.n = n;
    deck = new ArrayList<>();
    defaultCardDeck = new ArrayList<>(52);
    getDefaultCardDeck();
    drawCards();
  }

  public void shuffleDeck() {
    Collections.shuffle(deck);
  }

  private void shuffleDefaultDeck() {
    Collections.shuffle(defaultCardDeck);
  }

  public void drawCards() {
    shuffleDefaultDeck();
    deck.clear();
    int length = Math.min(n, 52); // prevent exception when user draw cards with n > 52
    for (int i = 0; i < length; i++) deck.add(getCard(defaultCardDeck.get(i)));
  }

  public void addCard() {
    for (int i = deck.size(); i < n; i++) deck.add(getCard(defaultCardDeck.get(i)));
  }

  public void removeCard() {
    deck = deck.subList(0, n);
  }

  public Card getCard(Integer cardIdx) {
    int rank = cardIdx % 13;
    int suit = cardIdx / 13 + 1;
    return new Card(rank, suit);
  }

  private void getDefaultCardDeck() {
    for (int i = 0; i < 52; i++) defaultCardDeck.add(i);
  }

  public List<Card> getDeck() {
    return deck;
  }

  public void sortDeck(Sort<Card> sortAlg) {
    int to = Math.min(n - 1, 51); // prevent exception when user sort deck with n > 52
    Card[] temp = deck.toArray(new Card[0]);
    sortAlg.sort(temp, 0, to);
    deck = new ArrayList<>(Arrays.asList(temp));
  }
}
