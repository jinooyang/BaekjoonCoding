import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long ary[][] = new long[N][2];
        long ppl = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            ary[i][0] = Integer.parseInt(st.nextToken());
            ary[i][1] = Integer.parseInt(st.nextToken());
            ppl += ary[i][1];
        }
        Arrays.sort(ary, (o1, o2) ->
                Long.compare(o1[0], o2[0])
        );
        long mid = (ppl+1) / 2;

//        System.out.println(mid);
        long answer = 0;
        long sum = 0;
        for (int i = 0; i < N; i++) {
//            System.out.println(sum);
            sum += ary[i][1];
            if (mid <= sum) {
                answer = ary[i][0];
                break;
            }
        }
        System.out.println(answer);
    }

}