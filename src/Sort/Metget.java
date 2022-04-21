package src.Sort;

public class Metget {
  public static int[] sort(int[] arr) {
    int[] temp = new int[arr.length];
    int left = 0;
    int right = arr.length - 1;
    orderDivide(arr, left, right, temp);
    return arr;
  }

  /***
   * @param order； true 为顺序； false为逆序
  */
  public static int[] sort(int[] arr, boolean order) {
    int[] temp = new int[arr.length];
    int left = 0;
    int right = arr.length - 1;
    if(order){
      orderDivide(arr, left, right, temp);
    }else{
      reversedOrderDivide(arr, left, right, temp);
    }
    return arr;
  }

  /***
   * @param order; > 0 为顺序； <= 0 为逆序
  */
  public static int[] sort(int[] arr, int order) {
    int[] temp = new int[arr.length];
    int left = 0;
    int right = arr.length - 1;
    if(order > 0){
      orderDivide(arr, left, right, temp);
    }else{
      reversedOrderDivide(arr, left, right, temp);
    }
    return arr;
  }

  // 順序
  public static void orderDivide(int[] arr, int left, int right, int[] temp) {
    if (right - left >= 1) { // num数量大于1继续分
      int mid = (left + right) / 2;
      // 左边继续分
      orderDivide(arr, left, mid, temp);
      // 右边继续分
      orderDivide(arr, mid + 1, right, temp);
      arr = orderConquer(arr, left, mid, right, temp);
    }
  }

  /**
   * 
   * @param arr   排序的原始数组
   * @param left  左边开始有序序列的初始索引
   * @param mid   中间索引
   * @param right 右边索引
   * @param temp  临时数组
   * @return
   */
  public static int[] orderConquer(int[] arr, int left, int mid, int right, int[] temp) {
    int i = left;
    int j = mid + 1;
    int t = 0; // 临时数组待放入的索引

    // 左右两边数据放入右边，直到左右两边有一边处理完毕为止
    while (i <= mid && j <= right) {
      if (arr[i] < arr[j]) {
        temp[t] = arr[i];
        t++;
        i++;
      } else {
        temp[t] = arr[j];
        t++;
        j++;
      }
    }
    // 处理未添加的某一边
    while (i <= mid) {
      temp[t] = arr[i];
      t++;
      i++;
    }
    while (j <= right) {
      temp[t] = arr[j];
      t++;
      j++;
    }
    // 将temp的到的值覆盖到数组中
    t = 0;
    int tempLeft = left;
    while (tempLeft <= right) {
      arr[tempLeft] = temp[t];
      tempLeft++;
      t++;
    }
    return arr;
  }

  // 逆序
  public static void reversedOrderDivide(int[] arr, int left, int right, int[] temp) {
    if (right - left >= 1) { // num数量大于1继续分
      int mid = (left + right) / 2;
      // 左边继续分
      reversedOrderDivide(arr, left, mid, temp);
      // 右边继续分
      reversedOrderDivide(arr, mid + 1, right, temp);
      arr = reversedOrderConquer(arr, left, mid, right, temp);
    }
  }

  public static int[] reversedOrderConquer(int[] arr, int left, int mid, int right, int[] temp) {
    int i = left;
    int j = mid + 1;
    int t = 0; // 临时数组待放入的索引

    // 左右两边数据放入右边，直到左右两边有一边处理完毕为止
    while (i <= mid && j <= right) {
      if (arr[i] > arr[j]) {
        temp[t] = arr[i];
        t++;
        i++;
      } else {
        temp[t] = arr[j];
        t++;
        j++;
      }
    }
    // 处理未添加的某一边
    while (i <= mid) {
      temp[t] = arr[i];
      t++;
      i++;
    }
    while (j <= right) {
      temp[t] = arr[j];
      t++;
      j++;
    }
    // 将temp的到的值覆盖到数组中
    t = 0;
    int tempLeft = left;
    while (tempLeft <= right) {
      arr[tempLeft] = temp[t];
      tempLeft++;
      t++;
    }
    return arr;
  }
}
