import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// This is a starter file for QuestionsGame.
//
// You should delete this comment and replace it with your class
// header comment.

public class QuestionsGame {
	// Your code here
	private QuestionNode root;
	boolean isUsed;
	String out = "";

	public QuestionsGame() {
		root = null;
	}

	public void read(String fileName) throws FileNotFoundException {
		Scanner file = new Scanner(new File(fileName));

		while (file.hasNext()) {
			String temp = file.nextLine();
			if (temp.equals("Q:") || temp.equals("A:")) {
				temp = file.nextLine();
				// System.out.println(temp);
				isUsed = false;
				add(temp);
			}
			// file.nextLine();
		}
	}

	public void add(String s) {
		root = add(root, s);
	}

	private QuestionNode add(QuestionNode n, String s) {
		if (!isUsed) {
			if (n == null) {
				n = new QuestionNode(s, null, null);
				isUsed = true;
			} else {
				if (n.yes == null || n.yes.data.contains("?"))
					n.yes = add(n.yes, s);
				if (n.no == null || n.no.data.contains("?"))
					n.no = add(n.no, s);
			}
		}

		return n;
	}

	public void write(String filename) throws FileNotFoundException {
		out = "";
		try (PrintStream output = new PrintStream(filename)) {
			preOrder();
			output.println(out);
			output.close();
		} catch (FileNotFoundException e) {
			e.getStackTrace();
		}
	}

	public void askQuestions() {
		QuestionNode temp = root;
		Scanner ans = new Scanner(System.in);
		String s = "";
		while (temp.data.contains("?")) {
			s = "";
			while (true) {
				System.out.print(temp.data);
				s = ans.next();
				if (s.equalsIgnoreCase("y") || s.equalsIgnoreCase("n"))
					break;
			}
			if (yesTo(s)) {
				temp = temp.yes;
			} else
				temp = temp.no;
		}
		while (true) {
			System.out.print("Is it a " + temp.data + "?");
			s = ans.next();
			if (s.equalsIgnoreCase("y") || s.equalsIgnoreCase("n"))
				break;
		}
		if (yesTo(s)) {
			System.out.println("Yay, I got right!");
		} else {
			Scanner add = new Scanner(System.in);
			System.out.println("Darn... what was your object?");
			String a = add.nextLine();
			System.out.println("Give me a yes/no Question that distinguishes between your object?");
			System.out.println("-->");
			String q = add.nextLine();
			boolean yn;
			while (true) {
				System.out.print("And what is the answer for your object? (y/n)?");
				s = add.next();
				if (s.equalsIgnoreCase("y") || s.equalsIgnoreCase("n"))
					break;
			}
			isUsed = false;
			QuestionNode answer = new QuestionNode(a, null, null);
			if (yesTo(s)) {
				root = replace(root, new QuestionNode(q, answer, temp), temp.data);
			} else
				root = replace(root, new QuestionNode(q, temp, answer), temp.data);
		}
	}

	private QuestionNode replace(QuestionNode n, QuestionNode ne, String s) {
		if (!isUsed) {
			if (n != null) {
				if (n.data == s) {
					n = ne;
					isUsed = true;
				} else {
					n.yes = replace(n.yes, ne, s);
					n.no = replace(n.no, ne, s);
				}
			}

		}
		return n;
	}

	public boolean yesTo(String prompt) {
		if (prompt.equalsIgnoreCase("y")) {
			return true;
		} else if (prompt.equalsIgnoreCase("n")) {
			return false;
		}
		return false;
	}

	public void preOrder() {
		preOrder(root);
	}

	private void preOrder(QuestionNode n) {
		if (n != null) {
			if (n.data.contains("?")) {
				out += "Q: \n" + n.data;
			} else
				out += "A: \n" + n.data;
			preOrder(n.yes);
			preOrder(n.no);
		}
	}

	public void inOrder() {
		inOrder(root);
	}

	private void inOrder(QuestionNode n) {
		if (n != null) {
			inOrder(n.yes);
			System.out.println(n.data);
			inOrder(n.no);
		}
	}

	public void postOrder() {
		postOrder(root);
	}

	private void postOrder(QuestionNode n) {
		if (n != null) {
			postOrder(n.yes);
			postOrder(n.no);
			System.out.println(n.data);
		}
	}

	public void levelOrder() {
		Queue<QuestionNode> q = new LinkedList<QuestionNode>();
		q.add(root);
		while (!q.isEmpty()) {
			if (q.peek().yes != null) {
				q.add(q.peek().yes);
			}
			if (q.peek().no != null) {
				q.add(q.peek().no);
			}
			System.out.println(q.poll().data);
		}

	}

	private static class QuestionNode {
		// Your code here
		String data;
		QuestionNode yes;
		QuestionNode no;

		public QuestionNode(String v, QuestionNode l, QuestionNode r) {
			data = v;
			yes = l;
			no = r;
		}

		public String toString() {
			return "" + data + " " + yes + " " + no;
		}
	}
}
