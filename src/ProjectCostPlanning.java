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
    public static void process(int[] costs,int[] profits,int k,int m){
        if(costs == null || profits == null){
            return;
        }
        //用来存放并选出花费最少的项目
        PriorityQueue<Integer> lowQueue=new PriorityQueue<>();
        //用来存放并选出利润最高的项目
        PriorityQueue<Integer> highQueue=new PriorityQueue<>();
        for(int cost:costs){
            lowQueue.add(cost);
        }
        int flag;
        for(int i=0;i< lowQueue.size();i++){
            flag=lowQueue.poll();
            if(m>=flag){
            }
        }
    }
    public static void main(String[] args){

    }
}
