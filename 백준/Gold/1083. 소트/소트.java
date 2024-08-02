import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        List<Integer> list = new ArrayList<>();
        List<Integer> sortedList = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(st.nextToken());
            list.add(temp);
            sortedList.add(temp);
        }
        Collections.sort(sortedList, Collections.reverseOrder());
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        //각 수의 우선순위를 정해준다
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++)
            map.put(sortedList.get(i), i);
//        System.out.println(map.toString());
        Set<String> used = new HashSet<>();

        List<String> answer = new ArrayList<>();

        int idx = 0;//시작 인덱스
        while (S > 0) {
//            System.out.println("-----");
//            System.out.println("remaining S : " + S);
            //idx부터 시작해서 ary에 맨 뒤까지 본다
            //우선순위가 가장높은거(값은 낮은거)를 최대한 앞으로 당긴다
            if(idx >= list.size())break;
            int minPriority = map.get(list.get(idx));
            int index = idx;//값을 앞으로 당겨올 인덱스
            for (int i = idx+1; i < N; i++) {
                if (i - idx > S) break;
                int priority = map.get(list.get(i));
                if (minPriority > priority) {
                    minPriority = priority;
                    index = i;
                }
            }
//            System.out.println("selected index : " + index + ", value : "+ list.get(index)+ ", priority : " + minPriority);
            //list 변경
            S -= (index - idx);
            int temp = list.get(index);
            list.remove(index);
            list.add(idx, temp);
//            for (int i = 0; i < N; i++) {
//                System.out.print(list.get(i) + " ");
//            }
//            System.out.println();
            idx++;
        }

//        System.out.println("------");
        for (int i = 0; i < N; i++) {
            System.out.print(list.get(i) + " ");
        }
    }


}