package list;

import list.util.SingleList.SingleListUtils;
import list.util.SingleList.SingleNode;

/**
 * @author ZAY
 * 反转单链表
 */
public class SingleListReverse {
    public static SingleNode reverse1(SingleNode head){
        if(head == null){
            return null;
        }
        SingleNode prev=null;
        SingleNode q=head;
        while (q != null){
            SingleNode next=q.getNext();
            q.setNext(prev);
            prev=q;
            q=next;
        }
        return prev;
    }
    public static void reverse2(SingleNode head){
        for(int i=1;i<=SingleListUtils.getLength(head) / 2;i++){
            SingleListUtils.swap(head,i,head.getData()-i+1);
        }
    }
    public static void main(String[] args){
        SingleNode head=new SingleNode(0);
        for(int i=1;i<=10;i++){
            SingleListUtils.addLast(head,i);
        }
        SingleListUtils.printList(head);
        head=reverse1(head);
        SingleListUtils.printList(head);
    }
}
