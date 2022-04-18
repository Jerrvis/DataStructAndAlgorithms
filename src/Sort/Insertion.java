package src.Sort;

public class Insertion {
  public static int[] sort(int[] arr){
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
}
