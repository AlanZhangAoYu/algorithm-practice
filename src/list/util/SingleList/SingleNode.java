package list.util.SingleList;

import java.util.Objects;

/**
 * @author ZAY
 * 单链表节点类定义
 */
public class SingleNode {
    private int data;
    private SingleNode next;
    public SingleNode(){
        this.next=null;
    }
    public SingleNode(int data){
        this.data=data;
        this.next=null;
    }
    public int getData() {
        return data;
    }
    public SingleNode getNext() {
        return next;
    }
    public void setData(int data) {
        this.data = data;
    }
    public void setNext(SingleNode next) {
        this.next = next;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SingleNode that = (SingleNode) o;
        return data == that.data && Objects.equals(next, that.next);
    }
    @Override
    public int hashCode() {
        return Objects.hash(data, next);
    }
}
