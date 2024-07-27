import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
       // System.setIn(new FileInputStream("test.txt"));
        //max가 others보다 작거나 같으면 모두 다 탈 수 있다 -> 적절히 분배가 가능함
        //max가 others보다 크면 max는 다 타지 못한다 -> others사이사이 배치  + 맨 마지막에 배치

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int ary[] = new int[N];
        long sum = 0;
        long max = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            ary[i] = Integer.parseInt(st.nextToken());
            sum += ary[i];
            max = Math.max(ary[i], max);
        }
        long others = sum - max;
        if (max <= others) {
            System.out.println(sum);
        } else {
            System.out.println(others * 2 + 1);
        }


    }

}