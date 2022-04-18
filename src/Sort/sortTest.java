package src.Sort;


public class sortTest {
  public static void main (String[] args){
    int[] arr = new int[10];
    
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int)(Math.random() * 10000); 
    }

    printArr(arr);
    arr = Shell.sort(arr);
    printArr(arr);
  }

  public static void printArr(int[] arr){
    System.out.print("{");
    for (int i = 0; i < arr.length; i++) {
      System.out.print(" " + arr[i]);
    }
    System.out.println(" }");
  }


}


