/**
 * @author ZAY
 * 将一个数组中的奇数放在数组左边，偶数放在数组右边
 */
public class OddEvenNumberDivide {
    public static boolean isOdd(int num){
        //位运算判断整数num的奇偶性，若num&1==1，则为奇数;若num&1==0，则为偶数
        return (num & 1) == 1;
    }
    public static void process(int[] list){
        if(list == null || list.length < 2){
            return;
        }
        int left=0;
        for(int i=0;i<list.length;i++){
            if(isOdd(list[i])){
                SortAlgorithm.swap(list,i,left);
                left++;
            }
        }
    }
    public static void main(String[] args){
        int[] list=new int[10];
        SortAlgorithm.createRandomList(list);
        SortAlgorithm.printList(list);
        process(list);
        SortAlgorithm.printList(list);
    }
}
