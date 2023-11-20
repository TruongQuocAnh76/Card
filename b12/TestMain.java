package b12;

import java.math.BigInteger;

public class TestMain {
  public static void main(String[] args) {
    TestSortAlgorithm test = new TestSortAlgorithm(100000);
    test.test(new SelectionSort<>());
    test.test(new BubbleSort<>());
    test.test(new InsertionSort<>());
    test.test(new MergeSort<>());
    test.test(new QuickSort<>());
    BigInteger a = new BigInteger("0");
  }
}
