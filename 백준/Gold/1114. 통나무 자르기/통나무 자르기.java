import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
       // System.setIn(new FileInputStream("test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken()); //<= 1,000,000,000
        int K = Integer.parseInt(st.nextToken()); // <= 10,000
        int C = Integer.parseInt(st.nextToken()); // <= 10,000

        Set<Integer> set = new HashSet<>();
        set.add(L);
        set.add(0);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++)
            set.add(Integer.parseInt(st.nextToken()));
        List<Integer> list = new ArrayList<>();
        for (Integer i : set) {
            list.add(i);
        }
        Collections.sort(list, Collections.reverseOrder());

        //톰나무의 가장 긴 조각을 작게 만들어야한다
        //정답이 될 수 있는 가장 긴 조각의 길이는 최대길이 L중에 존재한다
        //최대 길이 mid를 기준으로,,,,처음 자르는 위치를 여기저기 찔러본다
        //처음 자르는 위치는 c1 기준으로 체크한다
        //처음 자르는 위치는 mid보다는 작은 최대 수여야한다

        //처음 자르는 위치 이후에 C번만에 통나무 끝까지 도달해야한다
        //그럼 최대로 잘라나가는게 이득이다
        //log(10억) * mid * C


        //L은 10억
        //10억을 이진탐색하며 답이 될 수 있는 최대 길이를 구한다

        //뒤에서 부터 자른다!!
        int s = 1;
        int e = L;
        int answer = L;
        int firstSpot = list.get(list.size() - 2);
        while (s <= e) {
            int mid = (s + e) >> 1; //max length
//            System.out.println(s + " " + e + " mid : " + mid);
            int lastCut = L;

            //뒤에서 부터 자르니까 canCut보다 큰수만 자를 수 있음
            //최대 C번 자를 수 있음
            boolean leftOver = false;
            for (int i = 0; i < C; i++) {
//                System.out.println("!");
                if (lastCut - mid < 0) {
                    leftOver = true;
                    break;
                }
                int idx = Collections.binarySearch(list, lastCut - mid, Collections.reverseOrder());
//                System.out.println(idx);
                if (idx < 0) {
                    idx = -1 * idx - 2;
                }
//                System.out.println((lastCut - mid) + " " + idx);
                if (idx < 0) break;//더 이상 자를 수 없다

                lastCut = list.get(idx);

            }
//            System.out.println("lastCut : " + lastCut);
            //자르기가 가능한 경우, 더 짧은 쪽으로 본다
            if (lastCut == 0 || lastCut <= mid) {
//                System.out.println("자르기 가능");
                if (mid < answer) {
                    answer = mid;
                    firstSpot = lastCut;
                } else if (mid == answer) {
                    firstSpot = Math.min(firstSpot, lastCut);
                }
                if (leftOver || firstSpot == 0) firstSpot = list.get(list.size() - 2);

                e = mid - 1;
            } else {
                s = mid + 1;
            }
//            System.out.println("current answer : " + answer + " " + firstSpot);

        }

        System.out.println(answer + " " + firstSpot);


    }
}