package b12;

public class InsertionSort<E extends Comparable<E>> implements Sort<E> {
  @Override
  public void sort(E[] array, int from, int to) {
    for (int i = from + 1; i <= to; i++, compareTimes++) {
      E temp = array[i];
      for (int j = i; j > from; j--, compareTimes++) {
        if (array[j - 1].compareTo(temp) > 0){
          array[j] = array[j - 1];
          swapTimes++;
        }
        else {
          compareTimes++;
          array[j] = temp;
          break;
        }
      }
      if (temp.compareTo(array[from]) <= 0) array[from] = temp;
      compareTimes++;
    }
  }
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
}
