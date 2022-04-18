package src.Sort;

public class Select {
  public static int[] sort(int[] arr){
    int min;
    int minIndex;
    int temp;

    for (int i = 0; i < arr.length; i++) {
      min = arr[i];
      minIndex = i;

      // 查找最小值
      for (int j = i; j < arr.length; j++) {
        if(arr[j] < min){
          min = arr[j];
          minIndex = j;
        }
      }
      // 最小交换
      temp = arr[i];
      arr[i] = min;
      arr[minIndex] = temp;
    }
    return arr;
  }

  public static int[] doubleSort(int[] arr){
    int max;
    int maxIndex;
    int min;
    int minIndex;
    int temp;

    for (int i = 0; i <= arr.length - i ; i++) {
      max = arr[i];
      maxIndex = i;
      min = arr[i];
      minIndex = i;

      for (int j = i; j < arr.length - i; j++) {
        if(arr[j] < min){
          min = arr[j];
          minIndex = j;
        }
        if(arr[j] > max){
          max = arr[j];
          maxIndex = j;
        }
      }
      // 最小交换
      temp = arr[i];
      arr[i] = min;
      arr[minIndex] = temp;
      // 最大交换
      temp = arr[arr.length - 1 - i];
      arr[arr.length - 1 - i] = max;
      arr[maxIndex] = temp;
    }

    return arr;
  }
}
