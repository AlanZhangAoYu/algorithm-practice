package LeetcodeAlgorithmProblem;

/**
 * @author ZAY
 * 9.回文数
 * 给你一个整数x，如果x是一个回文整数，返回true ;否则，返回false 。
 * 回文数是指正序(从左向右）和倒序(从右向左)读都是—样的整数。
 */
public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        String str=String.valueOf(x);
        char[] charList=str.toCharArray();
        for(int i=0;i<charList.length / 2;i++){
            if(charList[i] != charList[charList.length-i-1]){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){

    }
}
