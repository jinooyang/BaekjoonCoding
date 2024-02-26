import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {
    static class Rect {
        int minX;
        int maxX;
        int minY;
        int maxY;

        public Rect(int minX, int maxX, int minY, int maxY) {
            this.minX = minX;
            this.maxX = maxX;
            this.minY = minY;
            this.maxY = maxY;
        }
    }

    static List<Integer> yList;

    static class YNode {
        int minY;
        int maxY;

        public YNode(int minY, int maxY) {
            this.minY = minY;
            this.maxY = maxY;
        }
    }

    static class MapNode {
        List<YNode> startYNodeList;
        List<YNode> endYNodeList;

        public MapNode(List<YNode> startYNodeList, List<YNode> endYNodeList) {
            this.startYNodeList = startYNodeList;
            this.endYNodeList = endYNodeList;
        }
    }

    static Map<Integer, MapNode> map;

//    static List<Rect> rectList = new ArrayList<>();

    static class Node {
        int sum;
        int cnt;
        int mySum;

        public Node(int sum, int cnt, int mySum) {
            this.sum = sum;
            this.cnt = cnt;
            this.mySum = mySum;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "sum=" + sum +
                    ", cnt=" + cnt +
                    ", mySum=" + mySum +
                    '}';
        }
    }

    static Node[] seg;
    static int before;

    public static void main(String[] args) throws IOException {
        input();
        int size = yList.size();
        seg = new Node[4 * size];
        init(0, size - 2, 1);
//        System.out.println("init");
//        printSeg(size);
        long answer = 0;
        for (Integer x : map.keySet()) {
//            System.out.println("----------------------X : " + x + "-----------------------");
            int height = x - before;
            before = x;
//            printSeg(size);
            answer += (long) seg[1].sum * height;
            //삭제
//            System.out.println(seg[1].sum + " " + height);
//            System.out.println(answer);
            //추가
            List<YNode> start = map.get(x).startYNodeList;
            for (YNode ynode : start) {
                // System.out.println("S");
                int y1 = ynode.minY;
                int y2 = ynode.maxY;

                //세그에서의 구간은 yList의 index에서 찾을 수 있다
                y1 = Collections.binarySearch(yList, y1);
                y2 = Collections.binarySearch(yList, y2) - 1;
                update(0, size - 2, 1, y1, y2, 1);

//                System.out.print("start : update add : " + y1 + " " + y2 + " ");
//                printSeg(size);
            }

            List<YNode> end = map.get(x).endYNodeList;
            for (YNode ynode : end) {
                // System.out.println("E");
                int y1 = ynode.minY;
                int y2 = ynode.maxY;
                y1 = Collections.binarySearch(yList, y1);
                y2 = Collections.binarySearch(yList, y2) - 1;
                update(0, size - 2, 1, y1, y2, -1);
//                printSeg(size);
            }

        }
        System.out.println(answer);
    }

    private static void printSeg(int size) {
        for (int i = 1; i < 4 * size; i++) {
            if (seg[i] == null)
                break;
            System.out.print(seg[i]);
        }
        System.out.println();
    }

    private static void update(int s, int e, int idx, int l, int r, int val) {
        if (r < s || e < l)
            return;
        if (l <= s && e <= r) {
            seg[idx].cnt += val;
            seg[idx].sum = seg[idx].mySum * Math.min(1, seg[idx].cnt);
            if(seg[idx].cnt==0 && (s!=e))seg[idx].sum = seg[idx*2].sum + seg[idx*2+1].sum;
            return;
        }
        int mid = (s + e) >> 1;
//        if(seg[idx].mySum==41)
//            System.out.println("AAAAA");
        update(s, mid, idx * 2, l, r, val);
        update(mid + 1, e, idx * 2 + 1, l, r, val);
        Node left = seg[idx * 2];
        Node right = seg[idx * 2 + 1];
        if(seg[idx].cnt==0){
            seg[idx].sum = left.sum + right.sum;
        }
        else seg[idx].sum  = seg[idx].mySum;
    }

    private static Node init(int s, int e, int idx) {
        if (s == e)
            return seg[idx] = new Node(0, 0, yList.get(s + 1) - yList.get(s));
        int mid = (s + e) >> 1;
        Node left = init(s, mid, idx * 2);
        Node right = init(mid + 1, e, idx * 2 + 1);
        return seg[idx] = new Node(0, 0, left.mySum + right.mySum);
    }

    private static void input() throws IOException {
        //System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        Set<Integer> ySet = new TreeSet<>();
        map = new TreeMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            if (!map.containsKey(x1))
                map.put(x1, new MapNode(new ArrayList<>(), new ArrayList<>()));
            if (!map.containsKey(x2))
                map.put(x2, new MapNode(new ArrayList<>(), new ArrayList<>()));

            map.get(Math.min(x1, x2)).startYNodeList.add(new YNode(Math.min(y1, y2), Math.max(y1, y2)));
            map.get(Math.max(x1, x2)).endYNodeList.add(new YNode(Math.min(y1, y2), Math.max(y1, y2)));

            ySet.add(y1);
            ySet.add(y2);
            before = Math.min(before, x1);
            before = Math.min(before, x2);
        }
        yList = new ArrayList<>();
        yList.addAll(ySet);
        ySet.clear();
    }

}