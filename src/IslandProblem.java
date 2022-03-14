/**
 * @author ZAY
 * 岛问题
 * 一个矩阵中只有 0和 1两种值，每个位置都可以和自己上下左右四个位置相连接，如果有一片 1连在一起，这个部分叫做岛。求一个矩阵中有多少个岛
 */
public class IslandProblem {
    public static void infect(int[][] map,int x,int y){
        //感染函数，如果遇到当前位置为1，就将这一片（这个岛）全都感染为2
        if(map == null){
            return;
        }
        if(x < 0 || x >= map.length || y >= map[0].length || y < 0 || map[x][y] != 1){
            return;
        }
        map[x][y] = 2;
        infect(map,x,y-1);
        infect(map,x,y+1);
        infect(map,x-1,y);
        infect(map,x+1,y);
    }
    public static int process(int[][] map){
        if(map == null){
            return 0;
        }
        int result = 0;
        for(int i=0;i< map.length;i++){
            for(int j=0;j< map[0].length;j++){
                if(map[i][j]==0 || map[i][j]==2){
                    continue;
                }
                result++;
                infect(map,i,j);
            }
        }
        return result;
    }
    public static void main(String[] args){
        int[][] map={
                {0,1,0,0,1,0},
                {1,1,1,0,1,0},
                {0,1,0,1,0,0},
                {0,0,0,0,0,1},
                {0,0,1,0,1,1}};
        System.out.println("共有"+process(map)+"个岛");
        nQueensProblem.print(map);
    }
}
