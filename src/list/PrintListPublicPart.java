package list;

import list.util.SingleList.SingleListUtils;
import list.util.SingleList.SingleNode;

/**
 * @author ZAY
 * 给定两个有序链表的头指针 head1 和 head2，打印链表的公共部分
 * 要求：如果两个链表长度之和为N，则时间复杂度要求为 O(N)，格外空间复杂度要求为 O(1)
 */
public class PrintListPublicPart {
    public static void main(String[] args){
        SingleNode head1=new SingleNode(0);
        SingleNode head2=new SingleNode(0);
        SingleNode result=new SingleNode(0);
        for(int i=5;i<=10;i++){
            SingleListUtils.addLast(head1,i);
        }
        for(int i=3;i<=7;i++){
            SingleListUtils.addLast(head2,i);
        }
        SingleListUtils.printList(head1);
        SingleListUtils.printList(head2);
        SingleNode p=head1.getNext();
        SingleNode q=head2.getNext();
        while(p !=null && q !=null){
            if(p.getData() < q.getData()){
                p = p.getNext();
            }else if(p.getData() > q.getData()){
                q = q.getNext();
            }else{
                SingleListUtils.addLast(result,p.getData());
                p=p.getNext();
                q=q.getNext();
            }
        }
        SingleListUtils.printList(result);
    }
}
