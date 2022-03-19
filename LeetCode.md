# LeetCode







## 415

Easy

给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。

你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。

 

示例 1：

```
输入：num1 = "11", num2 = "123"
输出："134"
```


示例 2：

``` 
输入：num1 = "456", num2 = "77"
输出："533"
```

示例 3：

```
输入：num1 = "0", num2 = "0"
输出："0"
```






提示：

1 <= num1.length, num2.length <= 10**4
num1 和num2 都只包含数字 0-9
num1 和num2 都不包含任何前导零



解答



以158 + 37为例

使用length定位数字单位

使用charAt函数提取具体数值进行加减法运算

carry记录相加结果，及逢十进一的数值

**Java**

``` java
class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder target = new StringBuilder()  ;
        int i = num1.length()-1; // i = 2
        int j = num2.length()-1; // j = 1
        int carry = 0; 
        while (i >= 0 || j >= 0 || carry != 0){
        if(i >= 0){
            carry += num1.charAt(i) - '0';//1# carry = 0+8;  ,2# carry = 6 + 5
            i--; //1#  1 ,2#  1
        }
        if(j >= 0){
            carry += num2.charAt(j) - '0';//1#carry = 8+7; 2# carry = 11 + 3
            j--; //1#  0 ,2#   -1
        }
        
        target.append(carry % 10); //1# ''+5   ,2# '5' + 4
        carry /= 10;  // 1# 1    ,2# 1
        }
        
        return target.reverse().toString();
    }
}
```



***



## 728

自除数 是指可以被它包含的每一位数整除的数。

例如，128 是一个 自除数 ，因为 128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。
自除数 不允许包含 0 。

给定两个整数 left 和 right ，返回一个列表，列表的元素是范围 [left, right] 内所有的 自除数 。

 

示例 1：

```
输入：left = 1, right = 22
输出：[1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
```

示例 2:

``` 
输入：left = 47, right = 85
输出：[48,55,66,77]
```




提示：

``` 
1 <= left <= right <= 10**4
```



**java**

以target = 123为例

开始循环

1.取个位testNum= 123  % 10  = 3

123 % 3 == 0 通过则  123 /10 = 12 重新循环 失败则标记（divSup）跳出循环

直到 target == 0 且 divSup == true 则认为是自除数



``` java
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
```





***



## 1030

给定四个整数 row ,   cols ,  rCenter 和 cCenter 。有一个 rows x cols 的矩阵，你在单元格上的坐标是 (rCenter, cCenter) 。

返回矩阵中的所有单元格的坐标，并按与 (rCenter, cCenter) 的 距离 从最小到最大的顺序排。你可以按 任何 满足此条件的顺序返回答案。

单元格(r1, c1) 和 (r2, c2) 之间的距离为|r1 - r2| + |c1 - c2|。

 

示例 1：

```
输入：rows = 1, cols = 2, rCenter = 0, cCenter = 0
输出：[[0,0],[0,1]]
解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
```

示例 2：

```
输入：rows = 2, cols = 2, rCenter = 0, cCenter = 1
输出：[[0,1],[0,0],[1,1],[1,0]]
解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
[[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
```

示例 3：

```
输入：rows = 2, cols = 3, rCenter = 1, cCenter = 2
输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
```


提示：

1 <= rows, cols <= 100
0 <= rCenter < rows
0 <= cCenter < cols



**java**

1.先求出最大的距离 如下 0-23

2.编写不同距离的循环过程

在这里我使用了双通道解法

3.外层y坐标循环坐标从负到正一次循环

4.内层x坐标 正负判断两次

![image-20220227131746927](C:\Users\H1g0r\AppData\Roaming\Typora\typora-user-images\image-20220227131746927.png)



``` java
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
```



***



## 1237

给你一个函数  f(x, y) 和一个目标结果 z，函数公式未知，请你计算方程 f(x,y) == z 所有可能的正整数 数对 x 和 y。满足条件的结果数对可以按任意顺序返回。

尽管函数的具体式子未知，但它是单调递增函数，也就是说：

