package list;

import list.util.BinaryTreeNode;

/**
 * @author ZAY
 * 搜索二叉树(又称排序二叉树、查找二叉树):
 * 若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值；若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值；
 * 它的左、右子树也分别为搜索二叉树
 */
public class SearchBinaryTree {
    /**
     * 用来记录当前节点的父节点的值
     */
    private static int flag = Integer.MIN_VALUE;
    public static boolean isSearchBinaryTree(BinaryTreeNode root){
        /*
           判断一个二叉树是否为搜索二叉树
           简单方法为中序遍历此二叉树，并将结果放入一个列表，再检查列表是否升序排列; 若升序，则为搜索二叉树; 若不升序，则不是搜索二叉树
           以下为逐个检查的方法
        */
        if(root == null){
            return true;
        }
        boolean leftChild=isSearchBinaryTree(root.getLeftChild());
        if(!leftChild){
            return false;
        }
        if(root.getData() < flag){
            return false;
        }else {
            flag=root.getData();
        }
        return isSearchBinaryTree(root.getRightChild());
    }
    public static int get(BinaryTreeNode root,int data){
        //查找data是否存在于二叉树中，若存在，返回该数; 若不存在，返回 -0xffff
        if(root == null){
            return -0xffff;
        }
        BinaryTreeNode p=root;
        while (p != null){
            if(data < p.getData()){
                p=p.getLeftChild();
            }else if(data > p.getData()){
                p=p.getRightChild();
            }else{
                return p.getData();
            }
        }
        return -0xffff;
    }
    public static void put(BinaryTreeNode root,int data){
        //输入数据构成排序二叉树
        if(root == null){
            return;
        }
        if(get(root,data)==-0xffff){
            return;
        }
        BinaryTreeNode newNode=new BinaryTreeNode(data);
        BinaryTreeNode p=root;
        if(data<p.getData()){
            p=p.getLeftChild();
        }
    }
    public static void main(String[] args){

    }
}
