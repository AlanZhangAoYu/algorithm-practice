package list.util;

/**
 * @author ZAY
 * 二叉树的各种工具函数
 */
public class BinaryTreeUtils {
    public static void recursivePreorderTraversal(BinaryTreeNode root){
        //递归先序遍历
        if(root == null){
            return;
        }
        System.out.print(root.getData()+",");
        recursivePreorderTraversal(root.getLeftChild());
        recursivePreorderTraversal(root.getRightChild());
    }
    public static void recursiveMiddleOrderTraversal(BinaryTreeNode root){
        //递归中序遍历
        if(root == null){
            return;
        }
        recursiveMiddleOrderTraversal(root.getLeftChild());
        System.out.print(root.getData()+",");
        recursiveMiddleOrderTraversal(root.getRightChild());
    }
    public static void recursivePostorderTraversal(BinaryTreeNode root){
        //递归后序遍历
        if(root == null){
            return;
        }
        recursivePostorderTraversal(root.getLeftChild());
        recursivePostorderTraversal(root.getRightChild());
        System.out.print(root.getData()+",");
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
        System.out.print("递归先序遍历:");
        recursivePreorderTraversal(root);
        System.out.print("递归中序遍历:");
        recursiveMiddleOrderTraversal(root);
        System.out.print("递归后序遍历:");
        recursivePostorderTraversal(root);
    }
}
