import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    private static long[] seg;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            int T = Integer.parseInt(br.readLine());
            if (T == 0) break;
            long ans = solution(br, T);
            sb.append(ans).append("\n");
        }
        System.out.println(sb);

    }

    private static long solution(BufferedReader br, int N) throws IOException {
        StringTokenizer st;
        int[] ary = new int[N];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            ary[i] = Integer.parseInt(br.readLine());
            map.put(ary[i], i);
        }
        Arrays.sort(ary);
        seg = new long[4 * N];
        long answer =0;
        for(int i=0;i<N;i++){;
            int index = map.get(ary[i]);
            answer += getSum(0,N-1,1,index,N-1);
            update(0,N-1,1,index);
        }


        return answer;

    }


    private static long getSum(int s, int e, int idx, int l, int r) {
        if (r < s || e < l)
            return 0;

        if (l <= s && e <= r)
            return seg[idx];

        int mid = (s + e) >> 1;
        long left = getSum(s, mid, idx * 2, l, r);
        long right = getSum(mid + 1, e, idx * 2 + 1, l, r);
        return left + right;
    }

    private static void update(int s, int e, int idx, int c) {
        if (c < s || e < c) return;
        if (s == e) {
            seg[idx] = 1;
            return;
        }
        int mid = (s + e) >> 1;
        update(s, mid, idx * 2, c);
        update(mid + 1, e, idx * 2 + 1, c);
        seg[idx] = seg[idx * 2] + seg[idx * 2 + 1];
    }


}
