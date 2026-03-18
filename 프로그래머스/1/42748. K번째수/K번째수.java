import java.util.*;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for(int i=0;i<commands.length;i++){
            int st = commands[i][0];
            int ed = commands[i][1];
            int subArray[] = Arrays.copyOfRange(array, st-1, ed);
            Arrays.sort(subArray);
            answer[i] = subArray[commands[i][2]-1];
        }
        return answer;
    }
}