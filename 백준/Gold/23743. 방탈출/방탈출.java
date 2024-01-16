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
        int a;
        int b;
        int dist;

        public Node(int a, int b, int dist) {
            this.a = a;
            this.b = b;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    static int parent[];

    public static void main(String[] args) throws IOException {
       // System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }
        List<Node> graph = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.add(new Node(a, b, c));
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int t = Integer.parseInt(st.nextToken());
            graph.add(new Node(0, i, t));
        }
        Collections.sort(graph);
        long answer = 0;
        for (int i = 0; i < graph.size(); i++) {
            Node now = graph.get(i);
            int a = now.a;
            int b = now.b;
            int dist = now.dist;
            if (find(a) != find(b)) {
                union(a, b);
                answer += dist;
            }
        }
        System.out.println(answer);
    }


    private static void union(int x, int y) {
        int a = find(x);
        int b = find(y);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    private static int find(int x) {
//        System.out.println(x);
        if (parent[x] == x) return x;
        else return parent[x] = find(parent[x]);
    }


}