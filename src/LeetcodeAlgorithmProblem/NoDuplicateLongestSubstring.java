package LeetcodeAlgorithmProblem;

/**
 * @author ZAY
 * 3.无重复字符的最长子串
 * 给定一个字符串s，请你找出其中不含有重复字符的最长子串的长度。
 */
public class NoDuplicateLongestSubstring {
    public static int lengthOfLongestSubstring(String s) {
        char[] list=s.toCharArray();

        return 0;
    }
    public static boolean hasRepeatChar(String str){
        //判断str中是否含有重复字符
        char[] list=str.toCharArray();
        for(int i=0;i<list.length;i++){
            for(int j=i+1;j<list.length;j++){
                if(list[i] == list[j]){
                    return true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args){

    }
}
