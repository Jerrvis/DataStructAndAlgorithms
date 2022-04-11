package src.Stack.SuffixCalculator;

import java.util.ArrayList;
import java.util.List;

public class SuffixCalculator {
  public static void main(String[] args) {

    /**
     * @author Jerrvis
     * @email 675926782@qq.com
     * 本数据为一个后缀表达的计算器支持括号运算
     */
    SuffixStack numStack = new SuffixStack();
    SuffixStack operStack = new SuffixStack();
    String expression = "87*474+674*(374+84+99)-28+207*8-686-7686";

    int index = 0;
    String sNum1 = "";
    String sNum2 = "";
    String sOper = "";
    int res = 0;
    String chS = "";
    char ch = ' ';
    String keepNum = "";

    while (true) {
      // 依次得到expression 每一个字符
      ch = expression.charAt(index);
      chS = "" + ch; // ch 字符串形式
      System.out.println(ch);

      if (operStack.isOper(chS)) { // ch为运算符号
        // 判断符号栈是否为空
        if (!operStack.isEmpty()) {
          // 如果读取的运算符优先级等于或低于符号栈顶
          if (operStack.priority(chS) <= operStack.priority(operStack.peek())) {
            numStack.push(operStack.pop());

            // 当前符号入栈
            operStack.push(chS);
          } else {
            operStack.push(chS);
          }

        } else {
          // 如果为空直接入栈
          operStack.push(chS);
        }

      } else if (operStack.isBracket(chS)) { // ch 为括号时
        if (ch == '(') { // ch 为'('时
          operStack.push(chS);
        } else {
          while (operStack.peek().charAt(0) != '(') {
            numStack.push(operStack.pop());
          }
          operStack.pop();
        }

      } else { // ch为数字时
        keepNum += ch;
        while (index < (expression.length() - 1) && numStack.isNumber(expression.charAt(index + 1))) { // expression的index是数字时
          index++;
          char tempCh = expression.charAt(index);
          System.out.println(tempCh);
          keepNum += tempCh;
        }
        numStack.push(keepNum); // 长数字完整入栈
        keepNum = "";
      }
      index++;
      if (index >= expression.length()) {
        // 扫描结束跳出循环
        break;
      }
    }

    while (true) {
      // 将所有的numStack 倒入 operStack
      if (numStack.isEmpty()) {
        break;
      }
      operStack.push(numStack.pop());
    }

    while (true) {
      if (operStack.isNumber(operStack.peek())) { // 当栈顶为数字时
        numStack.push(operStack.pop());
      } else { // 当栈顶为运算符时
        sNum2 = numStack.pop();
        sNum1 = numStack.pop();
        sOper = operStack.pop();
        res = operStack.cal(sNum1, sNum2, sOper);
        operStack.push("" + res);
      }
      if (operStack.getTop() == 0 && operStack.isNumber(operStack.peek())) { // 当栈中只有一个元素且为数字时跳出
        break;
      }
    }

    String res2 = operStack.pop();
    System.out.printf("表达式结果%s = %s\n", expression, res2);
  }
}

class SuffixStack {
  private List<String> stack; // 数组
  private int top = -1; // top表示栈顶

  // 构造器初始化栈
  public SuffixStack() {
    stack = new ArrayList<String>();
  }

  // 检查是否为空
  public boolean isEmpty() {
    return top == -1;
  }

  // 入栈
  public void push(String value) {
    top++;
    stack.add(value);
  }

  // 出栈
  public String pop() {
    if (isEmpty()) {
      throw new RuntimeException("栈空");
    }
    String temp = stack.get(top);
    stack.remove(top);
    top--;
    return temp;
  }

  // 显示栈顶
  public String peek() {
    return stack.get(top);
  }

  // 遍历展示栈
  public void showStack() {
    if (isEmpty()) {
      throw new RuntimeException("栈空");
    }
    for (int i = top; i >= 0; i--) {
      System.out.printf("Stack[%d] = %s\n", i, stack.get(i));
    }
  }

  // 是否为运算符
  public boolean isOper(String val) {
    char ch = val.charAt(0);
    return ch == '+' || ch == '-' || ch == '*' || ch == '/';
  }

  // 运算
  public int cal(String sNum1, String sNum2, String sOper) {
    int num1 = Integer.parseInt(sNum1);
    int num2 = Integer.parseInt(sNum2);
    int oper = sOper.charAt(0);
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

  // 计算优先级
  public int priority(String sOper) {
    int oper = sOper.charAt(0);
    if (oper == '*' || oper == '/') {
      return 1;
    } else if (oper == '+' || oper == '-') {
      return 0;
    } else {
      return -1;
    }
  }

  // get top
  public int getTop() {
    return top;
  }

  // 是否为括号
  public boolean isBracket(String sVal) {
    int val = sVal.charAt(0);
    return val == '(' || val == ')';
  }

  public boolean isBracket(char val) {
    return val == '(' || val == ')';
  }

  // 是否为数字
  public boolean isNumber(String sVal) {
    char val = sVal.charAt(0);
    if (val == '-' && sVal.length() > 1 && 48 <= sVal.charAt(1) && sVal.charAt(1) < 58) {
      return true;
    }
    return 48 <= val && val < 58;
  }

  public boolean isNumber(char val) {
    return 48 <= val && val < 58;
  }
}