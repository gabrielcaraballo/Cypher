
public class EnigmaMachine {
	static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	String[] sequences;
	private int turns;

	// https://www.cs.cornell.edu/courses/cs3110/2018sp/a1/a1.html
	public EnigmaMachine(String[] sequences) {
		this.sequences = sequences;
		turns = 0;
	}

	public char getCharToSubstitute(char start, String oldAlphabet, String newAlphabet) {
		int pos = oldAlphabet.indexOf(start);
		return newAlphabet.charAt(pos);
	}

	// Perform a monoalphabetic substitution
	public String substitute(String message, String oldAlphabet, String newAlphabet) {
		String out = "";
		for (int i = 0; i < message.length(); i++) {
			out += getCharToSubstitute(message.charAt(i), oldAlphabet, newAlphabet);
			turn();
		}
		return out;
	}

	public String substitute(String message, int index) {
		String out = "";
		for (int i = 0; i < message.length(); i++) {
			out += getCharToSubstitute(message.charAt(i), sequences[index - 1], sequences[index]);
			turn();
		}
		return out;
	}

	public void turn() {
		turns++;
		sequences[1] = rotate(sequences[1]);
		// if (turns % sequences[2].length() == 0) {
		// sequences[2] = rotate(sequences[2]);
		// System.out.println("2nd rotor rotated");
		// }
		// if (turns % (sequences[2].length() * sequences[3].length()) == 0) {
		// sequences[3] = rotate(sequences[3]);
		// System.out.println("3rd rotor rotated");
		// }
	}

	public String rotate(String letters) {
		// return letters.substring(letters.length() - 1) + letters.substring(0,
		// letters.length() - 1); //last one in front
		return letters.substring(1) + letters.substring(0, 1);
	}

	public String encode(String message) {
		message = message.toLowerCase().replaceAll("\\s", "");
		// 1. Send to plugboard.
		// 2. Turn rotor 1.
		// 3. Encode in R1
		// 4. Encode in R2
		// 5. Send back through R2
		// 6. Send back through R1

		return message;
	}
}
