import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
       // System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int ary[][] = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                ary[i][j] = s.charAt(j) - '0';
            }
        }
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int del = 1;
                while (true) {
                    if (i + del >= N || j + del >= M) break;
                    int a = ary[i][j];
                    int b = ary[i + del][j];
                    int c = ary[i][j + del];
                    int d = ary[i + del][j + del];
                    if (a == b && b == c && c == d) {
                        answer = Math.max(del, answer);
                    }
                    del++;
                }
            }
        }
        answer++;
        System.out.println(answer*answer);


    }

}
