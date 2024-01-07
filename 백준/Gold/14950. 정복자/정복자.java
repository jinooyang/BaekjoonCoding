import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Node implements Comparable<Node> {
        int u;
        int v;
        int t;

        public Node(int u, int v, int t) {
            this.u = u;
            this.v = v;
            this.t = t;
        }


        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.t, o.t);
        }
    }

    static int[] parent;

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }
        List<Node> graph = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.add(new Node(u, v, w));
        }

        Collections.sort(graph);
        int add = 0;
        int answer = 0;
        for (int i = 0; i < M; i++) {
            Node now = graph.get(i);
            if (find(now.u) != find(now.v)) {
                union(now.u, now.v);
                answer += now.t;
                answer+=add;
                add += T;
            }
        }
        System.out.println(answer);
    }


    private static void union(int x, int y) {
        int a = find(x);
        int b = find(y);
        if (a > b) parent[a] = b;
        else parent[b] = a;
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        else return parent[x] = find(parent[x]);
    }


}