package LeetcodeAlgorithmProblem;

/**
 * 一个人要走上共有n层台阶的楼梯， 其每迈一步都有两种选择: 往前跨上1个台阶或者跨上2个阶梯
 * 假设开始时此人站在第一层台阶，那么走到n层一共有多少种不同的走法?（提示：此人已经站在第一台阶，总共还需要走n-1步）
 *
 * 请选用自己熟悉的语言设计一个程序来求解此问题，要求输入一个自然数n，程序能输出问题答案（并在程序中自己编写测试用例进行验证）
 *
 * 举例: 3层台阶一共有2种走法: [1台阶，1台阶] 与[2台阶]， 此时输入n=3，输出则为2
 * @author ZAY
 */
public class ClimbStairs {
    public static int process(int n){
        //爬到第n个台阶,要么从第n-1个上来,要么从第n-2个上来
        if(n == 1) {
            return 1;
        }
        if(n == 2) {
            return 2;
        }
        int a=1;
        int b=2;
        for(int i=3;i<=n;i++){
            int sum=a+b;
            a=b;
            b=sum;
        }
        return b;
    }

    public static void main(String[] args) {
        System.out.println(process(5));
    }
}
