/**
 * @author ZAY
 * 位运算实现加减乘除运算
 * 参考资料: https://blog.csdn.net/biglxl/article/details/113181803
 */
public class BitOperationFourOperations {
    public static int add(int a,int b){
        if(b == 0){
            return a;
        }else {
            int carry = (a & b) << 1;
            a = a ^ b;
            return add(a,carry);
        }
    }
    public static int add2(int a,int b){
        int carry;
        while (b != 0){
            carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }
    public static int subtraction(int a,int b){
        b = add2(~ b,1);
        return add2(a,b);
    }
    public static int multiplication(int a,int b){
        /*
          方法1: 根据乘法的定义进行累加,乘数为负数时单独考虑
          方法2: 下列方法，待研究……
         */
        int i=0,result=0,temp=b;
        if(b < 0){
            b = add2(~ b,1);
        }
        while (b != 0){
            if((b & 1) == 1){
                result = add2(result,a << i);
            }
            b = b >> 1;
            i = add2(i,1);
        }
        if(temp < 0){
            return add2(~ result,1);
        }
        return result;
    }
    public static int division(int a,int b){
        /*
          方法1: a=a-b 直到 a < b; 仅支持a,b都为正数
         */
        int tempA=a,tempB=b;
        int result = 0;
        if(b == 0){
            throw new ArithmeticException("/ by zero");
        }
        if(a == 0 || a < b){
            return 0;
        }
        if(a == Integer.MIN_VALUE && b == Integer.MAX_VALUE){
            return 1;
        }else if(b == Integer.MIN_VALUE){
            return 0;
        }else if(a == Integer.MIN_VALUE){
            if(b == -1){
                return Integer.MAX_VALUE;
            }
        }else{
            a = a < 0 ? add2(~ a,1) : a;
            b = b < 0 ? add2(~ b,1) : b;
            int i=30;
            while(i>=0){
                if((a >> i) >= b){
                    result = result | (1 << i);
                    a = subtraction(a,b << 1);
                }
                i = subtraction(i,1);
            }
        }
        return tempA < 0 ^ tempB < 0 ? add2(~result,1) : result;
    }
    public static void main(String[] args){
        System.out.println(add(12,13));
        System.out.println(add2(-12,-88));
        System.out.println(subtraction(12,13));
        System.out.println(multiplication(1500,-33));
        System.out.println(division(15,3));
    }
}
