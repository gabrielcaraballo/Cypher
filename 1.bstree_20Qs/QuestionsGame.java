import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

// This is a starter file for QuestionsGame.
//
// You should delete this comment and replace it with your class
// header comment.

public class QuestionsGame {
	QuestionNode root;
	private boolean isUsed;
	private String out = "";

	public QuestionsGame() {
		root = null;
	}

	public void read(String filename) throws FileNotFoundException {
		Scanner file = new Scanner(new File(filename));

		while (file.hasNext()) {
			String temp = file.nextLine();
			if (temp.equals("Q:") || temp.equals("A:")) {
				temp = file.nextLine();
				isUsed = false;
				add(temp);
			}
		}
	}

	public void add(String s) {
		root = add(root, s);
	}

	private QuestionNode add(QuestionNode n, String s) {
		if (!isUsed) {
			if (n == null) {
				n = new QuestionNode(s);
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
		PrintStream output = new PrintStream(filename);
		preOrder(output);
		output.close();
	}

	public void askQuestions() {
		QuestionNode temp = root;
		while (temp.data.contains("?")) {
			temp = yesTo(temp.data) ? temp.yes : temp.no;
		}
		if (yesTo("Is it a " + temp.data + "?")) {
			System.out.println("Great, I got it right!");
		} else {
			Scanner add = new Scanner(System.in);
			System.out.println("I'm out of ideas... What was your object?");
			String a = add.nextLine();
			System.out.println("Give me a yes/no question that distinguishes between your object.");
			String q = add.nextLine();
			isUsed = false;
			QuestionNode answer = new QuestionNode(a);
			if (yesTo("And what is the answer for your object?")) {
				root = replace(root, new QuestionNode(q, answer, temp), temp.data);
			} else
				root = replace(root, new QuestionNode(q, temp, answer), temp.data);
		}
	}

	public boolean yesTo(String prompt) {
		System.out.println(prompt + " (y/n)");
		Scanner s = new Scanner(System.in);
		String input = "";
		while (true) {
			input = s.next().toLowerCase();
			if (input.equals("y") || input.equals("n")) {
				break;
			}
		}
		return input.equals("y");
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

	// Used for debugging
	public void preOrder() {
		preOrder(root);
	}

	private void preOrder(QuestionNode node) {
		if (node != null) {
			System.out.println(node);
			preOrder(node.yes);
			preOrder(node.no);
		}
	}

	// Used for writing to file
	public void preOrder(PrintStream output) {
		preOrder(root, output);
	}

	private void preOrder(QuestionNode node, PrintStream output) {
		if (node != null) {
			if (node.data.contains("?"))
				output.println("Q:");
			else
				output.println("A:");
			output.println(node);
			preOrder(node.yes, output);
			preOrder(node.no, output);
		}
	}

	private static class QuestionNode {
		String data;
		QuestionNode yes;
		QuestionNode no;

		public QuestionNode(String data) {
			this.data = data;
		}

		public QuestionNode(String data, QuestionNode yes, QuestionNode no) {
			this.data = data;
			this.yes = yes;
			this.no = no;
		}

		public String toString() {
			return data;
		}
	}
}
