package b12;

public class SelectionSort<E extends Comparable<E>> implements Sort<E> {
  private int swapTimes = 0;
  private int compareTimes = 0;

  @Override
  public void sort(E[] array, int from, int to) {
    for (int i = from; i < to; i++, compareTimes++) {
      int minIdx = i;
      for (int j = i + 1; j <= to; j++, compareTimes += 2)
        if (array[minIdx].compareTo(array[j]) > 0) minIdx = j;

      swap(array, minIdx, i);
    }
  }

  @Override
  public int getCompareTimes() {
    return compareTimes;
  }

  @Override
  public int getSwapTimes() {
    return swapTimes;
  }

  private void swap(E[] array, int i, int j) {
    E temp = array[i];
    array[i] = array[j];
    array[j] = temp;
    swapTimes++;
  }
}
