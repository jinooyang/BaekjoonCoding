import java.io.*;
import java.util.*;

public class Main {
    public static final long MOD = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("test.txt"));
//        System.out.println(Long.MAX_VALUE);
//        System.out.println((long)Math.pow(2,64));
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
//        System.out.println("allTake : " + allTake);

        for (int i = 0; i < N; i++) {
            long take = Math.min(canHave[i], allTake / (N - i));

            canHave[i] -= take;
            allTake -= take;
        }

//        System.out.println(Arrays.toString(canHave));
        long answer = 0;
        for (int i = 0; i < N; i++) {
            long anger = want[i] - canHave[i];
//            System.out.println("take : " + anger);
            answer += anger*anger%MOD;
        }
        System.out.println(answer);

    }

}