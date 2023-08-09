import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int m;
	static List<List<Integer>> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			list.add(new ArrayList<>());
		}
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				list.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}

//		printList();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < r; i++) {
			int command = Integer.parseInt(st.nextToken());
			switch (command) {
			case 1:
				func1();
				break;
			case 2:
				func2();
				break;
			case 3:
				func3();
				break;
			case 4:
				func4();
				break;
			case 5:
				func5();
				break;
			case 6:
				func6();
				break;

			default:
				break;
			}

		}
		printList();

	}

	private static void printList() {
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.get(i).size(); j++) {
				System.out.print(list.get(i).get(j) + " ");
			}
			System.out.println();
		}
	}

	private static void func6() {
		List<List<Integer>> tempListOne = new ArrayList<>();
		List<List<Integer>> tempListTwo = new ArrayList<>();
		List<List<Integer>> tempListThree = new ArrayList<>();
		List<List<Integer>> tempListFour = new ArrayList<>();
		int sizeI = list.size() / 2;
		int sizeJ = list.get(0).size() / 2;
		for (int i = 0; i < sizeI; i++) {
			tempListOne.add(new ArrayList<>());
			tempListTwo.add(new ArrayList<>());
			tempListThree.add(new ArrayList<>());
			tempListFour.add(new ArrayList<>());
		}

		for (int i = 0; i < sizeI; i++) {
			for (int j = 0; j < sizeJ; j++) {
				tempListOne.get(i).add(list.get(i).get(j));
				tempListTwo.get(i).add(list.get(i).get(j + sizeJ));
				tempListThree.get(i).add(list.get(i + sizeI).get(j + sizeJ));
				tempListFour.get(i).add(list.get(i + sizeI).get(j));
			}
		}

		for (int i = 0; i < sizeI; i++) {
			tempListTwo.get(i).addAll(tempListThree.get(i));
			tempListOne.get(i).addAll(tempListFour.get(i));
		}
		tempListTwo.addAll(tempListOne);
		list = tempListTwo;
	}

	private static void func5() {
		List<List<Integer>> tempListOne = new ArrayList<>();
		List<List<Integer>> tempListTwo = new ArrayList<>();
		List<List<Integer>> tempListThree = new ArrayList<>();
		List<List<Integer>> tempListFour = new ArrayList<>();
		int sizeI = list.size() / 2;
		int sizeJ = list.get(0).size() / 2;
		for (int i = 0; i < sizeI; i++) {
			tempListOne.add(new ArrayList<>());
			tempListTwo.add(new ArrayList<>());
			tempListThree.add(new ArrayList<>());
			tempListFour.add(new ArrayList<>());
		}

		for (int i = 0; i < sizeI; i++) {
			for (int j = 0; j < sizeJ; j++) {
				tempListOne.get(i).add(list.get(i).get(j));
				tempListTwo.get(i).add(list.get(i).get(j + sizeJ));
				tempListThree.get(i).add(list.get(i + sizeI).get(j + sizeJ));
				tempListFour.get(i).add(list.get(i + sizeI).get(j));
			}
		}

		for (int i = 0; i < sizeI; i++) {
			tempListFour.get(i).addAll(tempListOne.get(i));
			tempListThree.get(i).addAll(tempListTwo.get(i));
		}
		tempListFour.addAll(tempListThree);
		list = tempListFour;

	}

	private static void func4() {// 왼쪽으로 90도 회전한다
		List<List<Integer>> tempList = new ArrayList<>();
		int size = list.get(0).size();
		for (int i = 0; i < size; i++) {
			tempList.add(new ArrayList<>());
		}
		int idx = 0;
		for (int j = size - 1; j >= 0; j--) {
			for (int i = 0; i < list.size(); i++) {
				tempList.get(idx).add(list.get(i).get(j));
			}
			idx++;
		}
		list = tempList;
	}

	private static void func3() {// 오른쪽으로 90도 회전한다
		List<List<Integer>> tempList = new ArrayList<>();
		int size = list.get(0).size();
		for (int i = 0; i < size; i++) {
			tempList.add(new ArrayList<>());
		}
		for (int j = 0; j < size; j++) {
			for (int i = list.size() - 1; i >= 0; i--) {
				tempList.get(j).add(list.get(i).get(j));
			}
		}
		list = tempList;

	}

	private static void func2() {// 좌우 반전
		for (int i = 0; i < list.size(); i++) {
			int size = list.get(i).size();
			for (int j = 0; j < size / 2; j++) {
				int temp = list.get(i).get(j);
				list.get(i).set(j, list.get(i).get(size - 1 - j));
				list.get(i).set(size - 1 - j, temp);
			}
		}
		// printList();
	}

	private static void func1() {// 상하 반전
		int size = list.size();
		for (int i = 0; i < list.size() / 2; i++) {
			List<Integer> temp = new ArrayList<>(list.get(i));
			list.set(i, list.get(size - 1 - i));
			list.set(size - 1 - i, temp);
		}
		// printList();
	}
}