package b12;

import java.util.Arrays;

public class TestSortAlgorithm {
  private Integer[] array;

  public TestSortAlgorithm(int n) {
    array = new Integer[n];
    for (int i = 0; i < n; i++) array[i] = (int) (Math.random() * 99999 + 1);
    System.out.println(Arrays.toString(array));
  }

  public void test(Sort<Integer> sortAlgorithm) {
    Integer[] temp = new Integer[array.length];
    System.arraycopy(array, 0, temp, 0, array.length);

    System.out.println(sortAlgorithm.getClass().getSimpleName());

    long startTime = System.nanoTime();
    sortAlgorithm.sort(temp , 0, array.length - 1);
    long endTime = System.nanoTime();

    System.out.println(Arrays.toString(temp));
    System.out.printf("Time : %d ns or %f s\n", (endTime - startTime), (endTime - startTime) / Math.pow(10, 9));
    System.out.println("Compare times: " + sortAlgorithm.getCompareTimes());
    System.out.println("Swap times: " + sortAlgorithm.getSwapTimes() + "\n");
  }
}
