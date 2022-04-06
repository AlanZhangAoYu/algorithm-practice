package LeetcodeAlgorithmProblem;

import java.util.Collections;
import java.util.HashSet;

/**
 * @author ZAY
 * 198.打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 */
public class RaidHomesAndPlunderHouses {
    public static void process(int[] nums, int nowPoint, int sum, HashSet<Integer> set){
        //nowPoint表示当前选择偷的位置下标
        //sum代表当前偷到多少钱
        if(nowPoint >= nums.length){
            set.add(sum);
            return;
        }
        //此时要么选择nowPoint的下下个,要么选择下下下个
        sum=sum+nums[nowPoint];
        process(nums,nowPoint+2,sum,set);
        sum=sum-nums[nowPoint];
        process(nums,nowPoint+3,sum,set);
    }
    public static int rob(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        if(nums.length == 2){
            return Math.max(nums[0],nums[1]);
        }
        HashSet<Integer> set=new HashSet<>();
        process(nums,0,0,set);
        process(nums,1,0,set);
        System.out.println(set);
        return Collections.max(set);
    }
    public static void main(String[] args){
        int[] list=new int[]{2,1,1,2};
        System.out.println(rob(list));
    }
}
