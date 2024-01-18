import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int ary[];
    static Node seg[];

    static class Node {
        long odd;
        long even;

        public Node(long odd, long even) {
            this.odd = odd;
            this.even = even;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "odd=" + odd +
                    ", even=" + even +
                    '}';
        }
    }

    static Node zero = new Node(0, 0);

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        ary = new int[N + 1];
        seg = new Node[4 * N];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }

        init(1, N, 1);
//        printSeg(N);
        StringBuilder sb = new StringBuilder();
        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            int flag = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (flag == 1) {
                Node node = getSum(1, N, 1, a, b);
                sb.append(Math.abs(node.odd - node.even)).append("\n");
            }
            if (flag == 2) {
                update(1, N, 1, a, b);
//                printSeg(N);
            }
        }
        System.out.println(sb);
    }

    private static void printSeg(int N) {
        for(int i = 0; i<4* N; i++){
            System.out.print(seg[i] + " ");
        }
        System.out.println();
    }


    private static void update(int s, int e, int idx, int c, int val) {
        if (c < s || e < c) return;
        if (s == e) {
            if (c % 2 == 0) {
//                seg[idx].odd = 0;
                seg[idx].even += val;

            } else {
                seg[idx].odd += val;
//                seg[idx].even = 0;
            }

            return;
        }
        int mid = (s + e) >> 1;
        update(s, mid, idx * 2, c, val);
        update(mid + 1, e, idx * 2 + 1, c, val);
        seg[idx].even = seg[idx * 2].even + seg[idx * 2 + 1].even;
        seg[idx].odd = seg[idx * 2].odd + seg[idx * 2 + 1].odd;
    }

    private static Node getSum(int s, int e, int idx, int l, int r) {
        if (r < s || e < l) return zero;
        if (l <= s && e <= r) return seg[idx];
        int mid = (s + e) >> 1;
        Node left = getSum(s, mid, idx * 2, l, r);
        Node right = getSum(mid + 1, e, idx * 2 + 1, l, r);
        return new Node(left.odd + right.odd, left.even + right.even);
    }

    private static Node init(int s, int e, int idx) {
        if (s == e) {
            Node node = new Node(0, 0);
            if (s % 2 == 0) {
                node.even = ary[s];
            } else {
                node.odd = ary[s];
            }
            return seg[idx] = node;
        }

        int mid = (s + e) >> 1;

        Node left = init(s, mid, idx * 2);
        Node right = init(mid + 1, e, idx * 2 + 1);
        Node node = new Node(left.odd + right.odd, left.even + right.even);
        return seg[idx] = node;
    }
}
