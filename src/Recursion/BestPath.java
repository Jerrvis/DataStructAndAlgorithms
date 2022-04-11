package src.Recursion;

import java.util.ArrayList;
import java.util.List;

public class BestPath {
  public static void main (String[] args){

    // List<Integer> a = new ArrayList<>();
    // a.add(7);
    // a.add(9);
    // a.add(10);
    // a.remove(a.size()-1);
    // a.remove(a.size()-1);
    // System.out.println(a.get(0));
    Maze2 maze2 = new Maze2(9, 9);
    maze2.addWall(2, 4);
    maze2.addWall(3, 4);
    maze2.addWall(4, 4);
    maze2.addWall(5, 4);
    maze2.setStartPoint(3, 2);
    maze2.setEndPoint(5, 6);
    maze2.showMap();
    maze2.findWay();
  }
}

class Maze2 extends Maze{
  // Lowest Cost Path
  public int LCP;
  // Lowest Actual Cost Path
  public int LACP;
  // Actual Cost Path(changing)
  public int ACP;
  public int LCPX;
  public int LCPY;
  public List<Integer> pathNow;
  public List<Integer> pathBest;

  // public int count;
  
  public Maze2(int high, int width) {
    super(high, width);
    pathNow = new ArrayList<Integer>();
    // count = 0;
  }

  public void setLCP(){
    // 检查是否设置好 start end
    if(startPointX < 1 && startPointY < 1 && endPointX < 1 && endPointY < 1){
      return;
    }else{
      LCPX = endPointX - startPointX;
      LCPY = endPointY - startPointY;
      LACP = width*high + 10; // 设置为最大
      LCP = LCPX + LCPY;
    }
  }

  // 重置路径
  public void initMap(){
    for (int i = 0; i < high; i++) {
      for (int j = 0; j < width; j++) {
        if(map[i][j] > 1){
          map[i][j] = 0;
        }
      }
    }
    map[startPointY][startPointX] = 2;
  }

  // 设置起点(重写)
  @Override
  public void setStartPoint(int y, int x){
    if (map[y][x] == 1 ){
      System.out.println("该点为障碍无法设置为起点");
    }else{
      startPointX = x;
      startPointY = y;
      ACP = -1;
      setLCP();
    }
  }

  // 设置终点(重写)
  @Override
  public void setEndPoint(int y, int x){
    if (map[y][x] == 1 ){
      System.out.println("该点为障碍无法设置为终点");
    }else{
      endPointX = x;
      endPointY = y;
      setLCP();
    }
  }

  public int getDistance(int y, int x){
    return Math.abs(y - endPointY) + Math.abs(x - endPointX);
  }

  public void findWay(){
    if (startPointX < 1 && startPointY < 1 && endPointX < 1 && endPointY < 1){
      throw new RuntimeException("起点或终点异常，请重新检查设置");
    }
    setWay2(startPointY, startPointX);
    if (LACP <= LCP){
      System.out.println("成功找到最低成本路径(LCP):" + LCP);
      initMap(); // 初始化迷宫
      for (int i = 0; i < pathBest.size(); i+= 2) {
        System.out.println(pathBest.get(i) + " " + pathBest.get(i+1));
        map[pathBest.get(i)][pathBest.get(i+1)] = 2;
      }
      showMap();
    }else if (LACP < high*width){
      System.out.println("成功找到最低实际成本路径(LACP):" + LACP);
      initMap(); 
      for (int i = 0; i < pathBest.size(); i+= 2) {
        System.out.println(pathBest.get(i) + " " + pathBest.get(i+1));
        map[pathBest.get(i)][pathBest.get(i+1)] = 2;
      }
      showMap();
    }else {
      System.out.println(LACP);
      System.out.println("无法找到路径");
      showMap();
    } 
  }

  // 路径记录增加一步
  public void addPath(int y, int x){
    pathNow.add(y);
    pathNow.add(x);
  }

  // 路径记录减少一步
  public void removePath(){
    pathNow.remove(pathNow.size()-1);
    pathNow.remove(pathNow.size()-1);
  }

  /**
   * distance + ACP > LACP 
   * return false
   */
  public void setWay2(int y, int x){
    // count++;
    ACP++;
    addPath(y, x);
    if(y == endPointY && x == endPointX){
      // 当前点位是终点时
      if(ACP <= LACP){
        // 当前路径优于 记录路径
        LACP = ACP;
        pathBest = new ArrayList();
        pathBest.addAll(pathNow);
        ACP--;
        removePath();
        return;
      }else{
        // 记录路径优于当前路径
        ACP--;
        removePath();
        return;
      }
    }else if(ACP + getDistance(y, x) > LACP){
      // 当前点位所走的步数 + 到终点的最小距离 > 记录路径
      ACP--;
      removePath();
      return;
    }else{
      // 当前点位搜索
      map[y][x] = 2;
      if(map[y+1][x] == 0){
        setWay2(y + 1, x);
      }
      if(map[y-1][x] == 0){
        setWay2(y - 1, x);
      }
      if(map[y][x+1] == 0){
        setWay2(y, x + 1);
      }
      if(map[y][x-1] == 0){
        setWay2(y, x - 1);
      }
      // 当前点位搜索完毕 回溯到上一点位
      ACP--;
      map[y][x] = 0;
      removePath();
      return;
    }
  }
}
