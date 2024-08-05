import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
       // System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int ary[] = new int[N];
        int sortedAry[] = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            ary[i] = Integer.parseInt(st.nextToken());
            sortedAry[i] = ary[i];
        }
        Arrays.sort(sortedAry);

//        System.out.println(Arrays.toString(sortedAry));
        List<Queue<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= 1000; i++) {
            list.add(new ArrayDeque<>());
        }
        for (int i = 0; i < N; i++) {
            list.get(sortedAry[i]).add(i);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int index = list.get(ary[i]).poll();
            sb.append(index).append(" ");
        }
        System.out.println(sb);


    }

}