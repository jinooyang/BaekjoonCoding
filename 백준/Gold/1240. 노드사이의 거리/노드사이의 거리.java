import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int to;
        int dist;

        public Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, d));
            graph.get(v).add(new Node(u, d));

        }

        StringBuilder sb = new StringBuilder();
        boolean visited[] = new boolean[N + 1];
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int answer = 0;
            Arrays.fill(visited, false);
            Queue<Node> q = new ArrayDeque<>();
            q.add(new Node(u, 0));
            visited[u] = true;
            while (!q.isEmpty()) {
                Node node = q.poll();
                int now = node.to;

                int dist = node.dist;
                if (now == v) {
                    answer = dist;
                    break;
                }
                for (Node next : graph.get(now)) {
                    if (!visited[next.to]) {
                        q.add(new Node(next.to, dist + next.dist));
                        visited[next.to] = true;
                    }
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }

}
