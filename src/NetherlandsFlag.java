/**
 * @author ZAY
 * 荷兰国旗问题    (快速排序算法理解基础)
 * 给定一个数组arr和一个数num,请把小于等于num的数放在数组的左边,大于num的数放在数组的右边.左右两边的数不要求有序
 * 给定一个数组arr和一个数num,请把小于num的数放在数组的左边,等于num的数放在中间,大于num的数放在数组的右边.左右两边的数不要求有序
 */
public class NetherlandsFlag {
    public static void process1(int[] list,int num){
        if(list == null || list.length < 2){
            return;
        }
        int left=0;
        for(int i=0;i<list.length;i++){
            if(list[i]<=num){
                SortAlgorithm.swap(list,i,left);
                left++;
            }
        }
    }
    public static void process2(int[] list,int num){
        if(list == null || list.length < 2){
            return;
        }
        int left=0,right=list.length-1,i=0;
        while(i<list.length){
            System.out.print("("+i+")"+":");
            SortAlgorithm.printList(list);
            if(i == right){
                break;
            }
            if(list[i]<num){
                SortAlgorithm.swap(list,i,left);
                left++;
                i++;
            }else if(list[i]>num){
                SortAlgorithm.swap(list,i,right);
                right--;
            }else{
                i++;
            }
        }
    }
    public static void main(String[] args){
        int[] list=new int[10];
        int[] list1={4,62,50,72,18,6,50,49,50,99,63,47,55,46,25,50};
        SortAlgorithm.createRandomList(list);
        SortAlgorithm.printList(list);
        process1(list,50);
        SortAlgorithm.printList(list);
        SortAlgorithm.printList(list1);
        process2(list1,50);
        SortAlgorithm.printList(list1);
    }
}
