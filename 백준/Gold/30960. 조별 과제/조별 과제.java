import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[] ary = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            ary[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(ary);

        int diff[] = new int[N - 1];
        for (int i = 0; i < N - 1; i++)
            diff[i] = ary[i + 1] - ary[i];
        int dp[][] = new int[N - 1][2];

        dp[0][1] = diff[0];//홀수번째(짝수 인덱스)
        for (int i = 1; i < N - 1; i++) {
            if (i % 2 != 0) {//홀수인덱스 - 짝수번째
                dp[i][0] = dp[i - 1][0] + diff[i];
                dp[i][1] = dp[i - 1][1];
            } else {
                dp[i][1] = dp[i - 1][1] + diff[i];
                dp[i][0] = dp[i - 1][0];
            }


        }


//        for(int i=0;i<N-1;i++){
//            System.out.print(dp[i][0] + " ");
//        }
//        System.out.println();
//        for(int i=0;i<N-1;i++){
//            System.out.print(dp[i][1] + " ");
//        }
//
//        System.out.println();
        int minAnswer = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                continue;
            }

//            System.out.println("erase : " + ary[i]);

            int left = i >= 2 ? dp[i - 2][1] : 0;
            int right = dp[N - 2][0] - dp[i][0];
            int middle = diff[i - 1] + diff[i];
//            System.out.println(left + " " + right + " " + middle);
            int answer = left + middle + right;
            minAnswer = Math.min(answer, minAnswer);
        }
        System.out.println(minAnswer);

    }
}