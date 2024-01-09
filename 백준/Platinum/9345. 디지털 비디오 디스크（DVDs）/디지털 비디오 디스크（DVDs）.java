import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {


    static class Node {
        int min;
        int max;

        public Node(int min, int max) {

            this.min = min;
            this.max = max;
        }
    }

    static Node seg[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            seg = new Node[4 * N];
            init(0, N - 1, 1);
            int ary[] = new int[N];
            for (int i = 0; i < N; i++) {
                ary[i] = i;
            }
            for (int q = 0; q < K; q++) {
                st = new StringTokenizer(br.readLine());
                int Q = Integer.parseInt(st.nextToken());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                if (Q == 0) {
                    //swap
                    update(0, N - 1, 1, A, ary[B]);
                    update(0, N - 1, 1, B, ary[A]);
                    int temp = ary[A];
                    ary[A] = ary[B];
                    ary[B] = temp;
                }
                if (Q == 1) {
                    //get min and max
                    Node minMax = getMinMax(0,N-1,1,A,B);
                    if(minMax.min == A && minMax.max == B)sb.append("YES\n");
                    else sb.append("NO\n");
                }
            }
        }
        System.out.println(sb);
    }


    private static Node getMinMax(int s, int e, int idx, int l, int r) {
        if (r < s || e < l) return new Node(Integer.MAX_VALUE, 0);
        if(l<=s && e<=r)return seg[idx];
        int mid = (s+e)>>1;
        Node left = getMinMax(s, mid,idx*2,l,r);
        Node right = getMinMax(mid+1,e,idx*2+1,l,r);
        return new Node(Math.min(left.min,right.min), Math.max(left.max,right.max));
    }

    private static void update(int s, int e, int idx, int c, int val) {
        if (c < s || e < c) {
            return;
        }

        if (s == e) {
            seg[idx].min = val;
            seg[idx].max = val;
            return;
        }

        int mid = (s + e) >> 1;
        update(s, mid, idx * 2, c, val);
        update(mid + 1, e, idx * 2 + 1, c, val);

        seg[idx].min = Math.min(seg[idx * 2].min, seg[idx * 2 + 1].min);
        seg[idx].max = Math.max(seg[idx * 2].max, seg[idx * 2 + 1].max);
    }


    private static Node init(int s, int e, int idx) {
        if (s == e) {
            seg[idx] = new Node(s, s);
            return seg[idx];
        }
        int mid = (s + e) >> 1;
        Node left = init(s, mid, idx * 2);
        Node right = init(mid + 1, e, idx * 2 + 1);

        seg[idx] = new Node(Math.min(left.min, right.min), Math.max(left.max, right.max));
        return seg[idx];


    }


}