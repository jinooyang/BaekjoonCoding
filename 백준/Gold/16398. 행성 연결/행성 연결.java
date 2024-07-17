import java.io.*;
import java.util.*;

public class Main {
    static int parent[];

    static class Node implements Comparable<Node> {
        int u;
        int v;
        int dist;

        public Node(int u, int v, int dist) {
            this.u = u;
            this.v = v;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        List<Node> list = new ArrayList<>();

        parent = new int[N + 1];

        for (int i = 0; i < N + 1; i++)
            parent[i] = i;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int cost = Integer.parseInt(st.nextToken());
                if (i < j)
                    list.add(new Node(i, j, cost));

            }
        }

        Collections.sort(list);
        long answer = 0;
        for (int i = 0; i < list.size(); i++) {
            Node now = list.get(i);
            if (find(now.u) != find(now.v)) {
                union(now.u, now.v);
                answer += now.dist;
            }
        }
        System.out.println(answer);


    }

    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int a, int b) {
        int x = find(a);
        int y = find(b);
        if (x < y) {
            parent[y] = x;
        } else parent[x] = y;
    }

}