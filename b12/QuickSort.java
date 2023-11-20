package b12;

public class QuickSort<E extends Comparable<E>> implements Sort<E> {
  private int compareTimes = 0;
  private int swapTimes = 0;
  private int N = 10;
  private InsertionSort<E> insertionSort;

  public QuickSort(int N) {
    this.N = N;
    insertionSort = new InsertionSort<>();
  }

  public QuickSort() {
    insertionSort = new InsertionSort<>();
  }

  private void swap(E[] array, int i, int j) {
    E temp = array[i];
    array[i] = array[j];
    array[j] = temp;
    swapTimes++;
  }

  @Override
  public void sort(E[] array, int from, int to) {
    if (to - from + 1 <= N) insertionSort.sort(array, from, to);
    else {
      compareTimes++;

      E pivot = getPivot(array, from, to);
      array[(from + to) / 2] = array[to];

      int pivotIdx = partition(array, from, to, pivot);

      sort(array, from, pivotIdx - 1);
      sort(array, pivotIdx + 1, to);
    }
  }

  private E getPivot(E[] array, int from, int to) {
    int mid = (from + to) / 2;
    // find median with basically bubble sort
    if (array[from].compareTo(array[mid]) > 0) swap(array, from, mid);
    if (array[mid].compareTo(array[to]) > 0) swap(array, mid, to);
    if (array[from].compareTo(array[mid]) > 0) swap(array, from, mid);

    return array[mid];
  }

  private int partition(E[] array, int from, int to, E pivot) {
    int i = from + 1, j = to - 1;
    while (true) {
      while (i < j  && array[i].compareTo(pivot) <= 0) i++;
      while (j > i && array[j].compareTo(pivot) > 0) j--;

      if (i >= j) break;
      swap(array, i, j);
    }
    array[to] = array[i];
    array[i] = pivot;
    return i;
  }

  @Override
  public int getCompareTimes() {
    return compareTimes + insertionSort.getCompareTimes();
  }

  @Override
  public int getSwapTimes() {
    return swapTimes + insertionSort.getCompareTimes();
  }
}
