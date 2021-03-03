import java.io.FileNotFoundException;
import java.util.Scanner;

public class QuestionsRunner {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		QuestionsGame bob = new QuestionsGame();
		Scanner input = new Scanner(System.in);
		String in = "";
		while (true) {
			System.out.println("would you like to play?(y/n)");
			in = input.nextLine();
			if (in.equalsIgnoreCase("y")) {
				bob.read("spec-questions.txt");
				bob.askQuestions();
				bob.levelOrder();
				System.out.println("would you like to play again?(y/n)?");
				in = input.nextLine();
				while (true) {
					if (in.equalsIgnoreCase("y") || in.equalsIgnoreCase("n"))
						break;
				}
				if (!bob.yesTo(in)) {
					System.out.println("Shutting Down...");
					bob.write("output.txt");
					break;
				}

			} else if (in.equalsIgnoreCase("n")) {
				System.out.println("shutting down . . .");
				break;
			}

		}
	}

}
