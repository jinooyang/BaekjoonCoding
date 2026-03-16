class Solution {
    int d_length = 0;
    int max_answer = 0;
    int kk = 0;;
    int [][]duns = null;
    public int solution(int k, int[][] dungeons) {
        int[] order = new int[8];
        int used = 0;
        
        d_length = dungeons.length;
        kk = k;
        duns = dungeons;
        findOrder(order,-1, used);
        return max_answer;
    }
    public void findOrder(int[] order, int beforeIdx, int used){
        //System.out.println(beforeIdx);
        if(beforeIdx ==  d_length - 1){
            //정답 계산
            int find_k = kk;
            int cnt = 0;
            for(int i=0;i<d_length;i++){
                int tryd = order[i];
                if(find_k >= duns[tryd][0]){
                    find_k -= duns[tryd][1];
                    cnt++;
                }
            }
            max_answer = Math.max(max_answer, cnt);
            //System.out.println("test");
            return;
        }
        for(int i=0;i<d_length;i++){
            
            if(((1<<i) & used) == 0 ){
                //System.out.println(used);
                order[beforeIdx+1] = i;
                findOrder(order, beforeIdx+1, used | (1<<i));
            }
        }
    }
}