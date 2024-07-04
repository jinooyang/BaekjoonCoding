import java.io.FileInputStream;
import java.util.*;
import java.io.*;
public class Main {

    static long w, h, k;

    static boolean check(long height, long wide) {
        return height * wide <= k;
    }

    public static void main(String[] args) throws IOException {
       // System.setIn(new FileInputStream("test.txt"));
        Scanner scanner = new Scanner(System.in);

        w = scanner.nextLong();
        h = scanner.nextLong();
        k = scanner.nextLong();

        int n = scanner.nextInt();  // 가로 방향 커팅 개수
        long[] height = new long[n];
        List<Long> block_h = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            height[i] = scanner.nextLong();
            if (i != 0) {
                block_h.add(height[i] - height[i - 1]);
            } else {
                block_h.add(height[i]);
            }
        }
        block_h.add(h - height[n - 1]);

        int m = scanner.nextInt();  // 세로 방향 커팅 개수
        long[] wide = new long[m];
        List<Long> block_w = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            wide[i] = scanner.nextLong();
            if (i != 0) {
                block_w.add(wide[i] - wide[i - 1]);
            } else {
                block_w.add(wide[i]);
            }
        }
        block_w.add(w - wide[m - 1]);

        long ret = 0;
        Collections.sort(block_w);

        for (long bh : block_h) { // height
            int left = 0;  // block_w의 인덱스를 저장
            int right = block_w.size() - 1;

            while (left <= right) { // wide
                int mid = (left + right) / 2;
                if (check(bh, block_w.get(mid))) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            ret += left;
        }
        System.out.println(ret);
    }
}
