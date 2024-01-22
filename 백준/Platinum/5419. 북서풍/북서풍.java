import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class ArrayNode implements Comparable<ArrayNode> {
        int x;
        int y;

        public ArrayNode(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(ArrayNode o) {
            if (this.x == o.x) return Integer.compare(this.y, o.y);
            else return Integer.compare(o.x, this.x);
        }
    }

    static List<ArrayNode> list;


    static long seg[];

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            list = new ArrayList<>();
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                list.add(new ArrayNode(x, y));
                set.add(y);
            }
            Collections.sort(list);
            List<Integer> dup = new ArrayList<>();
            for (Integer x : set) dup.add(x);
            //y값을 정렬한다
            Collections.sort(dup);
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < dup.size(); i++) {
                map.put(dup.get(i), i);
            }
            int dsize = dup.size();
            seg = new long[4 * dsize];
            long answer = 0;
            for (int i = 0; i < list.size(); i++) {
                int y = list.get(i).y;
                int yindex = map.get(y);
                long ans = getSum(0, dsize - 1, 1, 0, yindex);
                answer += ans;
                update(0, dsize - 1, 1, yindex);
            }
            sb.append(answer).append("\n");

        }
        System.out.println(sb);
    }

    private static long getSum(int s, int e, int idx, int l, int r) {
        if (r < s || e < l) return 0;
        if (l <= s && e <= r) return seg[idx];
        int mid = (s + e) >> 1;
        return getSum(s, mid, idx * 2, l, r) + getSum(mid + 1, e, idx * 2 + 1, l, r);
    }

    private static void update(int s, int e, int idx, int c) {
        if (c < s || e < c) return;
        if (s == e) {
            seg[idx]++;
            return;
        }
        int mid = (s + e) >> 1;
        update(s, mid, idx * 2, c);
        update(mid + 1, e, idx * 2 + 1, c);
        seg[idx] = seg[idx * 2] + seg[idx * 2 + 1];
    }

}