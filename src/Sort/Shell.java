package src.Sort;

public class Shell {
  public static int[] sort(int[] arr){
    arr = orderSort(arr);

    return arr;
  }

  /***
   * @param order； true 为顺序； false为逆序
  */
  public static int[] sort(int[] arr, Boolean order){
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
    int gap = arr.length/2;
    int insertVal;
    int insertIndex;
    while (gap >= 1 ){
      for (int i = gap; i < arr.length; i+=gap) {
        insertVal = arr[i];
        insertIndex = i - gap;
        while (insertIndex >= 0 && insertVal < arr[insertIndex]){
          arr[insertIndex + gap] = arr[insertIndex];
          insertIndex -= gap;
        }
        arr[insertIndex + gap] = insertVal;
      }
      gap = gap/2;
    }

    return arr;
  }

  // 逆序
  public static int[] reversedOrderSort(int[] arr){
    int gap = arr.length/2;
    int insertVal;
    int insertIndex;
    while (gap >= 1 ){
      for (int i = gap; i < arr.length; i+=gap) {
        insertVal = arr[i];
        insertIndex = i - gap;
        while (insertIndex >= 0 && insertVal > arr[insertIndex]){
          arr[insertIndex + gap] = arr[insertIndex];
          insertIndex -= gap;
        }
        arr[insertIndex + gap] = insertVal;
      }
      gap = gap/2;
    }

    return arr;
  }
}
