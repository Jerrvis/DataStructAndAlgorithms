# 算法



***



# 数据结构



## 稀疏数组(sparse array)



编写的五子棋程序中，有存盘退出和续上盘的功能。

![image-20220312171842404](./.assets/README/image-20220312171842404.png)

因为该二维数组的很多值是默认值 0, 因此记录了 很多没有意义的数据.-> 

### 基本介绍

当一个数组中大部分元素为０，或者为同一个值的数组时，可以使用稀疏数组来保存该数组。

稀疏数组的处理方法是:
1) 第一行，记录数组 一共有几行几列，有多少个不同的值
2) 其他行，把具有不同值的元素的行列及值记录在一个小规模的数组中，从而 缩小程序的规模







如下左边为原始数组 右边为稀疏数组

它能够省去很多重复的数字

![image-20220209095611480](./.assets/README/image-20220209095611480.png)



**应用实例**

地图，棋盘



### 代码



``` java
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


```

结果如下

上面为原数组

下面为稀疏数组

![image-20220209102720784](/image-20220209102720784.png)





***



## 队列

队列中有一种重要的队列叫做环形队列。

环形队列，其实是队列尾部和首部相接的结构，初始状态时，head和tail指针分别指向下标为0的位置，如图：

![image-20220317213012300](/image-20220317213012300.png)

需要关注：***\*循环队列为空的判断条件是head==tail\**。**

随着新元素入队，head指针保持不边，tail指针往后移动，指向一个空闲位置。同样当有元素出队列时，head指针也会自然往后移动。

![image-20220317213036654](/image-20220317213036654.png)



**Java**

```java
import java.util.Scanner;

import javax.management.RuntimeErrorException;

public class cycleArrayQueue {
  public static void main(String[] args) {
    CircleArray Queue = new CircleArray(8);
    char key = ' '; // 接受用户输入
    Scanner scan = new Scanner(System.in);
    boolean loop = true;

    while (loop) {
      System.out.println("s(show): 显示队列");
      System.out.println("e(exit): 退出程序");
      System.out.println("a(add):  添加数据到队列");
      System.out.println("g(get):  从队列中取出数据");
      System.out.println("h(head): 查看队列头数据");
      key = scan.next().charAt(0); // 收字符指令
      switch (key) {
        case 's':
          try{
            Queue.showQueue();
          }catch(Exception e){
            e.printStackTrace();
          }
          break;
        case 'e':
          System.out.println("退出程序");
          loop = false;
          break;
        case 'a':
          System.out.println("請輸入要入列的數");
          int value = scan.nextInt();
          try{
            Queue.addElement(value);
          }catch(Exception e){
            e.printStackTrace();
          }
          break;
        case 'g':
          try{
            System.out.println(Queue.getElement());
          }catch(Exception e){
            e.printStackTrace();
          }
          break;
        case 'h':
          try{
            Queue.getHead();
          }catch(Exception e){
            e.printStackTrace();
          }
          break;
        default:
          break;
      }

    }

  }

}

class CircleArray {
  private int maxSize; // 最大容量
  private int front; // 队列头
  private int rear; // 队列尾后一个为空的位置
  private int[] arr; // 存放数据的队列
  // private boolean maxDist; // 判断是否刚好相差一圈首尾相连,可以让实际容量 == 最大容量

  // 构造函数创建队列
  public CircleArray(int arrMaxSize) {
    arr = new int[arrMaxSize];
    front = 0;
    rear = 0;
    maxSize = arrMaxSize;
    // maxDist = false;
  }

  // 判断队列是否为满
  public boolean isFull() {
    if ((rear + 1)%maxSize == front) {
      return true;
    }
    return false;
  }

  // 判断队列是否为空
  public boolean isEmpty() {
    if (front == rear) {
      return true;
    }
    return false;
  }

  // 加入元素
  public void addElement(int Element) {
    if (isFull()) {
      throw new RuntimeException("队列满，不能加入元素");
    }
    arr[rear] = Element;
    rear++;
    rear = rear % maxSize; // 当到达maxSize位置归0
  }

  // 提出元素
  public int getElement() {
    int temp; // 临时保存变量
    if (isEmpty()) {
      throw new RuntimeException("队列空，不能提取元素");
    }
    temp = arr[front];
    front++;
    front = front % maxSize;
    return temp;
  }

  // 遍历所有的元素
  public void showQueue() {
    if (isEmpty()) {
      throw new RuntimeException("队列空，不能提取元素");
    }
    for (int i = front; (i % maxSize) != rear; i++) {
      System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
    }
  }

  // 显示头元素
  public int getHead() {
    if (isEmpty()) {
      throw new RuntimeException("队列空，不能顯示元素");
    }
    return arr[front];
  }
}

```



***

# LeetCode



[LeetCode](./LeetCode.md)

