package list;

import list.util.SingleListUtils;
import list.util.SingleNode;

/**
 * @author ZAY
 * 判断一个单链表是不是回文 1,2,3,2,1
 * 另外增加快慢指针练习
 * 奇数链表: 快指针走完，慢指针在中点; 快指针走完，慢指针在中点前一个; 快指针走完，慢指针在中点后一个
 * 偶数链表: 快指针走完，慢指针指向中间两个中的前一个; 快指针走完，慢指针在中间两个中的后一个;
 *          快指针走完，慢指针在中间两个的前面; 快指针走完，慢指针在中间两个的后面
 */
public class SingleListPalindrome {
    public static boolean isPalindrome(SingleNode head){
        if(head == null){
            return false;
        }
        SingleNode p=head.getNext();
        SingleNode stack=new SingleNode(0);
        for(int i=1;i<=SingleListUtils.getLength(head) / 2;i++){
            SingleListUtils.addLast(stack,p.getData());
            p=p.getNext();
        }
        SingleListUtils.printList(stack);
        if(SingleListUtils.getLength(head) % 2==1){
            p=p.getNext();
        }
        while(stack.getNext() != null){
            if(p.getData() != SingleListUtils.removeLast(stack)){
                return false;
            }
            p=p.getNext();
        }
        return true;
    }
    public static void main(String[] args){
        SingleNode head1=new SingleNode(0);
        SingleListUtils.addLast(head1,1);
        SingleListUtils.addLast(head1,2);
        SingleListUtils.addLast(head1,3);
        SingleListUtils.addLast(head1,4);
        SingleListUtils.addLast(head1,4);
        SingleListUtils.addLast(head1,3);
        SingleListUtils.addLast(head1,2);
        SingleListUtils.addLast(head1,1);
        SingleListUtils.printList(head1);
        System.out.println(isPalindrome(head1));
        SingleNode head2=new SingleNode(0);
        SingleListUtils.addLast(head2,1);
        SingleListUtils.addLast(head2,2);
        SingleListUtils.addLast(head2,4);
        SingleListUtils.addLast(head2,5);
        SingleListUtils.addLast(head2,3);
        SingleListUtils.addLast(head2,9);
        SingleListUtils.addLast(head2,2);
        SingleListUtils.printList(head2);
        System.out.println(isPalindrome(head2));
    }
}
