package LeetCode;

public class LC_415 {
  public static void main (String[] args){
    
    LC_415 lc = new LC_415();
    String t = lc.addStrings("1648641","156161");
    System.out.println(t);
    System.out.println(1648641+156161);
    
  }

  public String addStrings(String num1, String num2) {
    StringBuilder target = new StringBuilder()  ;
    int i = num1.length()-1;
    int j = num2.length()-1;
    int carry = 0;
    while (i >= 0 || j >= 0 || carry != 0){
      if(i >= 0){
        carry += num1.charAt(i) - '0';
        i--;
      }
      if(j >= 0){
        carry += num2.charAt(j) - '0';
        j--;
      }
      
      target.append(carry % 10);
      carry /= 10;
    }
    
    return target.reverse().toString();
  }
}
