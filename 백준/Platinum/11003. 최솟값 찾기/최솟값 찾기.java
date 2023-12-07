import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int ary[];
    static int seg[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        ary = new int[N];
        seg = new int[4 * N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }
        init(N);
        for (int i = 0; i < N; i++) {
            int l = Math.max(0, i - L + 1);
            int r = i;
            int minNum = getMin(N, l, r);
            sb.append(minNum).append(" ");
        }
        System.out.println(sb);
    }

    private static int getMin(int N, int l, int r) {
        int minNum = Integer.MAX_VALUE;
        l += N;
        r += N;
        while (l <= r) {
            if ((l & 1) == 1) {
                minNum = Math.min(minNum, seg[l]);
                l++;
            }
            if ((r & 1) == 0) {
                minNum = Math.min(minNum, seg[r]);
                r--;
            }
            l >>= 1;
            r >>= 1;
        }
        return minNum;
    }

    private static void init(int N) {
        for (int i = 0; i < N; i++) {
            seg[N + i] = ary[i];
        }
        for (int i = N - 1; i > 0; i--) {
            seg[i] = Math.min(seg[i << 1], seg[i << 1 | 1]);
        }
    }

}
