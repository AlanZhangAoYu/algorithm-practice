package LeetcodeAlgorithmProblem;

/**
 * @author ZAY
 * 1.两数之和
 * 给定一个整数数组nums和一个整数目标值 target，请你在该数组中找出和为目标值target 的那两个整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。你可以按任意顺序返回答案。
 */
public class TwoNumSum {
    public static int[] process(int[] nums,int target){
        int[] result = new int[2];
        for(int i=0;i< nums.length;i++){
            for(int j=i+1;j< nums.length;j++){
                if(nums[i]+nums[j] == target){
                    result[0]=i;
                    result[1]=j;
                    return result;
                }
            }
        }
        return result;
    }
    public static void main(String[] args){
        int[] nums={3,2,4};
        int[] result=process(nums,6);
        for(int i:result){
            System.out.print(i+",");
        }
    }
}
