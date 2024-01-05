import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int seg[];
    static int mapping[];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        seg = new int[800000];//최대 크기로 열어버린다
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            Arrays.fill(seg, 0);//세그 트리 초기화한다
            solution(br, sb);
            sb.append("\n");
        }

        System.out.println(sb);

    }


    private static void solution(BufferedReader br, StringBuilder sb) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int segsize = 4*(m+n);
        mapping = new int[n + 1];
        //초기 위치를 ary에 저장한다
        //초기 위치란 내 위에 몇개의 영화가 있는지 카운트
        for (int i = 1; i <= n; i++) {
            mapping[i] = n - i;
        }
       // System.out.println("mapping : " + Arrays.toString(mapping));
        //구간합 세그트리를 열었다
        init(0, n + m - 1, 1, n);
       // System.out.println("seg");
       // printseg(m, n);
        st = new StringTokenizer(br.readLine());

        int nextIdx = n;
        for (int i = 0; i < m; i++) {
            int find = Integer.parseInt(st.nextToken());
            int location = mapping[find];//위치를 알 수 있다
            //내 오른족에 몇개 있는지 카운트 하면 된다
            int ans = getSum(0, m + n - 1, 1, location + 1, m + n - 1);
            sb.append(ans).append(" ");
            update(0, m + n - 1, 1, location, 0);
           // printseg(m, n);
            mapping[find] = nextIdx;
            update(0, m + n - 1, 1, nextIdx++, 1);
           // printseg(m, n);
        }
    }

    private static void printseg(int m, int n) {
        for(int k = 0; k< 4*(m + n); k++){
            System.out.print(seg[k] + " ");
        }
        System.out.println();
    }

    private static int getSum(int s, int e, int idx, int l, int r) {
        if (r < s || e < l) return 0;
        if (l <= s && e <= r) return seg[idx];
        int mid = (s + e) >> 1;
        return getSum(s, mid, idx * 2, l, r) + getSum(mid + 1, e, idx * 2 + 1, l, r);
    }

    private static int init(int s, int e, int idx, int n) {
        if (s == e) {

            if(s<n) {
               // System.out.println("여기"+idx + " " + s);
                return seg[idx] = 1;
            }
            else return seg[idx];
        }
        int mid = (s + e) >> 1;

        return seg[idx] = init(s, mid, idx * 2,n)
                + init(mid + 1, e, idx * 2 + 1,n);
    }

    private static void update(int s, int e, int idx, int c, int val) {
        if (c < s || e < c) return;
        if (s == e) {
            seg[idx] = val;
            return;
        }
        int mid = (s + e) >> 1;
        update(s, mid, idx * 2, c, val);
        update(mid + 1, e, idx * 2 + 1, c, val);
        seg[idx] = seg[idx * 2] + seg[idx * 2 + 1];
    }


}