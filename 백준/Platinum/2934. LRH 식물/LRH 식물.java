import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long seg[];
    static long lazy[];
    static final int max = 100000;

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        seg = new long[4 * max];
        lazy = new long[4 * max];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            long lsum = getSum(1, max, 1, L, L);
            long rsum = getSum(1, max, 1, R, R);
            update(1, max, 1, L, L, (-1) * lsum);
            update(1, max, 1, R, R, (-1) * rsum);
            long ans = lsum+rsum;
            sb.append(ans).append("\n");
            //제거

            if (R - L > 1) update(1, max, 1, L + 1, R - 1, 1);
//            printSeg();
//            printLazy();
        }
        System.out.println(sb);

    }

    private static void printSeg() {
        for (int k = 0; k < 4 * max; k++) {
            System.out.print(seg[k] + " ");
        }
        System.out.println();
    }

    private static void printLazy() {
        for (int k = 0; k < 4 * max; k++) {
            System.out.print(lazy[k] + " ");
        }
        System.out.println();
    }


    private static void updateLazy(int s, int e, int idx) {
        if (lazy[idx] != 0) {
            seg[idx] += lazy[idx] * (e - s + 1);
            if (s != e) {
                lazy[idx * 2] += lazy[idx];
                lazy[idx * 2 + 1] += lazy[idx];
            }
            lazy[idx] = 0;
        }
    }

    private static void update(int s, int e, int idx, int l, int r, long val) {
        updateLazy(s, e, idx);
        if (r < s || e < l) return;
        if (l <= s && e <= r) {
            seg[idx] += (e - s + 1) * val;
            if (s != e) {
                lazy[idx * 2] += val;
                lazy[idx * 2 + 1] += val;
            }
            return;
        }
        int mid = (s + e) >> 1;
        update(s, mid, idx * 2, l, r, val);
        update(mid + 1, e, idx * 2 + 1, l, r, val);
        seg[idx] = seg[idx * 2] + seg[idx * 2 + 1];
    }

    private static long getSum(int s, int e, int idx, int l, int r) {
        updateLazy(s, e, idx);
        if (r < s || e < l) return 0;
        if (l <= s && e <= r) return seg[idx];
        int mid = (s + e) >> 1;
        return getSum(s, mid, idx * 2, l, r) + getSum(mid + 1, e, idx * 2 + 1, l, r);
    }


}