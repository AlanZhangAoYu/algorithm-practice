package list.util;

import java.util.Scanner;

/**
 * @author ZAY
 * 单链表的各种工具函数(查插删改)
 */
public class SingleListUtils {
    public static void addLast(SingleNode head, int num){
        //在尾部插入
        if(head == null){
            return;
        }
        SingleNode p = head;
        SingleNode newData=new SingleNode(num);
        while (p.getNext() != null){
            p=p.getNext();
        }
        p.setNext(newData);
        head.setData(head.getData()+1);
    }
    public static void addFirst(SingleNode head, int num){
        //在头部插入
        if(head == null){
            return;
        }
        SingleNode newData=new SingleNode(num);
        newData.setNext(head.getNext());
        head.setNext(newData);
        head.setData(head.getData()+1);
    }
    public static void add(SingleNode head, int data,int location){
        //在指定位置插入元素
        if(head == null){
            return;
        }
        if(location > getLength(head) + 1 || location <= 0){
            System.out.println("超出列表长度或位置不合法!!!");
            return;
        }
        if(location == 1){
            addFirst(head,data);
            return;
        }
        SingleNode p =head.getNext();
        for(int i=1;i<location-1;i++){
            p=p.getNext();
        }
        SingleNode newData=new SingleNode(data);
        newData.setNext(p.getNext());
        p.setNext(newData);
        head.setData(head.getData()+1);
    }
    public static int removeFirst(SingleNode head){
        //删除并返回第一个元素
        if(head == null){
            return -0xffff;
        }
        if(getLength(head) == 0){
            System.out.println("当前列表长度为0，无法删除!");
            return -0xffff;
        }
        int data=head.getNext().getData();
        head.setNext(head.getNext().getNext());
        System.gc();
        head.setData(head.getData()-1);
        return data;
    }
    public static int removeLast(SingleNode head){
        //删除并返回最后一个元素
        if(head == null){
            return -0xffff;
        }
        if(getLength(head) == 0){
            System.out.println("当前列表长度为0，无法删除!");
            return -0xffff;
        }
        SingleNode p=head.getNext();
        SingleNode q=head;
        while(p.getNext() != null){
            p=p.getNext();
            q=q.getNext();
        }
        int data=p.getData();
        q.setNext(null);
        System.gc();
        head.setData(head.getData()-1);
        return data;
    }
    public static int remove(SingleNode head,int location){
        //删除并返回指定位置元素
        if(head == null){
            return -0xffff;
        }
        if(location > getLength(head) || location <= 0){
            System.out.println("超出列表长度或位置不合法!!!");
            return -0xffff;
        }
        if(head.getData() == 0){
            System.out.println("当前列表长度为0，无法删除!");
            return -0xffff;
        }
        if(location == 1){
            return removeFirst(head);
        }
        if(location == head.getData()){
            return removeLast(head);
        }
        SingleNode p=head.getNext();
        for(int i=1;i<location-1;i++){
            p=p.getNext();
        }
        int data=p.getNext().getData();
        p.setNext(p.getNext().getNext());
        System.gc();
        return data;
    }
    public static int get(SingleNode head,int location){
        //返回指定位置元素
        if(head == null){
            return -0xffff;
        }
        if(location > getLength(head) || location <= 0){
            System.out.println("超出列表长度或位置不合法!!!");
            return -0xffff;
        }
        if(head.getData() == 0){
            System.out.println("当前列表长度为0，无法获取!");
            return -0xffff;
        }
        SingleNode p=head.getNext();
        for(int i=0;i<location - 1;i++){
            p=p.getNext();
        }
        return p.getData();
    }
    public static void set(SingleNode head,int location,int data){
        //更改指定位置的元素
        if(head == null){
            return;
        }
        if(location > getLength(head) || location <= 0){
            System.out.println("超出列表长度或位置不合法!!!");
            return;
        }
        if(head.getData() == 0){
            System.out.println("当前列表长度为0，无法更改!");
            return;
        }
        SingleNode p=head.getNext();
        for(int i=0;i<location - 1;i++){
            p=p.getNext();
        }
        p.setData(data);
    }
    public static void swap(SingleNode head,int location1,int location2){
        if(head == null){
            return;
        }
        if(location1 > getLength(head) || location1 <= 0 || location2 > head.getData() || location2 <=0){
            System.out.println("超出列表长度或位置不合法!!!");
            return;
        }
        if(head.getData() <= 1){
            System.out.println("当前列表长度小于或等于1，无法交换!");
            return;
        }
        SingleNode p=head.getNext();
        SingleNode q=head.getNext();
        for(int i=0;i<location1 - 1;i++){
            p=p.getNext();
        }
        for(int i=0;i<location2 - 1;i++){
            q=q.getNext();
        }
        int temp=p.getData();
        p.setData(q.getData());
        q.setData(temp);
    }
    public static int getLength(SingleNode head){
        //比head.getData()更安全的获取列表长度
        if(head == null){
            return 0;
        }
        int length=0;
        SingleNode p=head.getNext();
        while (p != null){
            length++;
            p = p.getNext();
        }
        head.setData(length);
        return length;
    }
    public static void printList(SingleNode head){
        if(head == null){
            return;
        }
        SingleNode p=head.getNext();
        System.out.print("共有"+head.getData()+"个数:");
        while (p != null){
            System.out.print(p.getData()+",");
            p=p.getNext();
        }
        System.out.println();
    }
    public static void main(String[] args){
        SingleNode head=new SingleNode(0);
        System.out.println("0--退出程序");
        System.out.println("1--在尾部插入");
        System.out.println("2--在头部插入");
        System.out.println("3--显示列表所有数");
        System.out.println("4--指定位置插入数据");
        System.out.println("5--查找指定位置数据");
        System.out.println("6--删除并返回指定位置元素");
        System.out.println("7--删除并返回第一个元素");
        System.out.println("8--删除并返回最后一个元素");
        System.out.println("9--更改指定位置的元素");
        System.out.println("10--交换两个元素位置");
        Scanner scanner=new Scanner(System.in);
        int choice;
        while(true){
            System.out.print("请输入功能代码:");
            choice= scanner.nextInt();
            if(choice == 0){
                break;
            }else if(choice == 1){
                System.out.print("请输入要插入的数据:");
                int data= scanner.nextInt();
                addLast(head,data);
            }else if(choice == 2){
                System.out.print("请输入要插入的数据:");
                int data= scanner.nextInt();
                addFirst(head,data);
            }else if(choice == 3){
                printList(head);
            }else if(choice == 4){
                System.out.print("请输入要插入的数据:");
                int data= scanner.nextInt();
                System.out.print("请输入要插入的位置:");
                int location= scanner.nextInt();
                add(head,data,location);
            }else if(choice == 5){
                System.out.print("请输入要获取的位置:");
                int data= scanner.nextInt();
                System.out.println("第"+data+"个数是:"+get(head,data));
            }else if(choice == 6){
                System.out.print("请输入要删除的位置:");
                int location= scanner.nextInt();
                System.out.println("成功删除第"+location+"个元素:"+remove(head,location));
            }else if(choice == 7){
                System.out.println("成功删除第一个元素:"+removeFirst(head));
            }else if(choice == 8){
                System.out.println("成功删除最后一个元素:"+removeLast(head));
            }else if(choice == 9){
                System.out.print("请输入要更改的位置:");
                int location = scanner.nextInt();
                System.out.print("请输入要更改的值:");
                int data = scanner.nextInt();
                set(head,location,data);
            }else if(choice == 10){
                System.out.print("请输入第一个数的位置:");
                int location1=scanner.nextInt();
                System.out.print("请输入第二个数的位置:");
                int location2=scanner.nextInt();
                swap(head,location1,location2);
            }else{
                System.out.println("没有此功能代码，请重新输入!");
            }
        }
    }
}
