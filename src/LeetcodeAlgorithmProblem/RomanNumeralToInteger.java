package LeetcodeAlgorithmProblem;

/**
 * @author ZAY
 * 13.罗马数字转整数
 */
public class RomanNumeralToInteger {
    public static int romanToInt(String s) {
        int result=0;
        char[] list=s.toCharArray();
        for(int i=0;i<list.length;i++){
            if(list[i]=='I'){
                if(i+1< list.length){
                    if(list[i+1]=='V'){
                        result=result+4;
                        i++;
                        continue;
                    }
                    if(list[i+1]=='X'){
                        result=result+9;
                        i++;
                        continue;
                    }
                }
                result=result+1;
            }else if(list[i] == 'V'){
                result=result+5;
            }else if(list[i] == 'X'){
                if(i+1< list.length){
                    if(list[i+1]=='L'){
                        result=result+40;
                        i++;
                        continue;
                    }
                    if(list[i+1]=='C'){
                        result=result+90;
                        i++;
                        continue;
                    }
                }
                result=result+10;
            }else if(list[i] == 'L'){
                result=result+50;
            }else if(list[i] == 'C'){
                if(i+1< list.length){
                    if(list[i+1]=='D'){
                        result=result+400;
                        i++;
                        continue;
                    }
                    if(list[i+1]=='M'){
                        result=result+900;
                        i++;
                        continue;
                    }
                }
                result=result+100;
            }else if(list[i] == 'D'){
                result=result+500;
            }else if(list[i] == 'M'){
                result=result+1000;
            }
        }
        return result;
    }
    public static void main(String[] args){
        String str="III";
        System.out.println(romanToInt(str));
    }
}
