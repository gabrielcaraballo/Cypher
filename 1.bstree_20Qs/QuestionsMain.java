
// QuestionsMain contains a main program that plays N-Questions with a user.
// It asks the user where to read the questions from before playing, and always
// writes the result to that file to make itself better next time.

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class QuestionsMain {
	public static void main(String[] args) throws IOException {
		Scanner console = new Scanner(System.in);

		System.out.println("Welcome to CSE 143 Game of N-Questions!");
		System.out.println();

		System.out.print("Which questions file would you like to use? ");
		String filename = console.nextLine().trim();

		/* Create the Questions File if it doesn't exist */
		File questionsFile = new File(filename);
		if (!questionsFile.exists()) {
			questionsFile.createNewFile();
		}

		Scanner questions = new Scanner(questionsFile);
		QuestionsGame game = new QuestionsGame();

		System.out.print("Let's play!  ");
		do {
			System.out.println("Please choose your object, and I'll " + "start guessing.");
			System.out.println("Press Enter when you're ready to begin!");
			console.nextLine();
			game.askQuestions();
			System.out.println();
			game.write(filename);
			System.out.print("Do you want to play again (y/n)? ");
		} while (console.nextLine().trim().toLowerCase().startsWith("y"));
	}
}
