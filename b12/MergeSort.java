package b12;

public class MergeSort<E extends Comparable<E>> implements Sort<E> {
  private int compareTimes = 0;
  // swapping only happen in insertion sort
  private InsertionSort<E> insertionSort;
  private int N = 5;

  public MergeSort() {
    insertionSort = new InsertionSort<>();
  }

  public MergeSort(int N) {
    insertionSort = new InsertionSort<>();
    this.N = N;
  }

  @Override
  public void sort(E[] array, int from, int to) {
    if ((to - from) <= 5) insertionSort.sort(array, from, to);
    else {
      compareTimes++;

      int mid = (from + to) / 2;

      sort(array, from, mid);
      sort(array, mid + 1, to);
      merge(array, from, mid, to);
    }
  }

  @Override
  public int getCompareTimes() {
    return compareTimes + insertionSort.getCompareTimes();
  }

  @Override
  public int getSwapTimes() {
    return insertionSort.getSwapTimes();
  }

  private void merge(E[] array, int from, int mid, int to) {
    E[] res = (E[]) new Comparable[to - from + 1];
    System.arraycopy(array, from, res, 0, to - from + 1);
    int i = 0, j = mid - from + 1, k = from;

    while (i <= mid - from && j <= to - from) {
      if (res[i].compareTo(res[j]) <= 0) array[k++] = res[i++];
      else array[k++] = res[j++];

      compareTimes += 3;
    }
    while (i <= mid - from) {
      array[k++] = res[i++];

      compareTimes++;
    }
    while (j <= to - from) {
      array[k++] = res[j++];

      compareTimes++;
    }
  }
}
