package src;

public class sparseArray {
  public static void main (String[] args){
    int[][] chess = new int[11][11];
    chess[1][2] = 1;
    chess[2][3] = 2;

    int[][] sparseArray = toSparseArray(chess);
    for (int[] row:sparseArray){
      for (int aint: row){
          System.out.print(aint+" ");
      }
      System.out.println();
    }

    int[][] array = toArray(sparseArray);
    for (int[] row:array){
      for (int aint: row){
          System.out.print(aint+" ");
      }
      System.out.println();
    }
  }

  // 转为稀疏数组
  public static int[][] toSparseArray(int[][] array){
    int valueNum = 0;
    // 遍历非0个数
    for (int[] row: array){
      for (int data: row){
        if (data != 0){
          valueNum++;
        }
      }
    }

    // 定义稀疏数组
    int[][] sparseArray = new int[valueNum+1][3];
    sparseArray[0] = new int[]{array.length,array[0].length,valueNum};
    int count = 0;
    for (int i = 0; i < array.length; i++){
      for (int j = 0; j < array.length; j++){
        if(array[i][j] != 0){
          count++;
          sparseArray[count][0] = i;
          sparseArray[count][1] = j;
          sparseArray[count][2] = array[i][j];
        }
      }
    }
    return sparseArray;
  }

  //转为普通数组
  public static int[][] toArray(int[][] sparseArray){
    int[][] array = new int[sparseArray[0][0]][sparseArray[0][1]];
    for (int i = 1; i < sparseArray.length; i++){
      array[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
    }
    return array;
  }
}
