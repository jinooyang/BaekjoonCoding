import java.io.*;
import java.util.*;

public class Main {
    public static final long MOD = Long.MAX_VALUE;
    
    public static void main(String[] args) throws IOException {
        
        /*
        * 친구들이 못받을 사탕의 수를 배분한다
        * 정렬하고
        * 앞에서부터 엔빵한 만큼 제거한다
        * 1번 -> N빵한만큼 제거
        * 2번 -> 1번을 제외하고 나머지끼리 N-1빵한만큼 제거
        * 
        * */
        
        
       // System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        long canHave[] = new long[N];
        long want[] = new long[N];
        long totalWant = 0;
        for (int i = 0; i < N; i++) {
            want[i] = Integer.parseInt(br.readLine());
            canHave[i] = want[i];
            totalWant += canHave[i];
        }
        Arrays.sort(canHave);
        Arrays.sort(want);

        long allTake = totalWant - M;

        for (int i = 0; i < N; i++) {
            long take = Math.min(canHave[i], allTake / (N - i));
            canHave[i] -= take;
            allTake -= take;
        }

        long answer = 0;
        for (int i = 0; i < N; i++) {
            long anger = want[i] - canHave[i];
            answer += anger * anger % MOD;
        }
        System.out.println(answer);

    }

}