package list;

import list.util.BinaryTreeNode;
import list.util.BinaryTreeUtils;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author ZAY
 * 折纸问题
 * 请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。此时 折痕是凹下去的，即折痕突起的方向指向纸条的背面。
 * 如果从纸条的下边向上方连续对折2 次，压出折痕后展开，此时有三条折痕，从上到下依次是凹折痕、凹折痕和凸折痕。
 * 给定一 个输入参数N，代表纸条都从下边向上方连续对折N次，请从上到下打印所有折痕的方向 例如：N=1时，打印： down；N=2时，打印： down down up
 */
public class OrigamiProblem {
    public static BinaryTreeNode createTree(int depth){
        //输入树的深度depth(折纸次数)，建立折痕二叉树
        if(depth < 1){
            return null;
        }
        BinaryTreeNode root=new BinaryTreeNode(1);
        BinaryTreeNode p;
        if(depth == 1){
            return root;
        }
        //遍历整个二叉树，只要是叶子节点，就在叶子节点下做 process 操作
        Queue<BinaryTreeNode> queue=new LinkedList<>();
        queue.offer(root);
        for(int i=1;i<depth;i++){
            while (!queue.isEmpty()){
                p= queue.poll();
                if(p.getLeftChild() != null){
                    queue.offer(p.getLeftChild());
                }
                if(p.getRightChild() != null){
                    queue.offer(p.getRightChild());
                }
                if(p.getLeftChild() == null && p.getRightChild() == null){
                    p.setLeftChild(new BinaryTreeNode(1));
                    p.setRightChild(new BinaryTreeNode(2));
                }
            }
        }
        return root;
    }
    public static BinaryTreeNode process(BinaryTreeNode node){
        //输入根节点，在此根节点建立左孩子和右孩子，1代表凹折痕，2代表凸折痕
        if(node == null){
            return null;
        }
        node.setLeftChild(new BinaryTreeNode(1));
        node.setRightChild(new BinaryTreeNode(2));
        return node;
    }
    /**
    当前你来了一个节点，脑海中想象的！
    这个节点在第i层，一共有N层，N固定不变的
    这个节点如果是凹的话，down = T
    这个节点如果是凸的话，down = F
     */
    public static void process(int i, int N, boolean down) {
        // 函数的功能：中序打印以你想象的节点为头的整棵树！
        if (i > N) {
            return;
        }
        process(i + 1, N, true);
        System.out.print(down ? "凹 " : "凸 ");
        process(i + 1, N, false);
    }
    public static void printAllFolds(int depth) {
        process(1, depth, true);
        System.out.println();
    }
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        System.out.print("请输入折纸次数:");
        int data= scanner.nextInt();
        printAllFolds(data);
        BinaryTreeNode root=createTree(data);
        BinaryTreeUtils.recursiveMiddleOrderTraversal(root);
    }
}
