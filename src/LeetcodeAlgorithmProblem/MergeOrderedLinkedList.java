package LeetcodeAlgorithmProblem;

/**
 * @author ZAY
 * 21.合并两个有序链表
 * 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的
 */
public class MergeOrderedLinkedList {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode result=null;
        ListNode p1=list1;
        ListNode p2=list2;
        ListNode r=result;
        while (p1 != null && p2 != null){
            if(p1.val <= p2.val){
                if(r == null){
                    result=new ListNode(p1.val,null);
                    r=result;
                }else {
                    r.next=new ListNode(p1.val,null);
                    r=r.next;
                }
                p1=p1.next;
            }else {
                if(r == null){
                    result=new ListNode(p2.val,null);
                    r=result;
                }else {
                    r.next=new ListNode(p2.val,null);
                    r=r.next;
                }
                p2=p2.next;
            }
        }
        //以下while只会执行一个
        while (p1 != null){
            if(r == null){
                result=new ListNode(p1.val,null);
                r=result;
            }else {
                r.next=new ListNode(p1.val,null);
                r=r.next;
            }
            p1=p1.next;
        }
        while (p2 != null){
            if(r == null){
                result=new ListNode(p2.val,null);
                r=result;
            }else {
                r.next=new ListNode(p2.val,null);
                r=r.next;
            }
            p2=p2.next;
        }
        return result;
    }
    public static void main(String[] args){

    }
}
