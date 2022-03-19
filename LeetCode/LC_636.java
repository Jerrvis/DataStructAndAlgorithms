package LeetCode;

import java.util.Arrays;
import java.util.List;

public class LC_636 {
  public static void main (String[] args){
    LC_636 lc = new LC_636();
    String[] name  = {"0:start:0","1:start:2","1:end:5","0:end:6"};
    int[] pp = lc.exclusiveTime(2, Arrays.asList(name));
    for (int o:pp){
      System.out.println(o);
      
    }
    
  }
  public int[] exclusiveTime(int n, List<String> logs) {
    int[] Runtime = new int[n];
    int runningFun =0;
    boolean isRunning = false;
    int runningFrom = 0;
    String[] command;
    for (int i = 0; i < logs.size(); i++){
      command = logs.get(i).split(":");
      if (command[1].equals("start")){ // 开始指令
        if (!isRunning){ //指令为开始且没有正在运行的函数 
          runningFrom = Integer.parseInt(command[2]);
          runningFun = Integer.parseInt(command[0]);
          isRunning = true;
        }else{ //指令为开始且有正在运行的函数 "0:start:0" "1:start:2"

          Runtime[runningFun] += Integer.parseInt(command[2]) -runningFrom; //开始 函数0运行时间为 2-0
          runningFrom = Integer.parseInt(command[2]);
          runningFun = Integer.parseInt(command[0]);
        }
      }else {// 指令为结束
        
        Runtime[runningFun] += Integer.parseInt(command[2]) +1 -runningFrom;
        isRunning = false;
      }
    }
    return Runtime;

  }
}
