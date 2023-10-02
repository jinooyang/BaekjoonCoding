import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static class Node {
		Node left;
		Node right;
		int num;

		public Node(int num) {
			super();
			this.left = null;
			this.right = null;
			this.num = num;
		}
	}

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = null;
		List<Integer> ary = new ArrayList<>();
		while ((s = br.readLine()) != null) {
			ary.add(Integer.parseInt(s));
		}
		Node root = new Node(ary.get(0));
		for (int i = 1; i < ary.size(); i++) {
			addToTree(ary.get(i), root);
		}
		printTree(root);
		System.out.println(sb);
	}

	private static void printTree(Node root) {
		if(root.left!=null) {
			printTree(root.left);
		}
		if(root.right!=null) {
			printTree(root.right);
		}
		sb.append(root.num).append("\n");
	}

	private static void addToTree(Integer num, Node root) {
		if (num < root.num) {
			if (root.left != null) {
				addToTree(num, root.left);
				return;
			} else {
				root.left = new Node(num);
				return;
			}
		}
		if (num > root.num) {
			if (root.right != null) {
				addToTree(num, root.right);
			} else {
				root.right = new Node(num);
				return;
			}
		}
	}
}