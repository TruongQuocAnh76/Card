package b12;

public class BubbleSort<E extends Comparable<E>> implements Sort<E> {
  private int compareTimes = 0;
  private int swapTimes = 0;

  @Override
  public int getCompareTimes() {
    return compareTimes;
  }

  @Override
  public int getSwapTimes() {
    return swapTimes;
  }

  @Override
  public void sort(E[] array, int from, int to) {
    int lower = from;
    int upper = to;

    while (true) {
      int new_upper = lower;
      for (int i = lower; i < upper; i++, compareTimes += 2)
        if (array[i].compareTo(array[i + 1]) > 0) {
          swap(array, i, i + 1);
          new_upper = i;
        }
      upper = new_upper;

      if (lower == upper) break;
      compareTimes++;

      int new_lower = upper;
      for (int i = upper; i > lower; i--, compareTimes += 2)
        if (array[i].compareTo(array[i - 1]) < 0) {
          swap(array, i, i - 1);
          new_lower = i;
        }
      lower = new_lower;

      if (lower == upper) break;
      compareTimes++;
    }
  }

  private void swap(E[] array, int i, int j) {
    E temp = array[i];
    array[i] = array[j];
    array[j] = temp;
    swapTimes++;
  }
}
