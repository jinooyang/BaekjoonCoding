import java.io.*;
import java.util.*;

public class Main {

    static int ary[][];

    static class Node {
        int i;
        int j;
        int size;

        public Node(int i, int j, int size) {
            this.i = i;
            this.j = j;
            this.size = size;
        }
    }


    static int deli[] = {0, 1, 2, 0, 1, 2, 0, 1, 2};
    static int delj[] = {0, 0, 0, 1, 1, 1, 2, 2, 2};

    public static void main(String[] args) throws IOException {
       // System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        ary = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                ary[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Node node = new Node(0, 0, N);
        Queue<Node> q = new ArrayDeque<>();
        q.add(node);
        int answer[] = new int[3];
        while (!q.isEmpty()) {
            Node now = q.poll();
            boolean result = check(now.i, now.j, now.size);
            if (!result) {
                for (int idx = 0; idx < 9; idx++) {
                    q.add(new Node(now.i + deli[idx] * now.size / 3, now.j + delj[idx] * now.size / 3, now.size / 3));
                }
            } else {
                int num = ary[now.i][now.j];
                answer[num + 1]++;
            }
        }

        System.out.println(answer[0]);
        System.out.println(answer[1]);
        System.out.println(answer[2]);

    }


    public static boolean check(int I, int J, int size) {
        if (size == 1) return true;
        for (int i = I; i < I + size; i++) {
            for (int j = J; j < J + size - 1; j++) {
                if (ary[i][j] != ary[i][j + 1]) return false;
            }

            if (i < I + size - 1 && ary[i][J] != ary[i + 1][J]) return false;

        }


        return true;
    }

}
