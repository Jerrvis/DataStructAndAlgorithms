package src.Sort;

public class Redix {
  public static int[] sort(int[] arr) {
    orderSort(arr, 10);
    return arr;
  }

  /***
   * @param order； true 为顺序； false为逆序
  */
  public static int[] sort(int[] arr, boolean order) {
    if(order){
      orderSort(arr, 10);
      return arr;
    }else{
      reversedOrderSort(arr, 10);
      return arr;
    }
  }

  /***
   * @param order; > 0 为顺序； <= 0 为逆序
  */
  public static int[] sort(int[] arr, int order) {
    if(order > 0){
      orderSort(arr, 10);
      return arr;
    }else{
      reversedOrderSort(arr, 10);
      return arr;
    }
  }

  /**
   * 
   * @param arr
   * @param base 求个位 base = 10, 求十位 base = 100
   */
  public static void orderSort(int[] arr, int base) {
    // 10个桶
    int[][] bucket = new int[10][arr.length];
    // 记录每个桶里的数据个数
    int[] bucketNum = new int[10];

    // 分配数据到桶里
    for (int i = 0; i < arr.length; i++) {
      int num = arr[i] % base * 10 / base;
      bucket[num][bucketNum[num]] = arr[i];
      bucketNum[num]++;
    }

    // 把桶内的数据插回到数组中
    int index = 0;
    for (int i = 0; i < bucketNum.length; i++) {
      if (bucketNum[i] != 0) {
        for (int j = 0; j < bucketNum[i]; j++) {
          arr[index] = bucket[i][j];
          index++;
        }
      }
    }

    // 当所有数据都在第1个桶时 排序完成
    if (bucketNum[0] == arr.length) {
      return;
    }
    // 基数升一位继续排序
    orderSort(arr, base * 10);
  }

  public static void reversedOrderSort(int[] arr, int base) {
    // 10个桶
    int[][] bucket = new int[10][arr.length];
    // 记录每个桶里的数据个数
    int[] bucketNum = new int[10];

    // 分配数据到桶里
    for (int i = arr.length - 1; i >= 0; i--) {
      int num = arr[i] % base * 10 / base;
      bucket[num][bucketNum[num]] = arr[i];
      bucketNum[num]++;
    }

    // 把桶内的数据插回到数组中
    int index = 0;
    for (int i = 0; i < bucketNum.length; i++) {
      if (bucketNum[i] != 0) {
        for (int j = 0; j < bucketNum[i]; j++) {
          arr[index] = bucket[i][j];
          index++;
        }
      }
    }

    // 当所有数据都在第1个桶时 排序完成
    if (bucketNum[0] == arr.length) {
      return;
    }
    // 基数升一位继续排序
    reversedOrderSort(arr, base * 10);
  }
}