f(x, y) < f(x + 1, y)
f(x, y) < f(x, y + 1)
函数接口定义如下：

``` 
interface CustomFunction {
public:
  // Returns some positive integer f(x, y) for two positive integers x and y based on a formula.
  int f(int x, int y);
};
```

你的解决方案将按如下规则进行评判：

判题程序有一个由 CustomFunction 的 9 种实现组成的列表，以及一种为特定的 z 生成所有有效数对的答案的方法。
判题程序接受两个输入：function_id（决定使用哪种实现测试你的代码）以及目标结果 z 。
判题程序将会调用你实现的 findSolution 并将你的结果与答案进行比较。
如果你的结果与答案相符，那么解决方案将被视作正确答案，即 Accepted 。


示例 1：

``` 
输入：function_id = 1, z = 5
输出：[[1,4],[2,3],[3,2],[4,1]]
解释：function_id = 1 暗含的函数式子为 f(x, y) = x + y
以下 x 和 y 满足 f(x, y) 等于 5：
x=1, y=4 -> f(1, 4) = 1 + 4 = 5
x=2, y=3 -> f(2, 3) = 2 + 3 = 5
x=3, y=2 -> f(3, 2) = 3 + 2 = 5
x=4, y=1 -> f(4, 1) = 4 + 1 = 5
```

示例 2：

``` 
输入：function_id = 2, z = 5
输出：[[1,5],[5,1]]
解释：function_id = 2 暗含的函数式子为 f(x, y) = x * y
以下 x 和 y 满足 f(x, y) 等于 5：
x=1, y=5 -> f(1, 5) = 1 * 5 = 5
x=5, y=1 -> f(5, 1) = 5 * 1 = 5
```




提示：

1 <= function_id <= 9
1 <= z <= 100
题目保证 f(x, y) == z 的解处于 1 <= x, y <= 1000 的范围内。
在 1 <= x, y <= 1000 的前提下，题目保证 f(x, y) 是一个 32 位有符号整数。



**Java**

``` java
package dataStructAndAlgorithms.LeetCode;

import java.util.ArrayList;
import java.util.List;

public class LC_1237 {
  public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    int test ;
    for (int x = 1; x <= z ; x++){
        for (int y = 1; y <= z ; y++){
            test = customfunction.f(x,y);
            if (test==z){
                List<Integer> temp = new ArrayList<Integer>();
                temp.add(x);temp.add(y);
                result.add(temp);
                break;
            }else if (test > z){
                break;
            }
        }
    }
    return result;
  }
}
```



***



## 1441

给你一个目标数组 target 和一个整数 n。每次迭代，需要从  list = {1,2,3..., n} 中依序读取一个数字。

请使用下述操作来构建目标数组 target ：

Push：从 list 中读取一个新元素， 并将其推入数组中。
Pop：删除数组中的最后一个元素。
如果目标数组构建完成，就停止读取更多元素。
题目数据保证目标数组严格递增，并且只包含 1 到 n 之间的数字。

请返回构建目标数组所用的操作序列。

题目数据保证答案是唯一的。

示例 1：

``` 
输入：target = [1,3], n = 3
输出：["Push","Push","Pop","Push"]
解释： 
读取 1 并自动推入数组 -> [1]
读取 2 并自动推入数组，然后删除它 -> [1]
读取 3 并自动推入数组 -> [1,3]
```

示例 2：

``` 
输入：target = [1,2,3], n = 3
输出：["Push","Push","Push"]
```

示例 3：

``` 
输入：target = [1,2], n = 4
输出：["Push","Push"]
解释：只需要读取前 2 个数字就可以停止。
```

示例 4：

``` 
输入：target = [2,3,4], n = 4
输出：["Push","Pop","Push","Push","Push"]
```


提示：

1 <= target.length <= 100
1 <= target[i] <= 100
1 <= n <= 100
target 是严格递增的



**java**

