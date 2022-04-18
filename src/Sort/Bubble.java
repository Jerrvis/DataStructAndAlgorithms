package src.Sort;

public class Bubble{
  public static int[] sort(int[] arr){
    int temp;
    // i 为最小待放入的较小的数字的位置
    for (int i = 0; i < arr.length; i++) {
      for (int j = i + 1; j < arr.length; j++) {
        if(arr[i] > arr[j]){
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
      }
    }
    return arr;
  }
}