import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int pos;
        int num;

        public Node(int pos, int num) {
            this.pos = pos;
            this.num = num;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "pos=" + pos +
                    ", num=" + num +
                    '}';
        }

        @Override
        public int compareTo(Node o) {

            return Integer.compare(this.pos, o.pos);
        }
    }

    static class PQNode implements Comparable<PQNode> {
        int diff;
        int num1;
        int num2;

        public PQNode(int diff, int num1, int num2) {

            this.diff = diff;
            this.num1 = num1;
            this.num2 = num2;
        }


        @Override
        public int compareTo(PQNode o) {
            return Integer.compare(this.diff, o.diff);
        }

        @Override
        public String toString() {
            return "PQNode{" +
                    "diff=" + diff +
                    ", num1=" + num1 +
                    ", num2=" + num2 +
                    '}';
        }
    }

    static int parent[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
        List<Node> graphX = new ArrayList<>();
        List<Node> graphY = new ArrayList<>();
        List<Node> graphZ = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            graphX.add(new Node(x, i));
            graphY.add(new Node(y, i));
            graphZ.add(new Node(z, i));
        }

        Collections.sort(graphX);
//        System.out.println(graphX);
        Collections.sort(graphY);
        Collections.sort(graphZ);
        PriorityQueue<PQNode> pq = new PriorityQueue<>();
        for (int i = 0; i < N - 1; i++) {
            int diffX = Math.abs(graphX.get(i).pos - graphX.get(i + 1).pos);
            int diffY = Math.abs(graphY.get(i).pos - graphY.get(i + 1).pos);
            int diffZ = Math.abs(graphZ.get(i).pos - graphZ.get(i + 1).pos);

            pq.add(new PQNode(diffX, graphX.get(i).num, graphX.get(i + 1).num));
            pq.add(new PQNode(diffY, graphY.get(i).num, graphY.get(i + 1).num));
            pq.add(new PQNode(diffZ, graphZ.get(i).num, graphZ.get(i + 1).num));
        }
//        System.out.println(pq.toString());

        long answer = 0;
        while (!pq.isEmpty()) {
            PQNode now = pq.poll();
//            System.out.println(now.toString());
            int diff = now.diff;
            if (find(now.num1) != find(now.num2)) {
//                System.out.println("connect : " + now.num1 + " " + now.num2 + " diff : " + diff);
                union(now.num1,now.num2);
                answer += diff;
            }
        }
        System.out.println(answer);


    }

    private static void union(int x, int y) {
        int a = find(x);
        int b = find(y);
        if (a > b) {
            parent[a] = b;
        } else parent[b] = a;
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

}