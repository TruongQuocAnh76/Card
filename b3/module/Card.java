package b3.module;

public class Card implements Comparable<Card> {
  protected int rank;
  protected int suit;
  // 1: spades
  // 2: clubs
  // 3: diamonds
  // 4: hearts

  public Card(int rank, int suit) {
    this.rank = rank;
    this.suit = suit;
  }

  public String getCardImageURL() {
    StringBuilder str = new StringBuilder("resource/");
    str.append(suit).append("_");
    switch (rank) {
      case 0: str.append("A.png"); break;
      case 10: str.append("J.png"); break;
      case 11: str.append("Q.png"); break;
      case 12: str.append("K.png"); break;
      default: str.append(rank + 1).append(".png");
    }
    return str.toString();
  }

  @Override
  public int compareTo(Card card) {
    if(this.rank != card.rank) return this.rank - card.rank;
    return this.suit - card.suit;
  }

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();
    switch (rank % 13) {
      case 0: str.append("ace"); break;
      case 10: str.append("jack"); break;
      case 11: str.append("queen"); break;
      case 12: str.append("king"); break;
      default: str.append(rank + 1 % 13);
    }
    switch (suit) {
      case 1: str.append(" spades "); break;
      case 2: str.append(" clubs "); break;
      case 3: str.append(" diamonds "); break;
      default: str.append(" hearts ");
    }
    return str.toString();
  }
}
