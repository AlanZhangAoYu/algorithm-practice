package list.util;

/**
 * @author ZAY
 * 前缀树节点类定义
 */
public class TrieNode {
    /**
     * pass: 记录通过该节点的字符串数
     * end: 记录以该节点为结束的字符串数
     * next: 记录下一个节点是否存在，next[1] == null代表该节点以下没有 'b';next[25] != null代表该节点以下有 'z'
     */
    private int pass;
    private int end;
    private final TrieNode[] next;
    public TrieNode(){
        this.pass=0;
        this.end=0;
        this.next=new TrieNode[26];
    }
    public void setPass(int pass) {
        this.pass = pass;
    }
    public void setEnd(int end) {
        this.end = end;
    }
    public void setNext(TrieNode next,int location) {
        this.next[location] = next;
    }
    public int getPass() {
        return pass;
    }
    public int getEnd() {
        return end;
    }
    public TrieNode getNext(int location) {
        return this.next[location];
    }
}
