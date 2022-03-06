import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ZAY
 * 项目费用规划
 * 输入:正数数组costs 正数数组profits 正数K 正数M
 * 含义:
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * K表示你只能串行的最多做k个项目
 * M表示你初始的资金
 * 说明:
 * 你每做完一个项目，马上获得的收益，可以支持你去做下一个项目。
 * 输出: 你最后获得的最大钱数
 */
public class ProjectCostPlanning {
    public static class HighComparator implements Comparator<Integer>{
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
    public static int process(int[] costs,int[] profits,int k,int m){
        if(costs == null || profits == null){
            return 0;
        }
        //用来存放并选出花费最少的项目
        PriorityQueue<Integer> lowQueue=new PriorityQueue<>(new LowComparator());
        //用来存放并选出利润最高的项目
        PriorityQueue<Integer> highQueue=new PriorityQueue<>(new HighComparator());
        int consts=0;
        for(int cost:costs){
            lowQueue.add(cost);
        }
        int flag= lowQueue.poll();
        while(m>=flag){
            highQueue.add(flag);
            System.out.println("加入high:"+highQueue);
            if(highQueue.size() >= 1){
                flag=highQueue.poll();
                System.out.println("取出high:"+highQueue);
                m=m+flag;
                consts++;
            }
            if(consts > k){
                break;
            }
            if(lowQueue.size() >=1){
                flag= lowQueue.poll();
            }
        }
        return m;
    }
    public static void main(String[] args){
        int[] costs={4,5,10,8,2};
        int[] profits={3,5,8,1,2};
        int K=3;
        int M=2;
        System.out.println("可获得的最大钱数:"+process(costs,profits,K,M));
    }
}
