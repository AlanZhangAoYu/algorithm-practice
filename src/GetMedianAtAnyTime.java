import list.util.SingleListUtils;
import list.util.SingleNode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author ZAY
 * 随时获取中位数（堆的应用）
 * 在一个数据流中，随时可以取得中位数
 */
public class GetMedianAtAnyTime {
    public static class HighComparator implements Comparator<Integer> {
        //大根堆比较器定义
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2-o1;
        }
    }
    public static class LowComparator implements Comparator<Integer>{
        //小根堆比较器定义
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1-o2;
        }
    }
    public static int process(SingleNode head){
        PriorityQueue<Integer> highQueue=new PriorityQueue<>(new HighComparator());
        PriorityQueue<Integer> lowQueue=new PriorityQueue<>(new LowComparator());
        int flag;
        for(int i=1;i<=SingleListUtils.getLength(head);i++){
            flag=SingleListUtils.get(head,i);
            if(i == 1){
                highQueue.add(flag);
                continue;
            }
            if(flag <= highQueue.peek()){
                highQueue.add(flag);
            }else{
                lowQueue.add(flag);
            }
            if(Math.abs(highQueue.size()- lowQueue.size()) >= 2){
                highQueue.add(lowQueue.poll());
            }
        }
        if(head.getData() % 2 == 0){
            return (highQueue.peek()+ lowQueue.peek()) / 2;
        }else{
            return lowQueue.peek().intValue();
        }
    }
    public static void main(String[] args){
        SingleNode head=new SingleNode(0);
        Scanner scanner=new Scanner(System.in);
        int choice;
        System.out.println("0--退出程序");
        System.out.println("1--插入数据");
        System.out.println("2--获取中位数");
        System.out.println("3--打印数据");
        while (true){
            System.out.print("请输入功能代码:");
            choice= scanner.nextByte();
            if(choice==1){
                System.out.print("请输入要插入的数据:");
                int data= scanner.nextByte();
                SingleListUtils.addLast(head,data);
            }else if(choice == 2){
                System.out.println("当前数据中位数为:"+process(head));
            }else if(choice == 3){
                SingleListUtils.printList(head);
            }else if(choice == 0){
                break;
            }else{
                System.out.println("没有此功能代码,请重新输入!");
            }
        }
    }
}
