import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        
        int n = clothes.length;
        
        Map<String,List<String>> map = new HashMap<>();
        
        for(int i=0;i<n;i++){
            String c = clothes[i][0];
            String t = clothes[i][1];
            if(!map.containsKey(t))map.put(t,new ArrayList<>());
            map.get(t).add(c);
        }
        
        for(String key : map.keySet()){
            answer *= (map.get(key).size()+1);
        }
        
        return answer-1;
    }
}