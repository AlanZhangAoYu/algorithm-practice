package list;

import list.util.SingleListUtils;
import list.util.SingleNode;

/**
 * @author ZAY
 * 判断一个单链表是否有环，若没有，返回null; 若有，返回入环节点
 * 常规方法: 将链表中的每一个节点的 hashCoode 写入一个Set集合，每写入一个数判断它下一个节点地址在不在集合中。若整个链表写入完毕都没有重复，没有环;
 *         若存在重复节点，有环，返回重复的那个节点
 * 牛逼方法: 快慢指针; 若最终快指针指向 null，没有环; 若最终有重合，有环; 此时将快指针重新指向链表开头，慢指针不变，两个指针再每次同时向前走一步,
 *         快慢指针再次重合时，都指向入环节点 (不知道为什么这样!!!不会证明!!记住!!)
 */
public class JudgeWhetherTheSingleLinkedListHasRing {
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
    }
}
