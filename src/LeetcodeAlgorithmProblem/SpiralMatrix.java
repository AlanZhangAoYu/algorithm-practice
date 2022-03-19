package LeetcodeAlgorithmProblem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZAY
 * 54.螺旋矩阵
 * 给你一个 m 行 n 列的矩阵matrix, 请按照顺时针螺旋顺序返回矩阵中的所有元素
 */
public class SpiralMatrix {
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result =new ArrayList<>();
        int[] point=new int[]{0,0};
        //direction为当前指针前进的方向,若碰到右边界，方向朝下；若碰到下边界，方向朝左；若碰到左边界，方向朝上；若碰到上边界，方向朝右
        //朝右 0 朝下 1 朝左 2 朝上 3
        int direction=0;
        //边界限定值 每次指针遇到边界，边界就-1
        int up=0,down=matrix.length-1,left=0,right= matrix[0].length-1;
        if(down == 0 && right == 0){
            result.add(matrix[up][left]);
            return result;
        }
        for(int i=0;i< matrix.length * matrix[0].length;i++){
            if(direction == 0){
                if(point[1] >= right){
                    direction = 1;
                    up += 1;
                }else {
                    result.add(matrix[point[0]][point[1]]);
                    point[1] += 1;
                }
            }
            if(direction == 1){
                if(point[0] >= down){
                    direction = 2;
                    right -= 1;
                }else {
                    result.add(matrix[point[0]][point[1]]);
                    point[0] += 1;
                }
            }
            if(direction == 2){
                if(point[1] <= left){
                    direction = 3;
                    down -=1 ;
                }else {
                    result.add(matrix[point[0]][point[1]]);
                    point[1] -= 1;
                }
            }
            if(direction == 3){
                if(point[0] <= up ){
                    direction = 0;
                    left += 1;
                }else {
                    result.add(matrix[point[0]][point[1]]);
                    point[0] -= 1;
                }
            }
            System.out.println(result);
        }
        if(result.size() == matrix[0].length && matrix.length == 1){
            //矩阵为一条横线，一次都没有拐弯过
            return result;
        }
        if(direction == 3){
            result.add(matrix[up][left]);
        }else if(direction == 0){
            result.add(matrix[up][right]);
        }else if(direction == 1){
            result.add(matrix[down][right]);
        }else{
            result.add(matrix[down][left]);
        }
        return result;
    }
    public static void main(String[] args) {
        int[][] list={{3},{2}};
        ArrayList<Integer> result = (ArrayList<Integer>) spiralOrder(list);
        System.out.println(result);
    }
}
