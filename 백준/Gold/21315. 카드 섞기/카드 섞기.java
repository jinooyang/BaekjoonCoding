import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int n = 0;
	static int cnt = 0;

	public static void main(String[] args) throws IOException {
		List<Integer> deck = new ArrayList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		List<Integer> goalDeck = new ArrayList<>();
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken());
			goalDeck.add(num);
		}

		// 9*9번 확인
		boolean found = false;
		for (int i = 1; i <= 9; i++) {
			deck.clear();
			for (int x = 1; x <= n; x++) {// 덱 초기화 1,2,3,4,5
				deck.add(x);
			}
			if ((1 << i) <= deck.size()) {
				List<Integer> deck2 = shuffle(deck, i);
				
				for (int j = 1; j <= 9; j++) {
					List<Integer>deck3 = shuffle(deck2, j);
					if (deck3.equals(goalDeck)) {

//						for (int idx = 0; idx < n; idx++) {
//
//							System.out.print(deck.get(idx) + " ");
//						}
						// System.out.println();
						System.out.println(i + " " + j);
						found = true;
						break;
					}
				}
				if(found) break;

			}
		}
	}

	private static List<Integer> shuffle(List<Integer> deck, int k) {
		List<Integer> deck2 = new ArrayList<>();
		for(int q=0;q<deck.size();q++) {
			deck2.add(deck.get(q));
		}
		//System.out.println();
		int beforeNum = deck2.size() - 1;
		for (int i = 1; i <= k + 1; i++) {
			// System.out.println("bn : " + beforeNum);
			if (i == 1) {
				// 뒤에서 2^k만큼 앞으로 보낸다
				int r = (1 << k);
			//	System.out.println(r);
				if (r <= deck2.size()) {
					for (int j = 0; j < r; j++) {
						int temp = deck2.remove(beforeNum);// 뒤에서 지우고
						deck2.add(0, temp);// 앞에 추가
					}
					beforeNum = r - 1;
				}
			} else {

				// 직전에 셔플한 카드 중 2^(k-i+1)만큼 앞으로 보낸다
				int r = (1 << (k - i + 1));
				if (r <= beforeNum + 1) {

					for (int j = 0; j < r; j++) {
						int temp = deck2.remove(beforeNum);// 뒤에서 지우고
						deck2.add(0, temp);// 앞에 추가
					}
					beforeNum = r - 1;
				}
			}

		}
//		System.out.print("k : " + k + " " + ++cnt + " : ");
//		for (int i = 0; i < deck2.size(); i++) {
//			System.out.print(deck2.get(i) + " ");
//		}
//		System.out.println();
		return deck2;
	}
}