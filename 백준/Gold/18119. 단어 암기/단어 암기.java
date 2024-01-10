import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int ary[][] = new int[N][2];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            int temp = 0;
            for (int k = 0; k < s.length(); k++) {
                char a = s.charAt(k);
                int x = a - 'a';
//                System.out.println(1<<a);
                temp = temp | (1 << x);
//                System.out.println(x + " " + Integer.toBinaryString(temp));
            }
//            System.out.println(Integer.toBinaryString(temp));
            ary[i][0] = temp;
            ary[i][1] = temp;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int flag = Integer.parseInt(st.nextToken());
            int x = st.nextToken().charAt(0) - 'a';
            if (flag == 1) {
                forget(x, ary, N);
            }
            if (flag == 2) {
                remember(x, ary, N);
            }
            int answer = 0;
            for (int k = 0; k < N; k++) {
//                System.out.println(ary[k][0] + " " + ary[k][1]);
                if (ary[k][0] == ary[k][1])
                    answer++;

            }

            sb.append(answer).append("\n");
        }
        System.out.println(sb);


    }


    private static void remember(int x, int[][] ary, int n) {
        for (int i = 0; i < n; i++) {
            if (((1 << x) & ary[i][1]) > 0)
                ary[i][0] = ary[i][0] | (1 << x);
        }
    }

    private static void forget(int x, int[][] ary, int n) {
        for (int i = 0; i < n; i++) {
            int temp = 1<<26;
            temp = temp - 1;
            temp = temp - (1<<x);
            ary[i][0] = ary[i][0] & temp;
        }
    }


}