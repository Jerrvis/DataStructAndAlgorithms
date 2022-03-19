package src.DoubleLinked;

public class DoubleLinked {
  public static void main (String[] args){
    ShipNode ship1 = new ShipNode(1564, "Laffey", "Benson");
    ShipNode ship2 = new ShipNode(74362, "Shoukaku", "AB5");
    ShipNode ship3 = new ShipNode(35638, "NewJersey", "Iowa");
    ShipNode ship4 = new ShipNode(844, "Hutten", "H");
    ShipNodesList shipList = new ShipNodesList();
    shipList.addByNo(ship1);
    shipList.addByNo(ship3);
    shipList.addByNo(ship4);
    shipList.addByNo(ship2);
    shipList.deleteNode(844);
    shipList.showList();

    shipList.changeNode(1564, "Kronshtadt", "Kron");
  }
}


class ShipNodesList{
  // 定义头结点，不要修改
  private ShipNode head = new ShipNode(0, "", "");
  
  //添加节点到尾部
  public void add(ShipNode newNode){
    ShipNode temp = head;

    while (true){
      if(temp.next == null){
        break;
      }
      temp = temp.next; // 记录最后一个为空的节点
    }
    newNode.prev = temp;
    temp.next = newNode;
  }

  // 按照no的顺序添加(插入)节点
  public void addByNo(ShipNode shipNode){
    ShipNode temp = head;
    while (true){
      if(temp.next == null){
        temp.next = shipNode;
        shipNode.prev = temp;
        return;
      }else if (temp.next.no == shipNode.no){
        throw new RuntimeException("节点 "+shipNode.no+" 已存在,请调用changeNode()函数修改");
      }else if (temp.next.no > shipNode.no){
        ShipNode temp2 = temp.next;
        temp.next = shipNode;
        shipNode.next = temp2;

        temp2.prev = shipNode;
        shipNode.prev = temp;
        return;
      }
      temp = temp.next;
    }
  }

  // 删除 node.no == i 的节点
  public void deleteNode(int i){
    ShipNode temp = head;
    while (true){
      if (temp.next == null || temp.next.no > i){ // 判断为null必须放在前面
        throw new RuntimeException("链表中不存在节点 "+ i);
      }else if(temp.next.no == i) {
        temp.next = temp.next.next;
        temp.next.prev = temp;
        return;
      }
      temp = temp.next;
    }
  }

  // 修改节点数据
  public void changeNode(int i, String sName, String sClassName){
    ShipNode temp = head;
    while (true){
      if (temp.next == null || temp.next.no > i){ // 判断为null必须放在前面
        throw new RuntimeException("链表中不存在该节点 " + i);
      }else if(temp.next.no == i) {
        temp = temp.next;
        temp.name = sName;
        temp.className = sClassName;
        System.out.println("修改成功：" + temp.toString());
        return;
      }
      temp = temp.next;
    }
  }

  // 显示链表
  public void showList(){
    // 
    if(head.next == null){
      System.out.println("链表为空");
      return;
    }
    ShipNode temp = head;
    while (true){
      if (temp == null){ 
        break;
      }
      System.out.println(temp);
      temp = temp.next;
    }

  }
}

// 定义单个节点
class ShipNode{
  public int no;
  public String name;
  public String className;
  public ShipNode prev;
  public ShipNode next;

  //构造器
  public ShipNode(int sNo, String sName, String sClassName){
    this.no = sNo;
    this.name = sName;
    this.className = sClassName;
  }

  // 添加toString方法
  @Override
  public String toString(){
    String prevStr = prev == null ? "null" : "" + prev.no;
    String nextStr = next == null ? "null" : "" + next.no; // 空则输出null  否则输出号码
    return "ShipNode [node=" + no + ", prev="+ prevStr + ", className=" + className + ", name=" + name + ", next=" + nextStr + "]";
  }
}
