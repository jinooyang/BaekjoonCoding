import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int ary[] = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }


        Queue<Integer> q = new ArrayDeque<>();
        Queue<Integer> before = new ArrayDeque<>();
        q.add(1);
        before.add(ary[1]);

        boolean visited[] = new boolean[n + 1];
        visited[1] = true;
        int answer = 0;
        if (ary[1] != 0) answer++;
        while (!q.isEmpty()) {
            int now = q.poll();
            int colorBefore = before.poll();
            if (colorBefore != ary[now]) answer++;
            for (Integer next : graph.get(now)) {
                if (!visited[next]) {
                    q.add(next);
                    before.add(ary[now]);
                    visited[next] = true;

                }
            }
        }
        System.out.println(answer);


    }


}