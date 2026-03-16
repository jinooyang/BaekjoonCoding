import java.util.*;
class Solution {
    public int solution(int[][] sizes) {
        for(int i=0;i<sizes.length;i++){
            if(sizes[i][0] > sizes[i][1]){
                swap(sizes, i);
            }
        }
        int max_x = 0;
        int max_y = 0;
            
        for(int i=0;i<sizes.length;i++){
            max_x = Math.max(max_x, sizes[i][0]);
            max_y = Math.max(max_y, sizes[i][1]);
        }
        return max_x * max_y;
       
    }
    public void swap(int[][]sizes, int index){
        int temp = sizes[index][0];
        sizes[index][0] = sizes[index][1];
        sizes[index][1] = temp;
    }
}