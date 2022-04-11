package src.Recursion;

public class MazeTest{
  public static void main (String[] args){
    Maze maze = new Maze(7, 6);
    maze.addWall(3, 1);
    maze.addWall(3, 2);
    maze.addWall(4, 3);
    maze.setStartPoint(1, 1);
    maze.setEndPoint(5, 4);
    maze.showMap();
    maze.findWay();
  }
}

class Maze{
  /**
   * 迷宫
   * 0表示可以走的地方
   * 1表示墙不可走
   * 2表示已经走过的地方
   * 3表示无法通过此路到终点
   * 
   * 
   */
  public int[][] map;
  public int width;
  public int high;
  public int startPointX = -1;
  public int startPointY = -1;
  public int endPointX = -1;
  public int endPointY = -1;


  // 构造器初始化迷宫地图
  public Maze(int h, int w){
    high = h;
    width = w;
    map = new int[h][w];

    for (int i = 0; i < w; i++ ){
      map[0][i] = 1;
      map[h-1][i] = 1; 
    }

    for (int i = 0; i < h; i++ ){
      map[i][0] = 1;
      map[i][w-1] = 1; 
    }
  }

  // x为横向即width y为纵向即high
  public void addWall(int y, int x){
    map[y][x] = 1;
  }

  // 设置开始点
  public void setStartPoint(int y, int x){
    if (map[x][y] == 1 ){
      System.out.println("该点为障碍无法设置为起点");
    }else{
      this.startPointX = x;
      this.startPointY = y;
    }
  }

  // 设置终点
  public void setEndPoint(int y, int x){
    if (map[y][x] == 1 ){
      System.out.println("该点为障碍无法设置为终点");
    }else{
      this.endPointX = x;
      this.endPointY = y;
    }
  }

  // 输出地图
  public void showMap(){
    System.out.println("============");
    for (int i = 0; i < this.high; i++) {
      for (int j = 0; j < this.width; j++) {
        System.out.print(map[i][j] + " ");
      }
      System.out.println();
    }
  }

  // 自动寻找路径
  public void findWay(){
    if (startPointX < 1 && startPointY < 1 && endPointX < 1 && endPointY < 1){
      throw new RuntimeException("起点或终点异常，请重新检查设置");
    }
    boolean success = setWay(this.startPointY, this.startPointX);
    if (success){
      System.out.println("成功找到路径");
      this.showMap();
    }else {
      System.out.println("无法找到路径");
      this.showMap();
    } 
  }

  // 递归寻找
  public boolean setWay(int y, int x){
    if (map[this.endPointY][this.endPointX] == 2){
      return true;
    }else {
      if (map[y][x] == 0){  // 当前点位未走过
        map[y][x] = 2;
        if (setWay(y + 1,x)){ // 向下走一步
          return true;
        }else if(setWay(y,x + 1)){ // 向右走一步
          return true;
        }else if(setWay(y - 1,x)){ // 向上走一步
          return true;
        }else if(setWay(y,x - 1)){ // 向左走一步
          return true;
        }else { // 此路不通
          map[y][x] = 3;
          return false;
        }
      }else{ // 当前点位已走过
        return false;
      }
    }
  }

}