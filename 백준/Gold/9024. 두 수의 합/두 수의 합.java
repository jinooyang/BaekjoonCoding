import java.io.*;
import java.util.*;

public class Main {
    public static int MAX_NUM = 100_000_000;

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int[] array = new int[N];
            for (int i = 0; i < N; i++)
                array[i] = Integer.parseInt(st.nextToken());
            Arrays.sort(array);

            int s = 0;
            int e = N - 1;

            int minDiff = 2 * MAX_NUM;
            int cnt = 1;
            while (s < e) {
                int sum = array[s] + array[e];
                int diff = Math.abs(K - sum);
                if (diff == minDiff) {
                    cnt++;
                } else if (diff < minDiff) {
                    cnt = 1;
                    minDiff = diff;
                }
                if (sum == K) {
                    s++;
                    e--;
                } else if (sum > K) {
                    e--;
                } else {
                    s++;
                }
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }
}