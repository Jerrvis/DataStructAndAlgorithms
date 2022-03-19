package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class LC_728 {
  public static void main(String[] args) {
    LC_728 lc = new LC_728();
    List<Integer> arr = lc.selfDividingNumbers(1, 22);

    System.out.println(arr.get(1));

  }

  public List<Integer> selfDividingNumbers(int left, int right) {
    List<Integer> arr = new ArrayList();
    boolean divSuc = true;
    int target;
    int testNum;
    for (int x = left; x <= right + 1; x++) {
      target = x;
      divSuc = true;
      while (divSuc && target != 0) {  // 判断 target是否为0
        testNum = target % 10;
        if (target != 0 && target % 10 == 0) { // 判断x是否存在0
          divSuc = false;
        } else if (x % testNum != 0) { // 用x判断。因为x为原目标不变，target不断变小
          divSuc = false; // 123 %2 != 0 失败标记
        } else { 
          target /= 10;
        }
      }
      if (divSuc) {
        arr.add(x);
      }
    }
    return arr;

  }
}
