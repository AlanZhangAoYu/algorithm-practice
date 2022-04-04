package list.util;

import java.util.*;
import java.util.HashMap;

/**
 * @author ZAY
 * 并查集中的基本元素的包装类
 */
class Element<V>{
    private V value;
    public Element(V value){
        this.value=value;
    }
    public V getValue() {
        return value;
    }
    public void setValue(V value) {
        this.value = value;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Element<?> element = (Element<?>) o;
        return Objects.equals(value, element.value);
    }
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

class JointSearchSet<V>{
    /**
     * elementMap 基本元素与其包装类的对应表
     * fatherMap 并查集中每一个元素与其指向的元素的对应表(相当于链表节点中的next的作用)
     * sizeMap 并查集中每个集合 (用头元素来代表) 有多少个元素的对应表
     */
    private HashMap<V,Element<V>> elementMap;
    private HashMap<Element<V>,Element<V>> fatherMap;
    private HashMap<Element<V>,Integer> sizeMap;

    /**
     * 初始化并查集
     * @param list 用户传入的元素列表
     */
    public JointSearchSet(List<V> list){
        this.elementMap = new HashMap<>();
        this.fatherMap = new HashMap<>();
        this.sizeMap = new HashMap<>();
        for (V value : list) {
            Element<V> element=new Element<>(value);
            elementMap.put(value,element);
            fatherMap.put(element,element);
            sizeMap.put(element,1);
        }
    }

    /**
     * 输入并查集中任意一个节点元素,返回其一路向上走最终的头元素
     * @param element 要查询头元素的节点元素
     * @return 要查询的节点元素的头元素
     */
    public Element<V> findHead(Element<V> element){
        Stack<Element<V>> pathStack = new Stack<>();
        while (element != fatherMap.get(element)){
            //原本findHead方法只需要这个while的，但为了查询效率引入栈做优化
            pathStack.push(element);
            element=fatherMap.get(element);
        }
        while (!pathStack.isEmpty()){
            fatherMap.put(pathStack.pop(),element);
        }
        return element;
    }

    /**
     * 判断两个元素是否存在于同一个集合里
     * @param a 传入的一个元素
     * @param b 传入的另一个元素
     * @return 返回是否存在于同一个集合中
     */
    public boolean isSameSet(V a,V b){
        if(elementMap.containsKey(a) && elementMap.containsKey(b)){
            return findHead(elementMap.get(a)).equals(findHead(elementMap.get(b)));
        }
        return false;
    }

    /**
     * 将两个元素所在的集合合并成一个集合
     * @param a 一个元素
     * @param b 另一个元素
     */
    public void union(V a,V b){
        if(elementMap.containsKey(a) && elementMap.containsKey(b)){
            Element<V> aHead = findHead(elementMap.get(a));
            Element<V> bHead = findHead(elementMap.get(b));
            if(!aHead.equals(bHead)){
                //比较 aFather 集合与 bFather 集合中元素数量谁大谁小,小的接在大的的后面
                int aHeadSize = sizeMap.get(aHead);
                int bHeadSize = sizeMap.get(bHead);
                if(aHeadSize >= bHeadSize){
                    fatherMap.put(bHead,aHead);
                    sizeMap.put(aHead,aHeadSize+bHeadSize);
                    sizeMap.remove(bHead);
                }else {
                    fatherMap.put(aHead,bHead);
                    sizeMap.put(bHead,aHeadSize+bHeadSize);
                    sizeMap.remove(aHead);
                }
            }
        }
    }
}
/**
 * @author ZAY
 * 并查集
 */
public class MyJointSearchSetTest {
    public static void main(String[] args){
        ArrayList<Integer> list=new ArrayList<>();
        for(int i=1;i<=10;i++){
            list.add(i);
        }
        JointSearchSet<Integer> set=new JointSearchSet<>(list);
        System.out.println(set.isSameSet(2,5));
        set.union(2,5);
        System.out.println(set.isSameSet(2,5));
        set.union(8,6);
        System.out.println(set.isSameSet(2,6));
        set.union(2,6);
        System.out.println(set.isSameSet(2,6));
    }
}
