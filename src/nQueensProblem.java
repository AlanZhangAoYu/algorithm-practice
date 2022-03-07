import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author ZAY
 * N皇后问题
 * 在 N*N 的棋盘上要摆放N个皇后，要求任意两个皇后不同行，不同列，也不在同一个斜线上
 * 给定一个整数N,返回N皇后的摆法有几种
 */
public class nQueensProblem {
    public static void initCheckerboard(int[][] checkerboard){
        //初始化棋盘，没有棋子时为0，有棋子时为1
        if(checkerboard == null){
            return;
        }
        for(int i=0;i<checkerboard.length;i++){
            for(int j=0;j<checkerboard[0].length;j++){
                checkerboard[i][j]=0;
            }
        }
    }
    public static void print(int[][] checkerboard){
        if(checkerboard == null){
            return;
        }
        for (int[] ints : checkerboard) {
            for (int j = 0; j < checkerboard[0].length; j++) {
                System.out.print(ints[j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static boolean canPut(int[][] checkerboard,int[] location,int n){
        //判断在当前传入的位置location处是否可以放棋子
        for(int i=0;i<n;i++){
            if(checkerboard[location[0]][i] == 1){
                return false;
            }
            if(checkerboard[i][location[1]] == 1){
                return false;
            }
        }
        //以下代码用来判断斜边，flag用来记录从当前标签出发向上或向下走了几步
        /*int flag=0;
        while (location[0]+flag >=0 && location[0]+flag < n && location[1]+flag >=0 && location[1]+flag < n){
            if(checkerboard[location[0]+flag][location[1]+flag] == 1){
                return false;
            }
            flag=flag-1;
        }
        flag=0;
        while (location[0]+flag >=0 && location[0]+flag < n && location[1]+flag >=0 && location[1]+flag < n){
            if(checkerboard[location[0]+flag][location[1]+flag] == 1){
                return false;
            }
            flag=flag+1;
        }*/
        return true;
    }
    public static int process(int[][] checkerboard,int n){
        //list用来记录一次成功的放入中所有棋子的位置
        if(checkerboard == null){
            return -0xffff;
        }
        LinkedList<int[]> list=new LinkedList<>();
        //flag为棋盘第一行每次在列上变动的指针
        int result=0,flag;
        int[] location={0,0};
        for(flag=0;flag< checkerboard[0].length;flag++){
            checkerboard[0][flag]=1;
            for(int i=1;i<checkerboard.length;i++){
                location[0]=i;
                for(int j=0;j<checkerboard[0].length;j++){
                    location[1]=j;
                    if(canPut(checkerboard, location, n)) {
                        checkerboard[i][j]=1;
                        list.add(location);
                        print(checkerboard);
                    }
                }
            }
            if(list.size() == n-1){
                result=result+1;
                System.out.println(list);
                list.clear();
                initCheckerboard(checkerboard);
            }
        }
        return result;
    }
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        System.out.print("请输入整数N:");
        int n= scanner.nextByte();
        int[][] checkerboard=new int[n][n];
        initCheckerboard(checkerboard);
        System.out.println(process(checkerboard,n));
    }
}
