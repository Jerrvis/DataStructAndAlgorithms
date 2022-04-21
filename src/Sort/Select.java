package src.Sort;

public class Select {
  public static int[] sort(int[] arr){
    arr = order(arr);
    return arr;
  }

  /***
   * @param order； true 为顺序； false为逆序
  */
  public static int[] sort(int[] arr, boolean order){
    if(order){
      arr = order(arr);
    }else{
      arr = reversedOrder(arr);
    }
    return arr;
  }

  /***
   * @param order; > 0 为顺序； <= 0 为逆序
  */
  public static int[] sort(int[] arr, int order){
    if(order > 0){
      arr = order(arr);
    }else{
      arr = reversedOrder(arr);
    }
    return arr;
  }



  // 順序
  public static int[] order(int[] arr){
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

  // 逆序
  public static int[] reversedOrder(int[] arr){
    int max;
    int maxIndex;
    int temp;

    for (int i = 0; i < arr.length; i++) {
      max = arr[i];
      maxIndex = i;

      // 查找最大值
      for (int j = i; j < arr.length; j++) {
        if(arr[j] > max){
          max = arr[j];
          maxIndex = j;
        }
      }
      // 最大交换
      temp = arr[i];
      arr[i] = max;
      arr[maxIndex] = temp;
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
