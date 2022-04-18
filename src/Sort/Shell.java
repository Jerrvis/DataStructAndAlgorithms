package src.Sort;

public class Shell {
  public static int[] sort(int[] arr){
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
}
