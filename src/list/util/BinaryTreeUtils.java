package list.util;

import java.util.ArrayList;
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
    public static boolean isCompleteBinaryTree(BinaryTreeNode root){
        /*
          判断一个二叉树是不是完全二叉树
          完全二叉树:
          一棵深度为k的有n个结点的二叉树，对树中的结点按从上至下、从左到右的顺序进行编号，如果编号为i(1≤i≤n)的结点与满二叉树中编号为i的
          结点在二叉树中的位置相同，则这棵二叉树称为完全二叉树
          遍历二叉树所有节点，只要存在一个有右孩子没有左孩子的节点，就不是完全二叉树，否则是完全二叉树
         */
        if(root == null){
            return false;
        }
        Stack<BinaryTreeNode> stack=new Stack<>();
        ArrayList<BinaryTreeNode> nodeList=new ArrayList<>();
        stack.push(root);
        BinaryTreeNode p;
        while (!stack.empty()){
            p = stack.pop();
            nodeList.add(p);
            if(p.getRightChild() != null){
                stack.push(p.getRightChild());
            }
            if(p.getLeftChild() != null){
                stack.push(p.getLeftChild());
            }
        }
        for (BinaryTreeNode node:nodeList) {
            if(node.getRightChild() != null && node.getLeftChild() == null){
                return false;
            }
        }
        return true;
    }
    public static boolean isBalancedBinaryTree(BinaryTreeNode root){
        /*
          判断一个二叉树是不是平衡二叉树
          平衡二叉树: 任一节点对应的左右子树的高度差小于等于1
          二叉树所有子树都为平衡二叉树，此二叉树为平衡二叉树
         */
        if(root == null){
            return true;
        }
        if(Math.abs(recursiveMaxDepth(root)-recursiveMaxDepth(root)) <= 1){
            return true;
        }
        return isBalancedBinaryTree(root.getLeftChild()) && isBalancedBinaryTree(root.getRightChild());
        /*
          递归次数少的版本，但难理解:
          从根节点开始，从上往下遍历，按照中序遍历的思想，从左右子节点向根节点遍历，一依次判断平衡状态，这样根结点可以重复利用已经计算的子节点的
          高度，只需要依次遍历整棵树。在遇到某个子树非平衡时，能直接结束，返回false
          public boolean isBalanced(TreeNode root) {
                if(root == null){
                    return true;
                }
                return getHeight(root) != -1;
          }
          public int getHeight(TreeNode root){
              if(root == null){
                  return 0;
              }
              int left = getHeight(root.getLeftChild());
              int right = getHeight(root.getRightChild());
              if(left == -1){
                  return -1;
              }
              if(right == -1){
                  return -1;
              }
              return Math.abs(left - right) > 1 ? -1 : Math.max(left,right)+1;
          }
         */
    }
    public static String binaryTreeSerialization(BinaryTreeNode node){
        //二叉树的序列化(转换成唯一对应的字符串) null记为# 节点值记为对应值 节点结束符为_
        StringBuilder string=new StringBuilder();
        if(node == null){
            return new String(string.append("#_"));
        }
        string.append(node.getData()).append("_");
        string.append(binaryTreeSerialization(node.getLeftChild()));
        string.append(binaryTreeSerialization(node.getRightChild()));
        return new String(string);
    }
    public static BinaryTreeNode binaryTreeDeserialization(String str){
        //二叉树的反序列化(由字符串生成对应的二叉树)
        String[] strings=str.split("_");
        Queue<String> queue=new LinkedList<>();
        for (String string : strings) {
            queue.offer(string);
        }
        return deserializationProcess(queue);
    }
    public static BinaryTreeNode deserializationProcess(Queue<String> queue){
        //反序列化的递归过程
        if(queue == null){
            return null;
        }
        String value= queue.poll();
        if("#".equals(value)){
            return null;
        }
        BinaryTreeNode node=new BinaryTreeNode(Integer.parseInt(value));
        node.setLeftChild(deserializationProcess(queue));
        node.setRightChild(deserializationProcess(queue));
        return node;
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
        System.out.println("====================");
        System.out.println("是否为完全二叉树:"+isCompleteBinaryTree(root));
        System.out.println("是否为平衡二叉树:"+isBalancedBinaryTree(root));
        System.out.println("====================");
        System.out.println("序列化:"+binaryTreeSerialization(root));
    }
}
