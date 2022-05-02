package list;

import list.util.SingleList.SingleListUtils;
import list.util.SingleList.SingleNode;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ZAY
 * 判断一个单链表是否有环，若没有，返回null; 若有，返回入环节点
 * 常规方法: 将链表中的每一个节点的 hashCoode 写入一个Set集合，每写入一个数判断它下一个节点地址在不在集合中。若整个链表写入完毕都没有重复，没有环;
 *         若存在重复节点，有环，返回重复的那个节点
 * 牛逼方法: 快慢指针; 若最终快指针指向 null，没有环; 若最终有重合，有环; 此时将快指针重新指向链表开头，慢指针不变，两个指针再每次同时向前走一步,
 *         快慢指针再次重合时，都指向入环节点 (不知道为什么这样!!!不会证明!!记住!!)
 */
public class JudgeWhetherTheSingleLinkedListHasRing {
    public static SingleNode process1(SingleNode head){
        //常规使用Set集合方法
        if(head == null){
            return null;
        }
        SingleNode p=head.getNext();
        Set<SingleNode> set=new HashSet<>();
        while (p.getNext() != null){
            set.add(p);
            if(!set.contains(p)){
                return p;
            }
            p=p.getNext();
        }
        return null;
    }
    public static SingleNode process2(SingleNode head){
        //快慢指针方法
        if(head == null){
            return null;
        }
        SingleNode fast=head.getNext();
        SingleNode slow=head.getNext();
        while (fast != null){
            slow=slow.getNext();
            fast=fast.getNext().getNext();
            if(fast == slow){
                //此时链表一定有环
                fast=head.getNext();
                while (fast.getNext() != null){
                    fast=fast.getNext();
                    slow=slow.getNext();
                    if(fast==slow){
                        return fast;
                    }
                }
            }
        }
        return null;
    }
    public static void main(String[] args){
        SingleNode head1=new SingleNode(0);
        for(int i=1;i<=10;i++){
            SingleListUtils.addLast(head1,i);
        }
        SingleListUtils.printList(head1);
        //人为制造一个有环链表head2，入环节点是5
        SingleNode head2=new SingleNode(0);
        for(int i=1;i<=10;i++){
            SingleListUtils.addLast(head2,i);
        }
        SingleNode p=head2.getNext();
        SingleNode q=head2.getNext();
        while (p.getNext() != null){
            p=p.getNext();
        }
        for(int i=1;i<=5;i++){
            q=q.getNext();
        }
        p.setNext(q);

        if(process2(head1) == null){
            System.out.println("head1没有环");
        }else{
            System.out.println("head1有环，入环节点为:"+process2(head1).getData());
        }
        if(process2(head2) == null){
            System.out.println("head2没有环");
        }else{
            System.out.println("head2有环，入环节点为:"+process2(head2).getData());
        }
    }
}
