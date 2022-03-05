import java.util.PriorityQueue;

/**
 * @author ZAY
 * 最小代价分割金条
 * 一根金条切成两半要花费和金条长度数值一样的钱(切割30长度的金条要花费30元钱，20长度20元钱；不管切成两边多大)
 * 几个人要分金条，怎么分最省钱
 * 例如给定一个数组 {10,20,30}，代表有三个人要分10+20+30=60长度的金条，第一个人分10长度，第二个人20，第三个人30长度
 * 若先分出10再分出20，则要花费60+50=110元；若先分出20再分出10，则要花费60+40=100元；若先分出30再分出10，则要花费60+30=90元
 * 求出花费最小的方案
 */
public class MinimumCostSplitGoldBar {
    public static int verification(int[] nums){
        //对数器(暴力求出最小代价)
        return 0;
    }
    public static int process1(int[] nums){
        //整个问题抽象为已知二叉树的所有叶子节点，且二叉树的所有子树的根都等于其子节点的和，请求该二叉树所有非叶子节点的值的和的最小值
        if(nums == null){
            return 0;
        }
        PriorityQueue<Integer> queue=new PriorityQueue<>();
        for(int num : nums){
            queue.add(num);
        }
        int sum=0,crr;
        while (queue.size()>1){
            crr=queue.poll()+queue.poll();
            queue.add(crr);
            sum=sum+crr;
        }
        return sum;
    }
    public static int process2(int[] nums){
        /*
          给所有要求排序，每次分割出当前最大值以保证剩余最小，下次分割的花费最小
          为什么这种贪心策略不行?????
         */
        if(nums == null){
            return 0;
        }
        SortAlgorithm.insertSort(nums);
        SortAlgorithm.printList(nums);
        int sum=0;
        int crr=0;
        for (int num : nums) {
            crr = num + crr;
            sum = sum + crr;
        }
        return sum-nums[0];
    }
    public static void main(String[] args){
        int[] nums={29,52,31,44,10};
        System.out.println("最小代价(二叉树):"+process1(nums));
        System.out.println("最小代价(排序)(?):"+process2(nums));
    }
}
