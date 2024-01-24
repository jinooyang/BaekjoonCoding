import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        char leftEnd;
        char rightEnd;
        int leftMax;
        int rightMax;
        int max;
        int leftLength;
        int rightLength;
        int totalLength;
//        int sum;
//        List<Integer> list = new ArrayList<>();

        public Node(char leftEnd, char rightEnd, int leftMax, int rightMax, int max,
                    int leftLength, int rightLength, int totalLength) {
            this.leftEnd = leftEnd;
            this.rightEnd = rightEnd;
            this.leftMax = leftMax;
            this.rightMax = rightMax;
            this.max = max;
            this.leftLength = leftLength;
            this.rightLength = rightLength;
            this.totalLength = totalLength;

        }

        @Override
        public String toString() {
            return "Node{" +
                    "leftEnd=" + leftEnd +
                    ", rightEnd=" + rightEnd +
                    ", leftMax=" + leftMax +
                    ", rightMax=" + rightMax +
                    ", max=" + max +
                    '}';
        }
    }

    static Node seg[];

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        seg = new Node[4 * N];
        init(1, N, 1);
//        printSeg(N);
        int Q = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int q = 0; q < Q; q++) {
            int x = Integer.parseInt(br.readLine());
            update(1, N, 1, x);
//            printSeg(N);
            sb.append(seg[1].max).append("\n");
        }
        System.out.println(sb);
    }

    private static void printSeg(int N) {
        for (int i = 1; i < 4 * N; i++) {
            if (seg[i] == null)
                break;
            System.out.print(seg[i].max + " ");
        }
        System.out.println();
    }

    //c위치를 'R'로 바꾼다
    private static void update(int s, int e, int idx, int c) {
        if (c < s || e < c)
            return;
        if (s == e) {
            if (seg[idx].leftEnd == 'L') {
                seg[idx].leftEnd = 'R';
                seg[idx].rightEnd = 'R';
                return;
            }
            seg[idx].leftEnd = 'L';
            seg[idx].rightEnd = 'L';
            return;
        }
        int mid = (s + e) >> 1;
        update(s, mid, idx * 2, c);
        update(mid + 1, e, idx * 2 + 1, c);
        Node left = seg[idx * 2];
        Node right = seg[idx * 2 + 1];

        updateSegIdx(idx, left, right);
    }

    private static void updateSegIdx(int idx, Node left, Node right) {
        seg[idx].leftEnd = left.leftEnd;
        seg[idx].rightEnd = right.rightEnd;
        seg[idx].totalLength = left.totalLength + right.totalLength;
        seg[idx].leftLength = left.leftLength;
        seg[idx].rightLength = right.rightLength;
        int tempLeftMax = 1;
        int tempRightMax = 1;
        int tempMax = 1;
        if (left != null && right != null && left.rightEnd != right.leftEnd) {
            if (left.leftLength == left.totalLength) {
                tempLeftMax = left.max + right.leftMax;
                seg[idx].leftLength = left.totalLength + right.leftLength;
            }
            if (right.rightLength == right.totalLength) {
                tempRightMax = right.max + left.rightMax;
                seg[idx].rightLength = right.totalLength + left.rightLength;
            }
            tempMax = left.rightMax + right.leftMax;
        }
        seg[idx].leftMax = Math.max(left.leftMax, tempLeftMax);
        seg[idx].rightMax = Math.max(right.rightMax, tempRightMax);
        seg[idx].max = Math.max(Math.max(left.max, right.max), tempMax);
//        if (seg[idx].max == 7)
//            System.out.println("left : "+left.toString() + left.list.toString() + "\n" +
//                    "right : "+right.toString() + right.list.toString());
    }


    private static Node init(int s, int e, int idx) {
        seg[idx] = new Node('L', 'L', 1, 1, 1,
                1, 1, 1);

        if (s == e) {
//            seg[idx].list.add(s);
            return seg[idx];
        }
        int mid = (s + e) >> 1;
        Node left = init(s, mid, idx * 2);
        Node right = init(mid + 1, e, idx * 2 + 1);
        updateSegIdx(idx, left, right);
//        for (int i = 0; i < left.list.size(); i++) {
//            seg[idx].list.add(left.list.get(i));
//
//        }
//        for (int i = 0; i < right.list.size(); i++) {
//            seg[idx].list.add(right.list.get(i));
//
//        }
        return seg[idx];
    }
}