import java.util.Random;

/**
 * @author ZAY
 * 排序算法学习
 */
public class SortAlgorithm {
    private static final Random random=new Random();
    public static void swap(int[] list,int i,int j){
        if(list == null || list.length < 2 || i < 0 || j >= list.length){
            System.out.println("数组不合法!");
            return;
        }
        if(i == j){
            //如果要交换的地址相同,异或操作就会把两个数都变为0
            System.out.println("不能交换相同位置的数!");
        }else{
            //异或操作,不格外开辟空间交换两个地址的值
            list[i] = list[i] ^ list[j];
            list[j] = list[i] ^ list[j];
            list[i] = list[i] ^ list[j];
        }
    }
    public static void swapNormal(int[] list,int i,int j){
        if(list == null || list.length < 2 || i < 0 || j >= list.length){
            System.out.println("数组不合法!");
            return;
        }
        int temp=list[j];
        list[j]=list[i];
        list[i]=temp;
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
    /**
     * 归并排序法
     *
     */
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
        // 比较左右两部分的元素，哪个小，把那个元素填入help中
        while (p1 <= mid && p2 <= right) {
            help[i++] = list[p1] < list[p2] ? list[p1++] : list[p2++];
        }
        // 上面的循环退出后，把剩余的元素依次填入到help中
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
        if (list == null || list.length < 2) {
            return;
        }
        sort(list, 0, list.length - 1);
    }

    /**
     * 快速排序法
     *
     */
    public static void quickSort(int[] list,int left,int right){
        if(list == null || list.length < 2){
            return;
        }
        swap(list,left+random.nextInt(list.length),right);
        int[] p=partition(list,left,right);
        quickSort(list,left,p[0]-1);
        quickSort(list,p[1]+1,right);
    }
    public static int[] partition(int[] list, int left, int right){
        if(list == null || list.length < 2){
            return null;
        }
        int less=left - 1;
        int more=right;
        while (left < more){
            if(list[left] < list[right]){
                swap(list,++less,left++);
            } else if (list[left] > list[right]) {
                swap(list,--more,left);
            } else {
                left++;
            }
        }
        swap(list,more,right);
        return new int[] {less+1,more};
    }

    /**
     * 堆排序
     * 堆: 1. 一个完全二叉树 2. 某个结点的值总是不大于(小根堆)或不小于(大根堆)其父结点的值
     * 二叉树: 当前节点list[index] 父节点list[(index-1)/2] 左孩子list[index*2+1] 右孩子list[index*2+2]
     */
    public static void heapInsert(int[] list, int index){
        if(list == null || list.length < 2){
            return;
        }
        //当前的list[index]与其父节点list[(index-1)/2]比较,若大于父节点,则与父结点交换,使整个数组构成大根堆
        while(list[index] > list[(index-1)/2]){
            swapNormal(list,index,(index-1)/2);
            index=(index-1)/2;
        }
    }
    public static void heapIfy(int[] list, int index, int heapSize){
        if(list == null || list.length < 2){
            return;
        }
        int leftChild = index*2+1,largest=0;
        //当前的list[index]与其左右孩子中最大的一个比较,若小于那个,则与其交换,使整个数组组成大根堆
        while (leftChild < heapSize){
            largest = leftChild+1 < heapSize && list[leftChild + 1] > list[leftChild] ? leftChild + 1 : leftChild;
            largest = list[index] > list[largest] ? index : largest;
            if (largest == index){
                break;
            }
            swapNormal(list,largest,index);
            index = largest;
            leftChild = index*2+1;
        }
    }
    public static void heapSort(int[] list){
        if(list == null || list.length < 2){
            return;
        }
        int heapSize= list.length;
        for(int i=0;i<list.length;i++){
            heapInsert(list,i);
        }
        //printList(list);
        swapNormal(list,0,heapSize-1);
        heapSize--;
        while (heapSize>0){
            //System.out.print(heapSize+":");
            //printList(list);
            heapIfy(list,0,heapSize);
            heapSize--;
            swapNormal(list,0,heapSize);
        }
    }
    public static void main(String[] args){
        int[] list = new int[10];
        createRandomList(list);
        printList(list);
        heapSort(list);
        printList(list);
    }
}
