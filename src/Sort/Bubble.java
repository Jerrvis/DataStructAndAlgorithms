package src.Sort;

public class Bubble{
  public static int[] sort(int[] arr){
    arr = orderSort(arr);
    return arr;
  }

  /***
   * @param order； true 为顺序； false为逆序
  */
  public static int[] sort(int[] arr, boolean order){
    if(order){
      arr = orderSort(arr);
    }else{
      arr = reversedOrderSort(arr);
    }
    return arr;
  }

  /***
   * @param order; > 0 为顺序； <= 0 为逆序
  */
  public static int[] sort(int[] arr, int order){
    if(order > 0){
      arr = orderSort(arr);
    }else{
      arr = reversedOrderSort(arr);
    }
    return arr;
  }

  // 順序
  public static int[] orderSort(int[] arr){
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

  // 逆序
  public static int[] reversedOrderSort(int[] arr){
    int temp;
    // i 为最大待放入的较大的数字的位置
    for (int i = 0; i < arr.length; i++) {
      for (int j = i + 1; j < arr.length; j++) {
        if(arr[i] < arr[j]){
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
      }
    }
    return arr;
  }
}