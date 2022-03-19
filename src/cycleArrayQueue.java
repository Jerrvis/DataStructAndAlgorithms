package src;

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