``` java
class Solution {
    public List<String> buildArray(int[] target, int n) {
        List<String> operation = new ArrayList<>();
        int j = 0;
        for (int i = 1; i <= n; i++){
            if (j == target.length){
                break;
            }
            if (target[j] != i){
                operation.add("Push");
                operation.add("Pop");
            }else {
                operation.add("Push");
                j++;
            }
        }
        return operation;

    }
}
```



***



## 2023

给你一个 数字 字符串数组 nums 和一个 数字 字符串 target ，请你返回 nums[i] + nums[j] （两个字符串连接）结果等于 target 的下标 (i, j) （需满足 i != j）的数目。

 

示例 1：

```
输入：nums = ["777","7","77","77"], target = "7777"
输出：4
解释：符合要求的下标对包括：

- (0, 1)："777" + "7"
- (1, 0)："7" + "777"
- (2, 3)："77" + "77"
- (3, 2)："77" + "77"
```

示例 2：

``` 
输入：nums = ["123","4","12","34"], target = "1234"
输出：2
解释：符合要求的下标对包括

- (0, 1)："123" + "4"
- (2, 3)："12" + "34"
```

示例 3：

```
输入：nums = ["1","1","1"], target = "11"
输出：6
解释：符合要求的下标对包括

- (0, 1)："1" + "1"
- (1, 0)："1" + "1"
- (0, 2)："1" + "1"
- (2, 0)："1" + "1"
- (1, 2)："1" + "1"
- (2, 1)："1" + "1"
```




提示：

2 <= nums.length <= 100
1 <= nums[i].length <= 100
2 <= target.length <= 100
nums[i] 和 target 只包含数字。
nums[i] 和 target 不含有任何前导 0 。





**java**

``` java
class Solution {
    public int numOfPairs(String[] nums, String target) {
        int count = 0;
        for (int i = 0; i < nums.length; i++){
            if (target.indexOf(nums[i]) == 0){ // 匹配target的开头
                for (int j = 0; j < nums.length; j++){
                    if(target.equals(nums[i]+nums[j]) && j != i){
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
```









***





## 2091

给你一个下标从 0 开始的数组 nums ，数组由若干 互不相同 的整数组成。

nums 中有一个值最小的元素和一个值最大的元素。分别称为 最小值 和 最大值 。你的目标是从数组中移除这两个元素。

一次 删除 操作定义为从数组的 前面 移除一个元素或从数组的 后面 移除一个元素。

返回将数组中最小值和最大值 都 移除需要的最小删除次数。

示例 1：

```
输入：nums = [2,10,7,5,4,1,8,6]
输出：5
解释：
数组中的最小元素是 nums[5] ，值为 1 。
数组中的最大元素是 nums[1] ，值为 10 。
将最大值和最小值都移除需要从数组前面移除 2 个元素，从数组后面移除 3 个元素。
结果是 2 + 3 = 5 ，这是所有可能情况中的最小删除次数。
```

示例 2：

```
输入：nums = [0,-4,19,1,8,-2,-3,5]
输出：3
解释：
数组中的最小元素是 nums[1] ，值为 -4 。
数组中的最大元素是 nums[2] ，值为 19 。
将最大值和最小值都移除需要从数组前面移除 3 个元素。
结果是 3 ，这是所有可能情况中的最小删除次数。 
```

示例 3：

```
输入：nums = [101]
输出：1
解释：
数组中只有这一个元素，那么它既是数组中的最小值又是数组中的最大值。
移除它只需要 1 次删除操作。
```




提示：

1 <= nums.length <= 105
-105 <= nums[i] <= 105
nums 中的整数 互不相同



**分析**

假设有一个长度为10 的数组，它具有以下情况

**情况一**

最大最小的数分别在两边，那么经过裁剪后剩下中间黄色部分为最大可保留的数组，白色部分为最小可裁剪次数

![image-20220319010037205](C:\Users\H1g0r\AppData\Roaming\Typora\typora-user-images\image-20220319010037205.png)

**情况二**

最大最小的数均在靠左(右)的位置

![image-20220319010118205](C:\Users\H1g0r\AppData\Roaming\Typora\typora-user-images\image-20220319010118205.png)

