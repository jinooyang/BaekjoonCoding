import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {


    static class Node {
        int a;
        int b;

        public Node(int a, int b) {

            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }
    }

    private static int ary[];
    private static Node seg[];

    private static int[] tempAry = new int[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        ary = new int[N];
        seg = new Node[4*N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }

        init(0, N - 1, 1);
//        printSeg(N);
        int Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            int flag = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (flag == 1) {
                a = a - 1;
                update(0, N - 1, 1, a, b);
//                printSeg(N);
            }
            if (flag == 2) {
                Node node = getNode(0, N - 1, 1, a-1, b-1);
                int ans = node.a + node.b;
                sb.append(ans).append("\n");
            }
        }

        System.out.println(sb);
    }

    private static void printSeg(int N) {
        for(int k = 0; k<4* N; k++){
            System.out.print(seg[k]);
        }
        System.out.println();
    }


    private static Node getNode(int s, int e, int idx, int l, int r) {
        if (r < s || e < l) return new Node(0, 0);
        if (l <= s && e <= r) return seg[idx];
        int mid = (s + e) >> 1;
        Node left = getNode(s, mid, idx * 2, l, r);
        Node right = getNode(mid + 1, e, idx * 2 + 1, l, r);
        sortAry(left, right);
        return new Node(tempAry[3], tempAry[2]);
    }

    private static Node update(int s, int e, int idx, int c, int val) {
        if (c < s || e < c) return seg[idx];
        if (s == e) {
            seg[idx].a = val;
            return seg[idx];
        }

        int mid = (s + e) >> 1;

        Node left = update(s, mid, idx * 2, c, val);
        Node right = update(mid + 1, e, idx * 2 + 1, c, val);
//        if(idx==3) System.out.println(left + " " + right);
        sortAry(left, right);
        return seg[idx] = new Node(tempAry[3], tempAry[2]);
    }

    private static Node init(int s, int e, int idx) {
        if (s == e) return seg[idx] = new Node(ary[s], 0);
        int mid = (s + e) >> 1;
        Node left = init(s, mid, idx * 2);
        Node right = init(mid + 1, e, idx * 2 + 1);

        sortAry(left, right);
        return seg[idx] = new Node(tempAry[3], tempAry[2]);

    }


    private static void sortAry(Node a, Node b) {
        tempAry[0] = a.a;
        tempAry[1] = a.b;
        tempAry[2] = b.a;
        tempAry[3] = b.b;
        Arrays.sort(tempAry);
    }


}