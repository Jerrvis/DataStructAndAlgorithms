package src.Recursion;

public class EightQueenTest {
  public static void main (String[] args){
    EightQueen eq = new EightQueen();
    eq.searchSolutin();
    System.out.printf("总共有 %d 种解决办法",eq.num);
  }
}

class EightQueen{
  public int[] col;
  // 当前要放第 row+1 枚皇后，放在第row行中
  public int row;
  // 解决方案数目
  public int num;

  public EightQueen(){
    col = new int[8];
    for (int i = 0; i < col.length; i++) {
      col[i] = -1;
    }
    row = 0;
    num = 0;
  }

  public void searchSolutin(){
    // 棋盘上有棋子少于8枚时
    if(row < 8){
      for (int i = 0; i < 8; i++) { // 棋子放在第row 行 0-7位置
        if (isAllow(i)){ // 当前位置为合法位置
          col[row] = i; 
          row++; // 下一行
          searchSolutin();
        }
      }
      row--; // 当前行没有合法位置
      return;
    }else{ // 当成功放下8枚皇后时
      printCol();
      num++;
      row--;
      return;
    }
  }

  public void printCol(){
    System.out.print("{ ");
    for (int i = 0; i < col.length; i++) {
      System.out.print(col[i] + " ");
    }
    System.out.println("}");
  }

  public boolean isAllow(int index){
    if(row > 0 ){
      for (int i = 0; i < row; i++) {
        if(col[i] == index){ // 纵向冲突
          return false;
        }else if (Math.abs(col[i] - index) == row - i ){ // 斜向冲突
          return false;
        }
      }
      return true; 
    }
    return true; // 第0行不需要检查
  }
}
