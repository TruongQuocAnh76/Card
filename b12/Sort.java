package b12;

public interface Sort<E extends Comparable<E>> {
    /**
     * sort array in increasing order
     */
    void sort(E[] array, int from, int to);
    int getCompareTimes();
    int getSwapTimes();
}
