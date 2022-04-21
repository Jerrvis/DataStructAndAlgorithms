package src.Sort;

public class Insertion {
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
  public static int[] sort(int[] arr, int order) {
    if(order > 0){
      arr = orderSort(arr);
    }else{
      arr = reversedOrderSort(arr);
    }
    return arr;
  }

  // 順序
  public static int[] orderSort(int[] arr){
    int insertVal;
    int insertIndex;

    for (int i = 1; i < arr.length; i++) {
      // 待插入的数值
      insertVal = arr[i];
      insertIndex = i - 1;
      while (insertIndex >= 0 && insertVal < arr[insertIndex] ){
        arr[insertIndex + 1] = arr[insertIndex];
        insertIndex--;
      }
      arr[insertIndex + 1] = insertVal;
    }

    return arr;
  }

  // 逆序
  public static int[] reversedOrderSort(int[] arr){
    int insertVal;
    int insertIndex;

    for (int i = 1; i < arr.length; i++) {
      // 待插入的数值
      insertVal = arr[i];
      insertIndex = i - 1;
      while (insertIndex >= 0 && insertVal > arr[insertIndex] ){
        arr[insertIndex + 1] = arr[insertIndex];
        insertIndex--;
      }
      arr[insertIndex + 1] = insertVal;
    }

    return arr;
  }
}
