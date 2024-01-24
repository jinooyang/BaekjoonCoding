import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long[] ary;

    static class Node {
        long all;
        long left;
        long right;
        long max;

        public Node(long all, long left, long right, long max) {
            this.all = all;
            this.left = left;
            this.right = right;
            this.max = max;
        }
    }

    static Node[] seg;
    static final long MIN = -10000000000L;

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        ary = new long[N];
        seg = new Node[4 * N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }
        init(0, N - 1, 1);
        int M = Integer.parseInt(br.readLine());


        StringBuilder sb = new StringBuilder();
        for (int q = 0; q < M; q++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;
            if (y1 <= x2) {
                //구간이 겹치지 않는다
                Node left = getSum(0, N - 1, 1, x1, y1);
                Node middle = getSum(0, N - 1, 1, y1, x2);
                Node right = getSum(0, N - 1, 1, x2, y2);

                sb.append(left.right + middle.all + right.left - ary[y1] - ary[x2]).append("\n");
            } else {
                //구간이 겹친다
                Node left = getSum(0, N - 1, 1, x1, x2);
                Node middle = getSum(0, N - 1, 1, x2, y1);
                Node right = getSum(0, N - 1, 1, y1, y2);

                long ans1 = left.right + middle.all + right.left - ary[x2] - ary[y1];
                long ans2 = left.right + middle.left - ary[x2];
                long ans3 = middle.right + right.left - ary[y1];
                long ans4 = middle.max;
                //가운데 구간에서 답을 찾는 경우
//                System.out.println(q+ " ans1 : " + ans1 + ", ans2 : " + ans2 + ", ans3 : " + ans3 + ", ans4 : " + ans4);
                long ans = Math.max(Math.max(Math.max(ans1, ans2), ans3), ans4);
                sb.append(ans).append("\n");
            }
        }
        System.out.println(sb);
    }


    private static Node getSum(int s, int e, int idx, int l, int r) {
        if (r < s || e < l) return new Node(0, MIN, MIN, MIN);
        if (l <= s && e <= r)
            return seg[idx];
        int mid = (s + e) >> 1;
        Node left = getSum(s, mid, idx * 2, l, r);
        Node right = getSum(mid + 1, e, idx * 2 + 1, l, r);
        return new Node(left.all + right.all,
                Math.max(left.all + right.left, left.left),
                Math.max(right.all + left.right, right.right), Math.max(left.max, Math.max(right.max, left.right + right.left)));
    }

    private static Node init(int s, int e, int idx) {
        if (s == e)
            return seg[idx] = new Node(ary[s], ary[s], ary[s], ary[s]);
        int mid = (s + e) >> 1;
        Node left = init(s, mid, idx * 2);
        Node right = init(mid + 1, e, idx * 2 + 1);
        return seg[idx] = new Node(left.all + right.all,
                Math.max(left.all + right.left, left.left),
                Math.max(right.all + left.right, right.right), Math.max(left.max, Math.max(right.max, left.right + right.left)));
    }


}