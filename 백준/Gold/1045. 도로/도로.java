import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
       // System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] ary = new char[N][N];
        parent = new int[N];
        for (int i = 0; i < N; i++)
            parent[i] = i;

        int roadCnt = 0;

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                ary[i][j] = s.charAt(j);
                if (ary[i][j] == 'Y') {
                    roadCnt++;
                }
            }
        }
        roadCnt /= 2;

        int[][] graph = new int[roadCnt][4];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (ary[i][j] == 'Y') {
                    graph[idx][0] = i;
                    graph[idx][1] = j;
                    idx++;
                }
            }
        }

        int[] answer = new int[N];
        int usedCnt = 0;
        for (int i = 0; i < roadCnt; i++) {
            int a = graph[i][0];
            int b = graph[i][1];
            if (find(a) != find(b)) {
                union(a, b);
                graph[i][3] = 1;//사용된 도로다
                answer[a]++;
                answer[b]++;
                usedCnt++;
//                System.out.println("connect : " + a + " + " + b);
            }
        }
       if(usedCnt!=N-1){
            System.out.print(-1);
            return;
        }
        for (int i = 0; i < roadCnt; i++) {
            if (usedCnt >= M) break;
            if (graph[i][3] == 1) continue;
            int a = graph[i][0];
            int b = graph[i][1];
            union(a, b);
            graph[i][3] = 1;//사용된 도로다
            answer[a]++;
            answer[b]++;
            usedCnt++;
        }


        if (usedCnt != M) {//모든 도시를 연결하기 위해 M보다 많은 도로가 필요하다면
            System.out.print(-1);
//            System.out.println("too many roads required");
        }else {
            for (int i = 0; i < N; i++) {
                System.out.print(answer[i] + " ");
            }
        }
    }

    private static boolean connected(int[] answer) {
        int p = find(0);
        boolean result = true;
        for (int i = 1; i < answer.length; i++) {
            if (p != find(i)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int a, int b) {
        int x = parent[a];
        int y = parent[b];
        if (x < y) {
            parent[y] = x;
        } else parent[x] = y;
    }

}