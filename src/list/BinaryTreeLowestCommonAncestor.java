package list;

import list.util.BinaryTree.BinaryTreeNode;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author ZAY
 * 求二叉树两个节点的最低公共祖先（两个节点向上第一次相交的那个节点）
 */
public class BinaryTreeLowestCommonAncestor {
    public static BinaryTreeNode lowestCommonAncestor(BinaryTreeNode root,BinaryTreeNode node1,BinaryTreeNode node2){
        if(root == null){
            return null;
        }
        //这个Map表用来存二叉树中每一个节点与其对应的父节点的关系, 根节点的父节点为它自己
        HashMap<BinaryTreeNode,BinaryTreeNode> fatherMap=new HashMap<>(50);
        fatherMap.put(root,root);
        //这个Set用来存从node1回到根节点经过的所有节点(查找一个节点的父节点要用到上面的Map表)
        HashSet<BinaryTreeNode> nodeHashSet=new HashSet<>();
        process(root,fatherMap);
        BinaryTreeNode flag=node1;
        //while运行完后, flag会指向根节点（只有根节点的父节点为它自己）
        while(flag != fatherMap.get(flag)){
            nodeHashSet.add(flag);
            flag = fatherMap.get(flag);
        }
        flag = node2;
        while (flag != fatherMap.get(flag)){
            if(nodeHashSet.contains(flag)){
                return flag;
            }
            flag = fatherMap.get(flag);
        }
        return null;
    }
    public static void process(BinaryTreeNode node,HashMap<BinaryTreeNode,BinaryTreeNode> fatherMap){
        if(node == null){
            return;
        }
        fatherMap.put(node.getLeftChild(),node);
        fatherMap.put(node.getRightChild(),node);
        process(node.getLeftChild(), fatherMap);
        process(node.getRightChild(), fatherMap);
    }
    public static BinaryTreeNode lowestCommonAncestorFuck(BinaryTreeNode root,BinaryTreeNode node1,BinaryTreeNode node2){
        //比上面骚气的方法，代码量小，但不好理解
        if(root == null || root == node1 || root == node2){
            return root;
        }
        BinaryTreeNode left=lowestCommonAncestorFuck(root.getLeftChild(), node1, node2);
        BinaryTreeNode right=lowestCommonAncestorFuck(root.getRightChild(), node1, node2);
        if(left != null && right != null){
            return root;
        }
        return left != null ? left : right;
    }
    public static void main(String[] args){
        /*
          构造一个二叉树:
                 1
             2        3
           4   5    6   7
             8        9
              10
         */
        BinaryTreeNode root=new BinaryTreeNode(1);
        BinaryTreeNode node1=new BinaryTreeNode(2);
        BinaryTreeNode node2=new BinaryTreeNode(3);
        BinaryTreeNode node3=new BinaryTreeNode(4);
        BinaryTreeNode node4=new BinaryTreeNode(5);
        BinaryTreeNode node5=new BinaryTreeNode(6);
        BinaryTreeNode node6=new BinaryTreeNode(7);
        BinaryTreeNode node7=new BinaryTreeNode(8);
        BinaryTreeNode node8=new BinaryTreeNode(9);
        BinaryTreeNode node9=new BinaryTreeNode(10);
        root.setLeftChild(node1);
        root.setRightChild(node2);
        node1.setLeftChild(node3);
        node1.setRightChild(node4);
        node2.setLeftChild(node5);
        node2.setRightChild(node6);
        node4.setLeftChild(node7);
        node5.setRightChild(node8);
        node7.setRightChild(node9);
        System.out.println("10和4的最低公共祖先为:"+lowestCommonAncestorFuck(root,node9,node8).getData());
        System.out.println("10和9的最低公共祖先为:"+lowestCommonAncestor(root,node9,node3).getData());
    }
}
