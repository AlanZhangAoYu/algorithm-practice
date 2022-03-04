import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author ZAY
 * 贪心算法练习
 * 给定一个字符串组，你需要按照一定顺序拼接里面的所有字符串，使得拼接后的字符串的字典序最小
 * 字符串的字典序:
 * 原意是表示英文单词在字典中的先后顺序，在计算机领域中扩展成两个任意字符串的大小关系
 */
public class MinimumDictionaryOrderOfStringSplicing {
    public static class StringComparator implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {
            return (o1+o2).compareTo(o2+o1);
        }
    }
    public static void main(String[] args){
        ArrayList<String> strings=new ArrayList<>();
        strings.add("wdcsxcvs");
        strings.add("jiknxmcy");
        strings.add("bcyz");
        strings.add("uoipqqq");
        strings.add("zaqe");
        strings.sort(new StringComparator());
        StringBuilder result=new StringBuilder();
        for(String s:strings){
            result.append(s);
        }
        System.out.println("结果:"+ result);
    }
}
