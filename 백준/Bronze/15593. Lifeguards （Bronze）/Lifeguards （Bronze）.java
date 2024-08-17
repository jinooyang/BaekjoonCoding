import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int ary[][] = new int[N][2];
        int answer = 0;
        int T = 0;//시간 배열 크기 정하기 용도
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            ary[i][0] = Integer.parseInt(st.nextToken());
            ary[i][1] = Integer.parseInt(st.nextToken());
            T = Math.max(T, ary[i][1]);
        }
        //소를 한마리씩 제외 시킨다 100
        for (int i = 0; i < N; i++) {
            int time[] = new int[T + 1];
            for (int j = 0; j < N; j++) {//다 더한다 100
                if (j == i) continue;
                for (int t = ary[j][0]; t < ary[j][1]; t++) {//시간을 기록한다 1000
                    time[t] = 1;
                }
            }
            //10^7 = 10,000,000
            int temp = 0;
            for (int t = 0; t < T + 1; t++) {
                temp += time[t];
            }
            answer = Math.max(temp, answer);
        }
        System.out.println(answer);
    }

}