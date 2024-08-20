import java.io.*;
import java.util.*;


public class Main {


    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int bottom[] = new int[N / 2];
        int top[] = new int[N / 2];

        for (int i = 0; i < N / 2; i++) {
            bottom[i] = Integer.parseInt(br.readLine());
            top[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(bottom);
        Arrays.sort(top);


        int minAnswer = Integer.MAX_VALUE;
        int cnt = 0;
        for (int i = 1; i <= H; i++) {
            int b = Arrays.binarySearch(bottom, i);
            while( b>0 && bottom[b-1] == bottom[b]) b--;
//            System.out.println(b);
            if (b < 0) b = -1 * b - 1;
            b = N / 2 - b;
//            System.out.println("hieght : " + i + " bottom not pass = " + b);
//            System.out.println(b);

            int t = Arrays.binarySearch(top, H - i + 1);
            while( t>0 && top[t-1] == top[t]) t--;

            if (t < 0) t = -1 * t - 1;
            t = N / 2 - t;
//            System.out.println("hieght : " + i + " top not pass    = " + t);

            int answer = b + t;
//            System.out.println("answer : " + answer);

            if (answer < minAnswer) {
                minAnswer = answer;
                cnt = 0;
            }
            if (answer == minAnswer) {
                cnt++;
            }

        }
        System.out.println(minAnswer + " " + cnt);

    }

}
