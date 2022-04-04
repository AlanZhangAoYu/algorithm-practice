package list.util;

/**
 * @author ZAY
 * 手写 list.util.HashMap(JDK1.7)
 * JDK1.7: 数组+链表
 * JDK1.8: 数组+链表+红黑树
 */
class Node<K,V>{
    private K key;
    private V value;
    private Node<K,V> next;
    public Node(K key,V value){
        this.key=key;
        this.value=value;
        this.next=null;
    }
    public K getKey() {
        return key;
    }
    public void setKey(K key) {
        this.key = key;
    }
    public V getValue() {
        return value;
    }
    public void setValue(V value) {
        this.value = value;
    }
    public Node<K, V> getNext() {
        return next;
    }
    public void setNext(Node<K, V> next) {
        this.next = next;
    }
}
/**
 * @author ZAY
 */
class HashMap<K,V> {
    /**
     * DEFAULT_INIT_LENGTH: 存哈希值数组的默认长度
     * MAX_NODE_LENGTH: 每个 hashList可存 node的最大长度, 若大于此则触发扩容
     * hashList: 存放哈希值求模后的 Node组
     */
    private static int DEFAULT_INIT_LENGTH = 16;
    private static final int MAX_NODE_LENGTH = 20;
    private Node<K,V>[] hashList=new Node[DEFAULT_INIT_LENGTH];
    public void put(K k,V v){
        if(k==null || v==null){
            return;
        }
        Node<K,V> node=new Node<>(k,v);
        //求出node要存放在hashList中的数组下标location
        int location=Math.abs(hash(node.getKey()) % hashList.length);
        if(hashList[location] != null){
            Node<K,V> p=hashList[location];
            int size=0;
            while (p.getNext() != null){
                size++;
                p=p.getNext();
            }
            p.setNext(node);
            if(size >= MAX_NODE_LENGTH){
                expansion();
            }
        }else {
            hashList[location]=node;
        }
    }
    public V get(K k) {
        int location = Math.abs(hash(k) % hashList.length);
        if (hashList[location] != null) {
            Node<K, V> p = hashList[location];
            while (p.getNext() != null) {
                if (p.getKey() == k) {
                    return p.getValue();
                }
                p = p.getNext();
            }
        }
        return null;
    }
    public void expansion(){
        /*
          扩容,生成新的hashList,是之前的2倍(DEFAULT_INIT_LENGTH * 2)
          JDK1.7 是先扩容,在添加。具体put是否扩容需要两个条件:
            1、 存放新值的时候当前某个hashList串中的Node的个数必须大于等于阈值
            2、 存放新值的时候当前存放数据发生hash碰撞（当前key计算的hash值换算出来的数组下标位置已经存在值）
          JDK1.8 是先添加,在扩容。具体put是否扩容需要满足一个条件:
            当table中存储值的个数大于等于threshold的时候，进行扩容。容量为原来的2倍
            红黑树转化条件: 数组的长度大于64的时候，链表长度大于8才会从链表转换为红黑树
         */
        DEFAULT_INIT_LENGTH = DEFAULT_INIT_LENGTH << 1;
        Node<K,V>[] newHashList=new Node[DEFAULT_INIT_LENGTH];
        int location;
        for (Node<K, V> kvNode : hashList) {
            Node<K, V> p = kvNode;
            if (p == null) {
                continue;
            } else if (p.getNext() == null) {
                location = Math.abs(hash(p) % DEFAULT_INIT_LENGTH);
                newHashList[location] = p;
            } else {
                while (p.getNext() != null) {
                    location = Math.abs(hash(p) % DEFAULT_INIT_LENGTH);
                    newHashList[location] = p;
                    p = p.getNext();
                }
            }
        }
        hashList=newHashList;
        System.gc();
    }
    public int hash(Object key){
        int h;
        return key==null ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}

/**
 * @author ZAY
 */
public class MyHashMapTest {
    public static void main(String[] args){
        HashMap<Integer,String> hashMap=new HashMap<>();
        for(int i=0;i<100;i++){
            hashMap.put(i,"数据"+i);
        }
        //System.out.println(hashMap.hash(22)% 16+" "+ hashMap.hash(66)%16);
        System.out.println(hashMap.get(45));
    }
}
