package LeetCode;

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
