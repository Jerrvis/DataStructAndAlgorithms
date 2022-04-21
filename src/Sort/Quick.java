package src.Sort;

public class Quick {
  public static int[] sort(int[] arr) {
    int left = 0;
    int right = arr.length - 1;
    arr = order(arr, left, right);
    return arr;
  }

  /***
   * @param order； true 为顺序； false为逆序
  */
  public static int[] sort(int[] arr, boolean order) {
    int left = 0;
    int right = arr.length - 1;
    if(order){
      arr = order(arr, left, right);
    }else{
      arr = reversedOrder(arr, left, right);
    }
    return arr;
  }

  /***
   * @param order; > 0 为顺序； <= 0 为逆序
  */
  public static int[] sort(int[] arr, int order) {
    int left = 0;
    int right = arr.length - 1;
    if(order > 0){
      arr = order(arr, left, right);
    }else{
      arr = reversedOrder(arr, left, right);
    }
    return arr;
  }

  public static int[] order(int[] arr, int left, int right) {
    int l = left;
    int r = right;
    // 中间值 l为空位
    int pivot = arr[left];

    while (l < r) {
      // 寻找arr[r] < pivot 插入到空位 l
      while (l < r && arr[r] > pivot) {
        r -= 1;
      }
      // 交换， r 为空位
      if (l < r) {
        arr[l] = arr[r];
      }
      // 寻找arr[l] > pivot 插入到空位 r
      while (l < r && arr[l] < pivot) {
        l += 1;
      }
      // 交换， r 为空位
      if (l < r) {
        arr[r] = arr[l];
      }
    }
    // r==l 时这里为 pivot 的位置
    arr[r] = pivot;

    if (r > left + 1) {
      // r左边有两个空位时继续排序
      arr = order(arr, left, r - 1);
    }
    if (r < right - 1) {
      // r右边有两个空位时继续排序
      arr = order(arr, r + 1, right);
    }
    return arr;
  }

  public static int[] reversedOrder(int[] arr, int left, int right) {
    int l = left;
    int r = right;
    // 中间值 l为空位
    int pivot = arr[left];

    while (l < r) {
      // 寻找arr[r] < pivot 插入到空位 l
      while (l < r && arr[r] < pivot) {
        r -= 1;
      }
      // 交换， r 为空位
      if (l < r) {
        arr[l] = arr[r];
      }
      // 寻找arr[l] > pivot 插入到空位 r
      while (l < r && arr[l] > pivot) {
        l += 1;
      }
      // 交换， r 为空位
      if (l < r) {
        arr[r] = arr[l];
      }
    }
    // r==l 时这里为 pivot 的位置
    arr[r] = pivot;

    if (r > left + 1) {
      // r左边有两个空位时继续排序
      arr = reversedOrder(arr, left, r - 1);
    }
    if (r < right - 1) {
      // r右边有两个空位时继续排序
      arr = reversedOrder(arr, r + 1, right);
    }
    return arr;
  }
}
