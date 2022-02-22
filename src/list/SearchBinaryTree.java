package list;

import list.util.BinaryTreeNode;
import list.util.BinaryTreeUtils;

import java.util.Scanner;

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
        if(BinaryTreeUtils.recursiveMaxDepth(root) <= 1){
            root.setData(data);
            return;
        }
        if(get(root,data)==data){
            return;
        }
        //while循环完，parent指向要插入的位置的父节点; p指向要插入的位置(虽然为null)
        BinaryTreeNode p=root;
        BinaryTreeNode parent = null;
        while (p != null){
            parent=p;
            if(data<p.getData()){
                p=p.getLeftChild();
            }else if(data > p.getData()) {
                p = p.getRightChild();
            }
        }
        p=new BinaryTreeNode(data);
        if(parent.getData()<data){
            parent.setLeftChild(p);
        }else{
            parent.setRightChild(p);
        }
    }
    public static void main(String[] args){
        System.out.println("0--退出程序");
        System.out.println("1--插入数据");
        System.out.println("2--查询数据是否在二叉树内");
        System.out.println("3--判断二叉树是否为排序二叉树");
        System.out.println("4--删除数据");
        System.out.println("5--中序遍历排序二叉树");
        Scanner scanner=new Scanner(System.in);
        BinaryTreeNode root =new BinaryTreeNode(0);
        int choice=0;
        while (true){
            System.out.print("请输入功能代码:");
            choice=scanner.nextInt();
            if(choice == 1){
                System.out.print("请输入要插入的数:");
                int data= scanner.nextInt();
                put(root,data);
            }else if(choice == 2){
                System.out.print("请输入要查询的数:");
                int data= scanner.nextInt();
                if(get(root,data)== -0xffff){
                    System.out.println("二叉树中没有该数");
                }else{
                    System.out.println("二叉树中有该数");
                }
            }else if(choice == 3){
                if(isSearchBinaryTree(root)){
                    System.out.println("该二叉树是排序二叉树");
                }else{
                    System.out.println("该二叉树不是排序二叉树");
                }
            }else if(choice == 5){
                BinaryTreeUtils.recursiveMiddleOrderTraversal(root);
                System.out.println();
            }else if(choice == 0){
                break;
            }else{
                System.out.println("没有此功能代码,请重新输入!");
            }
        }
    }
}
