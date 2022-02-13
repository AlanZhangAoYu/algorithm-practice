package list.util;

import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

/**
 * @author ZAY
 * 二叉树的各种工具函数
 * 注意理解二叉树的"递归序",理解了它，先序中序后序遍历都好理解; 当递归访问整个树时，每个节点实际上都访问了三次，先中后序遍历实际只是取递归序中分别在
 *     第一次、第二次、第三次访问此节点的顺序
 * 注意: 任何(注意是任何)递归函数都可以转换成等价的非递归函数
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
    public static void noRecursivePreorderTraversal(BinaryTreeNode root){
        //非递归先序遍历
        if(root == null){
            return;
        }
        Stack<BinaryTreeNode> stack=new Stack<>();
        stack.push(root);
        BinaryTreeNode p;
        while (!stack.empty()){
            p = stack.pop();
            System.out.print(p.getData()+",");
            if(p.getRightChild() != null){
                stack.push(p.getRightChild());
            }
            if(p.getLeftChild() != null){
                stack.push(p.getLeftChild());
            }
        }
    }
    public static void noRecursiveMiddleOrderTraversal(BinaryTreeNode root){
        //非递归中序遍历
        if(root == null){
            return;
        }
        Stack<BinaryTreeNode> stack=new Stack<>();
        BinaryTreeNode p=root;
        while (!stack.empty() || p != null){
            if(p != null){
                stack.push(p);
                p=p.getLeftChild();
            }else{
                p= stack.pop();
                System.out.print(p.getData()+",");
                p=p.getRightChild();
            }
        }
    }
    public static void noRecursivePostorderTraversal(BinaryTreeNode root){
        //非递归后序遍历
        if(root == null){
            return;
        }
        Stack<BinaryTreeNode> stack= new Stack<>();
        Stack<BinaryTreeNode> resultStack= new Stack<>();
        stack.push(root);
        BinaryTreeNode p;
        while(!stack.empty()){
            p=stack.pop();
            resultStack.push(p);
            if(p.getLeftChild() != null){
                stack.push(p.getLeftChild());
            }
            if(p.getRightChild() != null){
                stack.push(p.getRightChild());
            }
        }
        while (!resultStack.empty()){
            p=resultStack.pop();
            System.out.print(p.getData()+",");
        }
    }
    public static void widthTraversal(BinaryTreeNode root){
        //宽度遍历二叉树
        if(root == null){
            return;
        }
        Queue<BinaryTreeNode> queue=new LinkedList<>();
        queue.offer(root);
        BinaryTreeNode p;
        while (!queue.isEmpty()){
            p= queue.poll();
            System.out.print(p.getData()+",");
            if(p.getLeftChild() != null){
                queue.offer(p.getLeftChild());
            }
            if(p.getRightChild() != null){
                queue.offer(p.getRightChild());
            }
        }
    }
    public static int recursiveMaxDepth(BinaryTreeNode root){
        //递归求二叉树最大深度
        if(root == null){
            return 0;
        }else {
            int leftHeight = recursiveMaxDepth(root.getLeftChild());
            int rightHeight = recursiveMaxDepth(root.getRightChild());
            return java.lang.Math.max(leftHeight, rightHeight) + 1;
        }
    }
    public static int noRecursiveMaxDepth(BinaryTreeNode root){
        //非递归求二叉树最大深度
        if(root == null){
            return 0;
        }
        return 1;
    }
    public static int maxWidth(BinaryTreeNode root){
        //求二叉树最大宽度
        if(root == null){
            return 0;
        }
        Queue<BinaryTreeNode> queue=new LinkedList<>();
        BinaryTreeNode p;
        queue.offer(root);
        int maxWidth=0,nowWidth=0;
        while (!queue.isEmpty()){
            if(nowWidth == 0){
                nowWidth = queue.size();
            }
            maxWidth=Math.max(nowWidth,maxWidth);
            while (nowWidth > 0){
                p= queue.poll();
                assert p != null;
                if(p.getLeftChild() != null){
                    queue.offer(p.getLeftChild());
                }
                if(p.getRightChild() != null){
                    queue.offer(p.getRightChild());
                }
                nowWidth--;
            }
        }
        return maxWidth;
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
        System.out.println("\n==================");
        System.out.print("非递归先序遍历:");
        noRecursivePreorderTraversal(root);
        System.out.print("非递归中序遍历:");
        noRecursiveMiddleOrderTraversal(root);
        System.out.print("非递归后序遍历:");
        noRecursivePostorderTraversal(root);
        System.out.println("\n==================");
        System.out.print("宽度遍历二叉树:");
        widthTraversal(root);
        System.out.println("\n==================");
        System.out.println("最大宽度:"+maxWidth(root));
        System.out.println("递归求最大深度:"+recursiveMaxDepth(root));
    }
}
