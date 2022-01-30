package list.util;

import java.util.Scanner;

/**
 * @author ZAY
 * 单链表的各种工具函数(查插删改)
 */
public class SingleListUtils {
    public static void insertData(SingleNode head, int num){
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
        System.out.println("2--显示列表所有数");
        System.out.println("3--插入数据");
        System.out.println("4--查找数据");
        System.out.println("5--删除数据");
        Scanner scanner=new Scanner(System.in);
        int choice;
        while(true){
            System.out.print("请输入功能代码:");
            choice= scanner.nextInt();
            if(choice == 0){
                break;
            } else if(choice == 1){
                System.out.print("请输入要插入的数据:");
                int data= scanner.nextInt();
                SingleListUtils.insertData(head,data);
            }else if(choice == 2){
                SingleListUtils.printList(head);
            }else{
                System.out.println("没有此功能代码，请重新输入!");
            }
        }
    }
}
