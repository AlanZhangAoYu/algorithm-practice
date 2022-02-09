package list.util;

/**
 * @author ZAY
 * 二叉树节点类定义
 */
public class BinaryTreeNode {
    private int data;
    private BinaryTreeNode leftChild;
    private BinaryTreeNode rightChild;
    public BinaryTreeNode(){
        this.leftChild=null;
        this.rightChild=null;
    }
    public BinaryTreeNode(int data){
        this.data=data;
        this.leftChild=null;
        this.rightChild=null;
    }
    public void setData(int data) {
        this.data = data;
    }
    public void setLeftChild(BinaryTreeNode leftChild) {
        this.leftChild = leftChild;
    }
    public void setRightChild(BinaryTreeNode rightChild) {
        this.rightChild = rightChild;
    }
    public int getData() {
        return data;
    }
    public BinaryTreeNode getLeftChild() {
        return leftChild;
    }
    public BinaryTreeNode getRightChild() {
        return rightChild;
    }
}
