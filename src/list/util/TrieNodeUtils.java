package list.util;

/**
 * @author ZAY
 */
public class TrieNodeUtils {
    public static void insert(TrieNode root,String str){
        if(root == null){
            return;
        }
        char[] chars=str.toCharArray();
        TrieNode p=root;
        //num记录str中下一个字符应该存在于节点组的位置
        int num;
        for (char aChar : chars) {
            num = aChar - 'a';
            if (p.getNext(num) == null) {
                p.setNext(new TrieNode(), num);
            }
            p.setPass(p.getPass() + 1);
            p = p.getNext(num);
        }
        p.setEnd(p.getEnd()+1);
    }
    public static void main(String[] args){
        String[] strings={"asd","asf","asexrc","azxqe","sxec",""};

    }
}
