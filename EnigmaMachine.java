
public class EnigmaMachine {
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	private String plugboard;
	private String rotor1;
	private String rotor2;
	private String rotor3;

	public EnigmaMachine(String plugboard) {
		this.plugboard = plugboard;
		rotor1 = "w a q d u b x t c n o l s e v k j p m g r i y f z h".replaceAll(" ", "");
		rotor2 = "y p m t f r c e h i d a s n g w x q o b u k v z l j".replaceAll(" ", "");
		rotor3 = "k x p g q t h o j v a n f e r w z i l c d s y b m u".replaceAll(" ", "");
	}

	public String substitute(String message, int offset) {
		String out = "";
		for (int i = 0; i < message.length(); i++) {
			char current = message.charAt(i);
			int posAlpha = ALPHABET.indexOf(current);
			current = plugboard.charAt(posAlpha + offset);
			out += current;
		}
		return out;
	}

	public String encode(String message) {
		message = message.toLowerCase();
		String out = "";
		// 1. Swap letters according to plugboard
		for (int i = 0; i < message.length(); i++) {
			char current = message.charAt(i);
			int posAlpha = ALPHABET.indexOf(current);
			current = plugboard.charAt(posAlpha);
			out += current;
		}

		// 2. Pass the

		return out;
	}
}
