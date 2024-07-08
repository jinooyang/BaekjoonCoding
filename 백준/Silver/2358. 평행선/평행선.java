import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        Map<Long, Integer> xmap = new HashMap<>();
        Map<Long, Integer> ymap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());

            if (!xmap.containsKey(x)) xmap.put(x, 0);
            xmap.put(x, xmap.get(x) + 1);

            if (!ymap.containsKey(y)) ymap.put(y, 0);
            ymap.put(y, ymap.get(y) + 1);
        }
        int answer = 0;
        for (Long l : xmap.keySet()) {
            if (xmap.get(l) >= 2) answer++;
        }

        for(Long l : ymap.keySet()){
            if(ymap.get(l)>=2)answer++;
        }
        System.out.println(answer);
    }

}
