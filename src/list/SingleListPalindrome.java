package list;

import list.util.SingleList.SingleListUtils;
import list.util.SingleList.SingleNode;

/**
 * @author ZAY
 * 判断一个单链表是不是回文 1,2,3,2,1
 * 另外增加快慢指针练习
 * 奇数链表: 快指针走完，慢指针在中点; 快指针走完，慢指针在中点前一个; 快指针走完，慢指针在中点后一个
 * 偶数链表: 快指针走完，慢指针指向中间两个中的前一个; 快指针走完，慢指针指向中间两个中的后一个;
 *          快指针走完，慢指针在中间两个的前面; 快指针走完，慢指针在中间两个的后面
 */
public class SingleListPalindrome {
    public static boolean isPalindrome(SingleNode head){
        if(head == null){
            return false;
        }
        SingleNode p=head.getNext();
        //用一个栈来存储链表前半截,再将链表后半截与出栈的数相比较，只要有一个不相等，就不是回文链表; 若最后栈弹空，是回文链表
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
        System.out.println("===偶数长度的链表=====");
        SingleNode head1=new SingleNode(0);
        SingleListUtils.addLast(head1,1);
        SingleListUtils.addLast(head1,2);
        SingleListUtils.addLast(head1,3);
        SingleListUtils.addLast(head1,4);
        SingleListUtils.addLast(head1,5);
        SingleListUtils.addLast(head1,6);
        SingleListUtils.addLast(head1,2);
        SingleListUtils.addLast(head1,1);
        SingleListUtils.printList(head1);
        System.out.println(isPalindrome(head1));

        //快指针走完，慢指针指向中间两个中的前一个
        SingleNode fast=head1.getNext();
        SingleNode slow =head1;
        while(fast != null){
            fast=fast.getNext();
            fast=fast.getNext();
            slow = slow.getNext();
        }
        System.out.println("slow:"+ slow.getData());

        //快指针走完，慢指针指向中间两个中的后一个
        fast=head1.getNext();
        slow =head1.getNext();
        while(fast != null){
            fast=fast.getNext();
            fast=fast.getNext();
            slow = slow.getNext();
        }
        System.out.println("slow:"+ slow.getData());

        //快指针走完，慢指针在中间两个的前面
        fast=head1.getNext();
        slow =head1;
        while(fast != null){
            fast=fast.getNext();
            fast=fast.getNext();
            slow = slow.getNext();
        }
        System.out.println("(一直有错,不会)slow:"+ slow.getData());

        //快指针走完，慢指针在中间两个的后面
        fast=head1.getNext();
        slow =head1.getNext().getNext();
        while(fast != null){
            fast=fast.getNext();
            fast=fast.getNext();
            slow = slow.getNext();
        }
        System.out.println("slow:"+ slow.getData());

        System.out.println("===奇数长度的链表====");
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

        //快指针走完，慢指针在中点
        fast=head2.getNext();
        slow =head2.getNext();
        while(fast.getNext() != null){
            fast=fast.getNext();
            fast=fast.getNext();
            slow = slow.getNext();
        }
        System.out.println("slow:"+ slow.getData());

        //快指针走完，慢指针在中点前一个
        fast=head2.getNext();
        slow =head2;
        while(fast.getNext() != null){
            fast=fast.getNext();
            fast=fast.getNext();
            slow = slow.getNext();
        }
        System.out.println("slow:"+ slow.getData());

        //快指针走完，慢指针在中点后一个
        fast=head2.getNext();
        slow =head2.getNext().getNext();
        while(fast.getNext() != null){
            fast=fast.getNext();
            fast=fast.getNext();
            slow = slow.getNext();
        }
        System.out.println("slow:"+ slow.getData());
    }
}
