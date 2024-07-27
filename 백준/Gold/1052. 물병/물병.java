import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        List<Integer> list = new ArrayList<>();
        while (N > 0) {
            //N보다 작은 가장 큰 2의 제곱수를 구한다
            int x = getLargestBinaryNumber(N);
            list.add(x);
            N -= x;
        }
        int sum = 0;
        for (int i = K; i < list.size(); i++) {
            sum += list.get(i);
        }
        if (list.size() <= K) {
            System.out.println(0);
        } else
            System.out.println(list.get(K - 1) - sum);


    }

    private static int getLargestBinaryNumber(int n) {
        int result = 1;
        while ((result << 1) <= n) {
            result = result << 1;
        }
        return result;
    }

}