import java.io.*;
import java.util.*;

public class Main {

    static int parent[];

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int cost[][] = new int[N + 1][N + 1];
        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) parent[i] = i;
        
        for (int i = 1; i <= N; i++) {
            cost[0][i] = Integer.parseInt(br.readLine());
            cost[i][0] = cost[0][i];
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) cost[i][j] = Integer.parseInt(st.nextToken());
        }

        int K = N + 1;
        int V = (K * K - K) / 2;
        int graph[][] = new int[V][3];
        int idx = 0;


        for (int i = 1; i <= N; i++) {
            for (int j = N; j > i; j--) {
                if (cost[i][j] != 0) {
                    graph[idx][0] = i;
                    graph[idx][1] = j;
                    graph[idx][2] = cost[i][j];
                    idx++;
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            graph[idx][0] = 0;
            graph[idx][1] = i;
            graph[idx][2] = cost[0][i];

            idx++;
        }


        sortGraph(graph);


        int answer = 0;
        for (int i = 0; i < V; i++) {
            int a = graph[i][0];
            int b = graph[i][1];
            int dist = graph[i][2];

            if (find(a) != find(b)) {
                union(a, b);
                answer += dist;
            }

        }
        System.out.println(answer);
    }

    private static void sortGraph(int[][] graph) {
        Arrays.sort(graph, (o1, o2) -> {
                return Integer.compare(o1[2], o2[2]);
        });
    }

    private static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    private static void union(int a, int b) {
        int x = find(a);
        int y = find(b);
        if (x < y) parent[y] = x;
        else parent[x] = y;
    }


}
