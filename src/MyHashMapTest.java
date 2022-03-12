/**
 * @author ZAY
 * 手写 HashMap
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
     * 存哈希值数组的默认长度
     */
    private static final int DEFAULT_INIT_LENGTH = 16;
    /**
     * 存放哈希值求模后的 Node组
     */
    private Node<K,V>[] hashList=new Node[DEFAULT_INIT_LENGTH];

    public void put(K k,V v){
        if(k==null || v==null){
            return;
        }
        Node<K,V> node=new Node<>(k,v);
        //求出node要存放在hashList中的数组下标location
        int location=hash(node.getKey()) % hashList.length;
        if(hashList[location] != null){
            Node<K,V> p=hashList[location];
            while (p.getNext() != null){
                p=p.getNext();
            }
            p.setNext(node);
        }else {
            hashList[location]=node;
        }
    }
    public V get(K k) {
        int location = hash(k) % hashList.length;
        if(hashList[location] == null){
            return null;
        }else if(hashList[location].getNext() != null){
            return hashList[location].getValue();
        }else{
            Node<K,V> p=hashList[location];
            if(p.getKey() != k){
                p=p.getNext();
            }
            return p.getValue();
        }
    }
    public void expansion(){
        //扩容
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
        hashMap.put(4,"HAHA");
        hashMap.put(9,"JUJU");
        hashMap.put(6,"BUHP");
        hashMap.put(4,"LUht");
        System.out.println(hashMap.get(4));
        System.out.println(hashMap.get(9));
        System.out.println(hashMap.get(3));
    }
}