现在我们的目标是求出黄色数组的大小。

**Java**

``` java
class LC_2091 {
    public int minimumDeletions(int[] nums) {
        int min = nums[0];
        int max = nums[0];
        int minIndex = 0;
        int maxIndex = 0;
        for (int i = 1; i < nums.length; i++){
            if (nums[i] > max){
                max = nums[i];
                maxIndex = i; // 最大值的地址
            }else if (nums[i] < min){
                min = nums[i];
                minIndex = i; // 最小值的地址
            }
        }
        // 让minIndex < maxIndex 方便计算
        if (maxIndex < minIndex){ 
            int temp = maxIndex;
            maxIndex = minIndex;
            minIndex = temp;
        }
        return nums.length - Math.max(minIndex, Math.max(maxIndex-1-minIndex,nums.length-1-maxIndex));
    }
}
```







***



## 2116

一个括号字符串是只由 '(' 和 ')' 组成的 非空 字符串。如果一个字符串满足下面 任意 一个条件，那么它就是有效的：

字符串为 ().
它可以表示为 AB（A 与 B 连接），其中A 和 B 都是有效括号字符串。
它可以表示为 (A) ，其中 A 是一个有效括号字符串。
给你一个括号字符串 s 和一个字符串 locked ，两者长度都为 n 。locked 是一个二进制字符串，只包含 '0' 和 '1' 。对于 locked 中 每一个 下标 i ：

如果 locked[i] 是 '1' ，你 不能 改变 s[i] 。
如果 locked[i] 是 '0' ，你 可以 将 s[i] 变为 '(' 或者 ')' 。
如果你可以将 s 变为有效括号字符串，请你返回 true ，否则返回 false 。

 

示例 1：

![img](https://assets.leetcode.com/uploads/2021/11/06/eg1.png)

输入：s = "))()))", locked = "010100"
输出：true
解释：locked[1] == '1' 和 locked[3] == '1' ，所以我们无法改变 s[1] 或者 s[3] 。
我们可以将 s[0] 和 s[4] 变为 '(' ，不改变 s[2] 和 s[5] ，使 s 变为有效字符串。
示例 2：

输入：s = "()()", locked = "0000"
输出：true
解释：我们不需要做任何改变，因为 s 已经是有效字符串了。
示例 3：

输入：s = ")", locked = "0"
输出：false
解释：locked 允许改变 s[0] 。
但无论将 s[0] 变为 '(' 或者 ')' 都无法使 s 变为有效字符串。


提示：

n == s.length == locked.length
1 <= n <= 10**5
s[i] 要么是 '(' 要么是 ')' 。
locked[i] 要么是 '0' 要么是 '1' 。



**java**

定义一个数字 num = 0

先从左到右遍历  

被锁且为")"  则 num+=1   否则 num -= 1 

判断 num是否大于0   是则 无法为效字符串

如 s="())(..." locked = "0110..." 

num =0 ，遍历时    -1，   +1 ， +1 ，num =1 >0 无法通过跳出 返回false

``` java
public class LC_2116 {
  public static void main (String[] args){
  
    

  }
  public boolean canBeValid(String s, String locked) {
    if (locked.length() % 2 != 0){
      return false; 
    }
    int lockNum = 0;
    for (int i=0;i < s.length(); i++){
      if (locked.charAt(i)!='0' && s.charAt(i) == ')'){
        lockNum++; //从左到右 计算被锁 ")"
      }else{
        lockNum--; // 平衡解决被锁的")"
      }
      if (lockNum>0){ // 如这种情况 "())(..." "0110..." i= 2 时 lock > 0无法通过
        return false;
      }
    }

    lockNum = 0;
    for (int i=s.length()-1;i >= 0; i--){ //同上列循环
      if (locked.charAt(i)!='0' && s.charAt(i) == '('){
        lockNum++;
      }else{
        lockNum--;
      }
      if (lockNum>0){
        return false;
      }
    }
    return true;
  }
}

```

