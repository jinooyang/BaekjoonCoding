import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
       // System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean[] row = new boolean[N];
        boolean[] col = new boolean[M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char x = s.charAt(j);
                if (x == 'X') {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }
        int rowCnt = 0;
        int colCnt = 0;
        for (int i = 0; i < N; i++) {
            if (!row[i]) rowCnt++;
        }
        for (int i = 0; i < M; i++) {
            if (!col[i]) colCnt++;
        }

        System.out.println(Math.max(rowCnt, colCnt));
    }

}
