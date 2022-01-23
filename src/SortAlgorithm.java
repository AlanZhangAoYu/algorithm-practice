import java.util.Random;

/**
 * @author ZAY
 * 排序算法学习
 */
public class SortAlgorithm {
    public static void swap(int[] list,int i,int j){
        if(list == null || list.length<2){
            System.out.println("数组不合法!");
            return;
        }
        //异或操作,不格外开辟空间交换两个地址的值
        if(i == j){
            //如果要交换的地址相同,异或操作就会把两个数都变为0
            System.out.println("不能交换相同位置的数!");
        }else{
            list[i] = list[i] ^ list[j];
            list[j] = list[i] ^ list[j];
            list[i] = list[i] ^ list[j];
        }
    }
    public static void printList(int[] list){
        if(list == null || list.length<2){
            System.out.println("数组不合法!");
            return;
        }
        for (int data : list) {
            System.out.print(data+",");
        }
        System.out.println();
    }
    public static void createRandomList(int[] list){
        if(list == null || list.length < 2){
            return;
        }
        Random random=new Random();
        for(int i=0;i<list.length;i++){
            list[i]= random.nextInt(100);
        }
    }
    public static void selectSort(int[] list){
        //选择排序法
        if(list == null || list.length<2){
            System.out.println("数组不合法!");
            return;
        }
        for(int i=0;i<list.length;i++){
            for(int j=i;j<list.length;j++){
                if(list[j]<list[i]){
                    swap(list,i,j);
                }
            }
        }
    }
    public static void bubbleSort(int[] list){
        //冒泡排序法
        if(list == null || list.length<2){
            System.out.println("数组不合法!");
            return;
        }
        for(int i= list.length-1;i>0;i--){
            for(int j=0;j<i;j++){
                if(list[j]>list[j+1]){
                    swap(list,j,j+1);
                }
            }
        }
    }
    public static void insertSort(int[] list){
        //插入排序法
        if(list == null || list.length<2){
            System.out.println("数组不合法!");
            return;
        }
        for(int i=0;i<list.length;i++){
            for(int j=i;j>0;j--){
                if(list[j]<list[j-1]){
                    swap(list,j,j-1);
                }else {
                    break;
                }
            }
        }
    }
    private static void sort(int[] list, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        // 对左侧子序列进行递归排序
        sort(list, left, mid);
        // 对右侧子序列进行递归排序
        sort(list, mid + 1, right);
        // 合并
        merge(list, left, mid, right);
    }
    private static void merge(int[] list, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int i = 0;
        int p1 = left;
        int p2 = mid + 1;
        // 比较左右两部分的元素，哪个小，把那个元素填入temp中
        while (p1 <= mid && p2 <= right) {
            help[i++] = list[p1] < list[p2] ? list[p1++] : list[p2++];
        }
        // 上面的循环退出后，把剩余的元素依次填入到temp中
        // 以下两个while只有一个会执行
        while (p1 <= mid) {
            help[i++] = list[p1++];
        }
        while (p2 <= right) {
            help[i++] = list[p2++];
        }
        // 把最终的排序的结果复制给原数组
        for (i = 0; i < help.length; i++) {
            list[left + i] = help[i];
        }
    }
    public static void mergeSort(int[] list){
        //归并排序法
        if (list == null || list.length < 2) {
            return;
        }
        sort(list, 0, list.length - 1);
    }
    public static void main(String[] args){
        int[] list = new int[10];
        createRandomList(list);
        printList(list);
        mergeSort(list);
        printList(list);
    }
}
