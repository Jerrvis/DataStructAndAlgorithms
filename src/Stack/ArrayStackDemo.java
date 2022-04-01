package src.Stack;

import java.util.Scanner;

public class ArrayStackDemo {
  public static void main(String[] args) {
    ArrayStack stack = new ArrayStack(4);
    String key = "";
    boolean loop = true;
    Scanner scanner = new Scanner(System.in);

    while (loop) {
      System.out.println("show:显示栈");
      System.out.println("exit:退出程序");
      System.out.println("push:表示数据入栈");
      System.out.println("pop:表示出栈");
      System.out.println("请输入选择");

      key = scanner.next();
      switch (key) {
        case "show":
          try {
            stack.showStack();
          } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e.getMessage());
          }
          break;
        case "exit":
          System.out.println("退出程序");
          loop = false;
          break;
        case "push":
          int value = scanner.nextInt();
          stack.push(value);
          break;
        case "pop":
          try {
            int res = stack.pop();
            System.out.printf("出栈的数据是：%d\n", res);
          } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
          }
          break;
        default:
          break;
      }
    }
    scanner.close();
  }
}

// class ArrayStack {
//   private int maxSize; // 栈的大小
//   private int[] stack; // 数组
//   private int top = -1; // top表示栈顶

//   // 构造器初始化栈
//   public ArrayStack(int maxSize) {
//     this.maxSize = maxSize;
//     stack = new int[this.maxSize];
//   }

//   // 检查是否为满
//   public boolean isFull() {
//     return top == maxSize - 1;
//   }

//   // 检查是否为空
//   public boolean isEmpty() {
//     return top == -1;
//   }

//   // 入栈
//   public void push(int value) {
//     if (isFull()) {
//       System.out.println("栈满");
//       return;
//     }
//     top++;
//     stack[top] = value;
//   }

//   // 出栈
//   public int pop() {
//     if (isEmpty()) {
//       throw new RuntimeException("栈空");
//     }
//     int temp = stack[top];
//     top--;
//     return temp;
//   }

//   // 遍历展示栈
//   public void showStack() {
//     if (isEmpty()) {
//       throw new RuntimeException("栈空");
//     }
//     for (int i = top; i >= 0; i--) {
//       System.out.printf("Stack[%d] = %d\n", i, stack[i]);
//     }
//   }
// }
