package LeetcodeAlgorithmProblem;

import java.util.Stack;

/**
 * @author ZAY
 * 2.两数相加
 * 给你两个非空的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储―位数字。
 * 请你将两个数相加，并以相同形式返回—个表示和的链表。
 * 你可以假设除了数字О之外，这两个数都不会以О开头。
 */
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
/**
 * @author ZAY
 */
public class TwoNumListSum {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result=null;
        ListNode p1=l1;
        ListNode p2=l2;
        ListNode p3=result;
        int jinwei=0;
        while (p1 != null && p2 != null){
            if(p3 == null){
                result=new ListNode(((p1.val+p2.val)%10+jinwei)%10,null);
                p3=result;
            }else {
                p3.next=new ListNode(((p1.val+p2.val)%10+jinwei)%10,null);
                p3=p3.next;
            }
            if(p1.val+ p2.val+jinwei > 9){
                jinwei=1;
            }else {
                jinwei=0;
            }
            p1=p1.next;
            p2=p2.next;
        }
        //以下两个while只会执行一个,或者都不执行
        while (p1 != null){
            p3.next=new ListNode(((p1.val % 10)+jinwei)%10);
            if(p1.val%10+jinwei > 9){
                jinwei=1;
            }else {
                jinwei=0;
            }
            p3=p3.next;
            p1=p1.next;
        }
        while (p2 != null){
            p3.next=new ListNode(((p2.val % 10)+jinwei)%10);
            if(p2.val%10+jinwei > 9){
                jinwei=1;
            }else {
                jinwei=0;
            }
            p3=p3.next;
            p2=p2.next;
        }
        if(jinwei != 0){
            p3.next=new ListNode(1,null);
        }
        return result;
    }
    public static void main(String[] args){
        ListNode l1=new ListNode(1,null);
        ListNode l2=new ListNode(2,null);
        ListNode l3=new ListNode(3,null);
        ListNode l4=new ListNode(1,null);
        ListNode l5=new ListNode(2,null);
        l1.next=l2;
        l2.next=l3;
        l4.next=l5;
        ListNode result = addTwoNumbers(l1,l4);
        ListNode p=result;
        while (p != null){
            System.out.print(p.val+",");
            p=p.next;
        }
    }
}
