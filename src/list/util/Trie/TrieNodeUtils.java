package list.util.Trie;

/**
 * @author ZAY
 * 前缀树的各种工具函数
 */
public class TrieNodeUtils {
    public static void insert(TrieNode root,String str){
        //向前缀数中插入字符串
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
    public static boolean search(TrieNode root, String str){
        //搜索字符串是否存在于前缀树中
        if(root == null){
            return false;
        }
        TrieNode p=root;
        char[] chars=str.toCharArray();
        int num;
        for(char aChar:chars){
            num=aChar - 'a';
            if(p.getNext(num) != null){
                p=p.getNext(num);
            }else{
                return false;
            }
        }
        return p.getEnd() >= 1;
    }
    public static int beginWithStr(TrieNode root,String str){
        //求前缀树中以某个字符串str开头的字符串的个数
        if(root == null){
            return 0;
        }
        char[] chars=str.toCharArray();
        TrieNode p=root;
        int num;
        for(char aChar:chars){
            num=aChar - 'a';
            if(p.getNext(num) != null){
                p=p.getNext(num);
            }else{
                return 0;
            }
        }
        return p.getPass();
    }
    public static void main(String[] args){
        String[] strings={"asd","asf","asexrc","azxqe","sxec",""};
        TrieNode root=new TrieNode();
        for(String string:strings){
            insert(root,string);
        }
        System.out.println("sxec是否存在于前缀树中:"+search(root,"sxec"));
        System.out.println("有"+beginWithStr(root,"as")+"个以as开头的字符串");
    }
}
