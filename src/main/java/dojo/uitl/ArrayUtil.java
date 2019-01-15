package dojo.uitl;

public class ArrayUtil {

  public static void sortFromGrantToSmall(Integer[] a, int low, int high) {
    int start = low;
    int end = high;
    int key = a[low];

    while (end > start) {
      while (end > start && a[end] >= key) {
        end--;
      }
      if (a[end] >= key) {
        int temp = a[end];
        a[end] = a[start];
        a[start] = temp;
      }
      //从前往后比较
      while (end > start && a[start] <= key) {
        start++;
      }
      if (a[start] <= key) {
        int temp = a[start];
        a[start] = a[end];
        a[end] = temp;
      }
    }
    if (start > low) {
      sortFromGrantToSmall(a, low, start - 1);
    }
    if (end < high) {
      sortFromGrantToSmall(a, end + 1, high);
    }
  }
}
