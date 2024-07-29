import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
       // System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String[] ary = new String[N];
        for (int i = 0; i < N; i++) {
            ary[i] = br.readLine();
        }
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 0);
        map.put('C', 1);
        map.put('G', 2);
        map.put('T', 3);
        StringBuilder sb = new StringBuilder();
        String[] ATCG = {"A", "C", "G", "T"};
        for (int j = 0; j < M; j++) {
            int[] cnt = new int[4];
            for (int i = 0; i < N; i++) {
                cnt[map.get(ary[i].charAt(j))]++;
            }
            int maxIdx = cnt[0] >= cnt[1] ? 0 : 1;
            maxIdx = cnt[maxIdx] >= cnt[2] ? maxIdx : 2;
            maxIdx = cnt[maxIdx] >= cnt[3] ? maxIdx : 3;

            sb.append(ATCG[maxIdx]);
        }
        System.out.println(sb);
        String ans = sb.toString();
        int cnt =0;
        for (int i = 0; i < N; i++) {
            String chk = ary[i];
            for (int j = 0; j < M; j++) {
                if(ans.charAt(j)!=chk.charAt(j))cnt++;
            }
        }
        System.out.println(cnt);

    }

}