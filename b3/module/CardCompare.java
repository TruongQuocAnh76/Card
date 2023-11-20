package b3.module;

import java.util.Comparator;

public class CardCompare implements Comparator<Card> {
  @Override
  public int compare(Card o1, Card o2) {
    return o1.compareTo(o2);
  }
}
