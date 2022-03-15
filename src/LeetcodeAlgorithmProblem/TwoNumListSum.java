package LeetcodeAlgorithmProblem;

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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result=null;
        ListNode p1=l1;
        ListNode p2=l2;
        while ( p1.next !=null && p2.next != null){
            ListNode newNum = null;
            if(p1.val+p2.val>9 || (p1.val+ p2.val) % 10 + 1 ==10){
                newNum=new ListNode((p1.val + p2.val) % 10,null);
            }else {
                newNum=new ListNode((p1.val+ p2.val)%10,null);
            }
            p1=p1.next;
            p2=p2.next;
        }
        //以下两个while只会执行一个
        while (p1.next != null){

        }
        while(p2.next != null){

        }
        return result;
    }
    public static void main(String[] args){
    }
}
