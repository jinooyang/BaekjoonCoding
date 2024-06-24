import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int left;
        int right;
        int cnt;

        public Node(int left, int right, int cnt) {
            this.left = left;
            this.right = right;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "left=" + left +
                    ", right=" + right +
                    ", cnt=" + cnt +
                    '}';
        }
    }

    static Node seg[];
    static int ary[];


    public static void main(String[] args) throws IOException {
       // System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        ary = new int[N];
        seg = new Node[4 * N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            ary[i] = Integer.parseInt(st.nextToken());

        init(0, N - 1, 1);

//        printSeg(N);

        StringBuilder sb = new StringBuilder();

        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            int flag = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            if (flag == 1) {
                update(0, N - 1, 1, a, b+1);
//                printSeg(N);
            } else {
                Node node = getSum(0, N - 1, 1, a, b);
                sb.append(node.cnt).append("\n");
            }

        }
        System.out.println(sb);

    }

//    private static void printSeg(int N) {
//        System.out.println("print Seg");
//        for (int i = 0; i < 4 * N; i++) {
//            System.out.println(seg[i]);
//        }
//    }

    static Node init(int s, int e, int idx) {
        if (s == e) return seg[idx] = new Node(ary[s], ary[s], 1);
        int mid = (s + e) >> 1;

        Node left = init(s, mid, idx * 2);
        Node right = init(mid + 1, e, idx * 2 + 1);

        Node node = new Node(left.left, right.right, left.cnt + right.cnt);

        if (left.right == right.left) node.cnt--;

        return seg[idx] = node;
    }

    static void update(int s, int e, int idx, int c, int val) {
        if (c < s || e < c) return;
        if (s == e) {
            seg[idx].left = val;
            seg[idx].right = val;
            return;
        }

        int mid = (s + e) >> 1;
        update(s, mid, idx * 2, c, val);
        update(mid + 1, e, idx * 2 + 1, c, val);

        seg[idx].left = seg[idx * 2].left;
        seg[idx].right = seg[idx * 2 + 1].right;
        seg[idx].cnt = seg[idx * 2].cnt + seg[idx * 2 + 1].cnt;
        if (seg[idx * 2].right == seg[idx * 2 + 1].left) seg[idx].cnt--;

    }

    static Node getSum(int s, int e, int idx, int l, int r) {
        if (r < s || e < l) return new Node(-1, -1, 0);
        if (l <= s && e <= r) return seg[idx];
        int mid = (s + e) >> 1;

        Node left = getSum(s, mid, idx * 2, l, r);
        Node right = getSum(mid + 1, e, idx * 2 + 1, l, r);

        Node node = new Node(left.left != -1 ? left.left : right.left, right.right != -1 ? right.right : left.right, left.cnt + right.cnt);
        if (left.right == right.left) node.cnt--;
        return node;
    }


}
