import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int ary[][] = new int[N][2];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            ary[i][0] = Integer.parseInt(st.nextToken());
            ary[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(ary, (o1, o2) -> Integer.compare(o1[0], o2[0]));
//        for (int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(ary[i]));
//        }
        int maxIndex = 0;
        int maxLength = 0;
        int maxHeight = 0;
        for (int i = 0; i < N; i++) {
            if (ary[i][1] >= maxHeight) {
                maxIndex = i;
                maxLength = ary[i][0];
                maxHeight = ary[i][1];
            }
        }

//        System.out.println(maxIndex + " " + maxLength + " " + maxHeight);
        int answer = 0;
        int max = 0;
        for (int i = 0; i < maxIndex; i++) {
            int width = ary[i + 1][0] - ary[i][0];
            max = Math.max(max, ary[i][1]);
//            System.out.println(width + " * " + max + " = " + width * max);
            answer += width * max;
        }
        max = 0;
        for (int i = N - 1; i > maxIndex; i--) {
            int width = ary[i][0] - ary[i - 1][0];
            max = Math.max(max, ary[i][1]);
//            System.out.println(width + " * " + max + " = " + width * max);
            answer += width * max;
        }
        System.out.println(answer += maxHeight);

    }

}