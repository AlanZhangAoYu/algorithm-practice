package list;

import list.util.BinaryTree.BinaryTreeNode;
import list.util.BinaryTree.BinaryTreeUtils;

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
    public static BinaryTreeNode getMinData(BinaryTreeNode root){
        //获取排序二叉树最小节点
        if(root == null){
            return null;
        }
        BinaryTreeNode p=root;
        while (p.getLeftChild() != null){
            p=p.getLeftChild();
        }
        return p;
    }
    public static BinaryTreeNode getMaxData(BinaryTreeNode root){
        //获取排序二叉树最大节点
        if(root == null){
            return null;
        }
        BinaryTreeNode p=root;
        while (p.getRightChild() != null){
            p=p.getRightChild();
        }
        return p;
    }
    public static BinaryTreeNode get(BinaryTreeNode root,int data){
        //查找数据data是否存在于二叉树中，若存在，返回该节点; 若不存在，返回null
        if(root == null){
            return null;
        }
        BinaryTreeNode p=root;
        while (p != null){
            if(data < p.getData()){
                p=p.getLeftChild();
            }else if(data > p.getData()){
                p=p.getRightChild();
            }else{
                return p;
            }
        }
        return null;
    }
    public static BinaryTreeNode put(BinaryTreeNode root,int data){
        //输入数据构成排序二叉树
        if(root == null){
            root=new BinaryTreeNode(data);
            return root;
        }
        if(get(root,data) != null){
            return root;
        }
        //while循环完，parent指向要插入的位置的父节点; p指向要插入的位置(虽然为null)
        BinaryTreeNode p=root;
        BinaryTreeNode parent = null;
        while (p != null){
            parent=p;
            if(data<p.getData()){
                p = p.getLeftChild();
            }else{
                p = p.getRightChild();
            }
        }
        p=new BinaryTreeNode(data);
        if(data< parent.getData()){
            parent.setLeftChild(p);
        }else{
            parent.setRightChild(p);
        }
        return root;
    }
    public static BinaryTreeNode remove(BinaryTreeNode root,int data){
        /*
          从二叉树中删除数据data
          对于二叉排序树中的节点A，对它的删除分为三种情况:
          1、如果A只有一个子节点，就直接将A的子节点连至A的父节点上，并将A删除;
          2、如果A有两个子节点，我们就以右子树内的最小节点取代A
          3、如果A没有子节点，直接将A删除
         */
        if(root == null){
            return null;
        }
        if(get(root, data)==null){
            System.out.println("二叉树中没有该数!");
            return null;
        }
        if(data < root.getData()){
            root.setLeftChild(remove(root.getLeftChild(),data));
        }else if(data > root.getData()){
            root.setRightChild(remove(root.getRightChild(),data));
        }else{
            if(root.getLeftChild() != null && root.getRightChild() != null){
                //左右子节点均不为空
                root.setData(getMinData(root).getData());
                root.setRightChild(remove(root.getRightChild(), root.getData()));
            }else {
                if(root.getLeftChild() == null && root.getRightChild() == null){
                    //左右节点均为空
                    root=null;
                }else if(root.getLeftChild() != null){
                    //左节点不为空，右节点为空
                    root=root.getLeftChild();
                }else if(root.getRightChild() != null){
                    //右节点为空，左节点不为空
                    root=root.getRightChild();
                }
                return root;
            }
        }
        return root;
    }
    public static void main(String[] args){
        System.out.println("0--退出程序");
        System.out.println("1--插入数据");
        System.out.println("2--查询数据是否在二叉树内");
        System.out.println("3--判断二叉树是否为排序二叉树");
        System.out.println("4--删除数据");
        System.out.println("5--中序遍历排序二叉树");
        System.out.println("6--获取最小节点");
        System.out.println("7--获取最大节点");
        BinaryTreeNode root = null;
        Scanner scanner=new Scanner(System.in);
        int choice;
        while (true){
            System.out.print("请输入功能代码:");
            choice=scanner.nextInt();
            if(choice == 1){
                System.out.print("请输入要插入的数:");
                int data= scanner.nextInt();
                root=put(root,data);
            }else if(choice == 2){
                System.out.print("请输入要查询的数:");
                int data= scanner.nextInt();
                if(get(root,data)== null){
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
            }else if(choice == 4){
                System.out.print("请输入要删除的数:");
                int data= scanner.nextInt();
                remove(root,data);
            }else if(choice == 5){
                BinaryTreeUtils.recursiveMiddleOrderTraversal(root);
                System.out.println();
            }else if(choice == 6){
                System.out.println("最小值为:"+getMinData(root).getData());
            }else if(choice == 7){
                System.out.println("最大值为:"+getMaxData(root).getData());
            }else if(choice == 0){
                break;
            }else{
                System.out.println("没有此功能代码,请重新输入!");
            }
        }
    }
}
