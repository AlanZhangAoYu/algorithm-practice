import java.util.Scanner;

/**
 * @author ZAY
 * N皇后问题
 * 在 N*N 的棋盘上要摆放N个皇后，要求任意两个皇后不同行，不同列，也不在同一个斜线上
 * 给定一个整数N,返回N皇后的摆法有几种
 */
public class nQueensProblem {
    private static int result = 0;
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
            for(int j=0;j<n;j++) {
                if(checkerboard[i][location[1]]==1 || checkerboard[location[0]][j] == 1) {
                    return false;
                }
                if(Math.abs(i-location[0])==Math.abs(j-location[1])){
                    if(checkerboard[i][j]==1){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public static void process(int[][] checkerboard,int n,int row){
        if(row == n){
            result++;
            print(checkerboard);
        }else{
            for(int i=0;i<n;i++){
                int[] location={row,i};
                if(canPut(checkerboard,location,n)){
                    checkerboard[row][i] = 1;
                    process(checkerboard,n,row+1);
                    checkerboard[row][i] = 0;
                }
            }
        }
    }
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        System.out.print("请输入整数N:");
        int n= scanner.nextByte();
        int[][] checkerboard=new int[n][n];
        initCheckerboard(checkerboard);
        process(checkerboard,n,0);
        System.out.println("result:"+result);
    }
}
