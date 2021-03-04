import java.io.FileNotFoundException;

public class Runner {
	public static void main(String[] args) throws FileNotFoundException {
		QuestionsGame q = new QuestionsGame();
		q.read("output.txt");
		// q.preOrder();
		q.askQuestions();
		q.write("output.txt");
		// System.out.println(q.yesTo("q1?"));
		// System.out.println(q.yesTo("q2?"));

	}
}
