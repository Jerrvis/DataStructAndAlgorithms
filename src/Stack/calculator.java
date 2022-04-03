package src.Stack;

public class calculator {

  public static void main(String[] args) {
    ArrayStack numStack = new ArrayStack(100);
    ArrayStack operStack = new ArrayStack(99);
    String expression = "87*474+674*374+84+99-28+207*8-686-7686";
    int index = 0;
    int num1 = 0;
    int num2 = 0;
    int oper = 0;
    int res = 0;
    char ch = ' ';
    String keepNum = "";

    while (true) {
      // 依次得到expression 每一个字符
      ch = expression.charAt(index);
      System.out.println(ch);
      // 判断ch是否为符号
      if (operStack.isOper(ch)) {
        // 判断符号栈是否为空
        if (!operStack.isEmpty()) {
          // 如果读取的运算符优先级等于或低于符号栈顶
          if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
            num2 = numStack.pop();
            num1 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            // 运算结果入栈
            numStack.push(res);

            // 当前符号入栈
            operStack.push(ch);
          } else {
            operStack.push(ch);

          }

        } else {
          // 如果为空直接入栈
          operStack.push(ch);
        }
      } else {
        // numStack.push(ch - 48);
        // 判断后面数字位数，整理入栈
        
        // 
        keepNum += ch;
        while(index < (expression.length()-1) && !operStack.isOper(expression.charAt(index+1))){
          index++;
          char tempCh = expression.charAt(index);
          keepNum += tempCh;
        }
        numStack.push(Integer.parseInt(keepNum));
        keepNum = "";
      }
      index++;
      if (index >= expression.length()){
        // 扫描结束跳出循环
        break;
      }
    }

    while(true){
      if(operStack.isEmpty()){
        break;
      }
      num2 = numStack.pop();
      num1 = numStack.pop();
      oper = operStack.pop();
      res = numStack.cal(num1, num2, oper);
      numStack.push(res);
    }
    int res2 = numStack.pop();
    System.out.printf("表达式结果%s = %d\n", expression, res2);
  }
}

class ArrayStack {
  private int maxSize; // 栈的大小
  private int[] stack; // 数组
  private int top = -1; // top表示栈顶

  // 构造器初始化栈
  public ArrayStack(int maxSize) {
    this.maxSize = maxSize;
    stack = new int[this.maxSize];
  }

  // 检查是否为满
  public boolean isFull() {
    return top == maxSize - 1;
  }

  // 检查是否为空
  public boolean isEmpty() {
    return top == -1;
  }

  // 入栈
  public void push(int value) {
    if (isFull()) {
      System.out.println("栈满");
      return;
    }
    top++;
    stack[top] = value;
  }

  // 出栈
  public int pop() {
    if (isEmpty()) {
      throw new RuntimeException("栈空");
    }
    int temp = stack[top];
    top--;
    return temp;
  }

  // 显示栈顶
  public int peek() {
    return stack[top];
  }

  // 遍历展示栈
  public void showStack() {
    if (isEmpty()) {
      throw new RuntimeException("栈空");
    }
    for (int i = top; i >= 0; i--) {
      System.out.printf("Stack[%d] = %d\n", i, stack[i]);
    }
  }

  public boolean isOper(char val) {
    return val == '+' || val == '-' || val == '*' || val == '/';
  }

  public int cal(int num1, int num2, int oper) {
    int res = 0;
    switch (oper) {
      case '+':
        res = num1 + num2;
        break;
      case '-':
        res = num1 - num2;
        break;
      case '*':
        res = num1 * num2;
        break;
      case '/':
        res = num1 / num2;
        break;
      default:
        break;
    }
    return res;
  }

  public int priority(int oper) {
    if (oper == '*' || oper == '/') {
      return 1;
    } else if (oper == '+' || oper == '-') {
      return 0;
    } else {
      return -1;
    }
  }

  // get top
  public int getTop(){
    return top;
  }
  
  // 是否为括号
  public boolean isBracket(char val) {
    return val == '(' || val == ')';
  }

  // 是否为数字
  public boolean isNumber(char val) {
    return 48 <= val && val < 58;
  }
}
