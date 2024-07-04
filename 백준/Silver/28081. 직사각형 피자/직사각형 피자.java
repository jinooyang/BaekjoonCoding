import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        long K = Long.parseLong(st.nextToken());

        int N = Integer.parseInt(br.readLine());
        long yAry[] = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            yAry[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        long xAry[] = new long[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            xAry[i] = Integer.parseInt(st.nextToken());
        }


        long height[] = new long[N + 1];
        getDiff(N, height, yAry, H);



        long width[] = new long[M + 1];
        getDiff(M, width, xAry, W);



        Arrays.sort(height);
        Arrays.sort(width);

//        System.out.println(Arrays.toString(height));
//        System.out.println(Arrays.toString(width));

        long answer = 0;
        for (int i = 0; i < width.length; i++) {
            long w = width[i];
            int s = 0;
            int e = height.length - 1;
            int index = -1;

            while (s <= e) {
                int mid = (s + e) >> 1;

                long area = w * height[mid];
                if (area <= K) {
                    index = Math.max(index, mid);
                    s = mid + 1;
                    continue;
                }
                if (area > K) {
                    e = mid - 1;
                }
            }
            answer += index + 1;

        }

        System.out.println(answer);
    }

    private static void getDiff(int N, long[] height, long[] ary, long X) {
        for (int i = 0; i <= N; i++) {
            if (i > 0 && i < N)
                height[i] = ary[i] - ary[i - 1];
            else if (i == N)
                height[i] = X - ary[i - 1];
            else height[i] = ary[i];
        }
    }

}
