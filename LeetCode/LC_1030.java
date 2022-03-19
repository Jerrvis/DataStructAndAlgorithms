package LeetCode;

public class LC_1030 {
  public static void main (String[] args){
    LC_1030 lc = new LC_1030();
    int[][] arr = lc.allCellsDistOrder(1,2,0,0);
    System.out.println(arr);
    
    
  }

  public int[][] allCellsDistOrder(int rows, int cols, int rCenter, int cCenter) {
    int MaxDist = Math.max(rCenter, rows - 1 - rCenter) + Math.max(cCenter, cols - 1 - cCenter); // 1.先求出最大的距离 
    int PointNum = rows * cols;
    int CountNUM = 1;
    int[][] arr = new int[PointNum][] ; // 创建数组
    arr[0] = new int[]{rCenter,cCenter}; // 放入原点
    int row = rCenter;
    int col = cCenter;

    for (int Dist= 1; Dist <= MaxDist ; Dist++){ // 从距离1开始循环
      for (int Dr = 0-Dist; Dr <= Dist; Dr++){ // 3.外层y坐标循环坐标从负到正一次循环
        if (Dist != Math.abs(Dr)){ // 判断x轴偏移不为0
          row = rCenter + Dr;
          col = cCenter + (Dist - Math.abs(Dr)); // x轴偏移为正
          if (row >= 0 && row < rows && col >= 0 && col < cols ){
              //判断点是否在框内
            arr[CountNUM] = new int[]{row,col};
            CountNUM++;
          }

          col = cCenter - (Dist - Math.abs(Dr)); // x轴偏移为负
          if (row >= 0 && row < rows && col >= 0 && col < cols ){
            arr[CountNUM] = new int[]{row,col};
            CountNUM++;
          }
        }else{ // 判断x轴偏移为0
          row = rCenter + Dr;
          col = cCenter;
          if (row >= 0 && row < rows && col >= 0 && col < cols ){
            arr[CountNUM] = new int[]{row,col};
            CountNUM++;
          }
        }
      }
    }

    return arr;
  
  }
  
  
}
