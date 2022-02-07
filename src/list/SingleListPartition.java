package list;

import list.util.SingleListUtils;
import list.util.SingleNode;

/**
 * @author ZAY
 * 单链表给定一个数num，小于num的数放左边，等于num的数放中间，大于num的数放右边，且每个数之间顺序保持一致(内存连续的数组做不到，但单链表可以)
 * 常规方法: 将链表中的数复制到一个内存连续的数组中，在执行类似于快速排序中的 partition 操作，最后将处理好的数组复制回原链表，原有顺序不一定一致
 */
public class SingleListPartition {
    public static SingleNode partition(SingleNode head,int num){
        //类似于 "桶" 的思想，三个"桶"分别装大于等于小于num的数，最后在串到一起
        SingleNode low=new SingleNode(0);
        SingleNode equals=new SingleNode(0);
        SingleNode high=new SingleNode(0);
        SingleNode p=head.getNext();
        while (p != null){
            if(p.getData() < num){
                SingleListUtils.addLast(low,p.getData());
            }
            if(p.getData() == num){
                SingleListUtils.addLast(equals,p.getData());
            }
            if(p.getData() > num){
                SingleListUtils.addLast(high,p.getData());
            }
            p=p.getNext();
        }
        System.out.print("low:");
        SingleListUtils.printList(low);
        System.out.print("equals:");
        SingleListUtils.printList(equals);
        System.out.print("high:");
        SingleListUtils.printList(high);
        p=low;
        if(low.getNext() != null){
            p=low.getNext();
            while (p.getNext() != null){
                p=p.getNext();
            }
        }
        if(equals.getNext() != null){
            p.setNext(equals.getNext());
            while (p.getNext() != null){
                p=p.getNext();
            }
        }
        if(high.getNext() != null){
            p.setNext(high.getNext());
        }
        if(low.getNext() != null){
            return low;
        }
        if(equals.getNext() != null){
            return equals;
        }
        if(high.getNext() != null){
            return high;
        }
        return null;
    }
    public static void main(String[] args){
        SingleNode head=new SingleNode(0);
        SingleListUtils.addLast(head,81);
        SingleListUtils.addLast(head,5);
        SingleListUtils.addLast(head,45);
        SingleListUtils.addLast(head,16);
        SingleListUtils.addLast(head,34);
        SingleListUtils.addLast(head,71);
        SingleListUtils.addLast(head,55);
        SingleListUtils.addLast(head,69);
        SingleListUtils.addLast(head,99);
        SingleListUtils.addLast(head,27);
        SingleListUtils.printList(head);
        SingleNode result=partition(head,50);
        SingleListUtils.printList(result);
    }
}
