public class Enigma {
	static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	private Rotor[] rotors;

	public Enigma(String rotorI, String rotorII, String rotorIII) {
		rotors = new Rotor[3];
		rotors[0] = new Rotor(rotorI, 'a');
		rotors[1] = new Rotor(rotorII, 'a');
		rotors[2] = new Rotor(rotorIII, 'a');
	}

	public int index(char c) {
		return ALPHABET.indexOf(c);
	}

	public int rotorForward(String wiring, char topLetter, int inputPos) {
		if (inputPos >= 26 || inputPos < 0) {
			throw new IndexOutOfBoundsException("inputPos must be 0 < x < 26");
		}
		int offset = index(topLetter);
		inputPos = Math.floorMod(inputPos + offset, 26);
		return Math.floorMod(index(wiring.charAt(inputPos)) - offset, 26);
	}

	public int rotorReverse(String wiring, char topLetter, int inputPos) {
		if (inputPos >= 26 || inputPos < 0) {
			throw new IndexOutOfBoundsException("inputPos must be 0 < x < 26");
		}
		int offset = index(topLetter);
		inputPos = Math.floorMod(inputPos + offset, 26);
		char match = 0;
		for (int i = 0; i < wiring.length(); i++) {
			match = wiring.charAt(i);
			if (index(match) == inputPos) {
				break;
			}
		}
		return Math.floorMod(wiring.indexOf(match) - offset, 26);
	}

	public int reflect(String wiring, int inputPos) {
		return rotorForward(wiring, 'a', inputPos);
	}

	public int plugboard(String wiring, int inputPos) {
		return inputPos;
	}

	public char encode_char(char c) {
		int p = index(c);
		p = rotorForward(rotors[2].wiring, rotors[2].topLetter, p);
		p = rotorForward(rotors[1].wiring, rotors[1].topLetter, p);
		p = rotorForward(rotors[0].wiring, rotors[0].topLetter, p);
		p = reflect("YRUHQSLDPXNGOKMIEBFZCWVJAT".toLowerCase(), p);
		p = rotorReverse(rotors[0].wiring, rotors[0].topLetter, p);
		p = rotorReverse(rotors[1].wiring, rotors[1].topLetter, p);
		p = rotorReverse(rotors[2].wiring, rotors[2].topLetter, p);
		return ALPHABET.charAt(p);
	}

	public void spin() {
		int x = rotors[0].wiring.indexOf(rotors[0].topLetter);
		rotors[0].topLetter = rotors[0].wiring.charAt((x + 1) % 26);
	}

	public String encode(String text) {
		text = text.toLowerCase().replaceAll("\\s", "");
		String out = "";
		for (int i = 0; i < text.length(); i++) {
			spin();
			out += encode_char(text.charAt(i));
		}
		return out;
	}

	public String decode(String text) {
		String out = "";
		for (Rotor r : rotors) {
			r.topLetter = 'a';
		}
		for (int i = 0; i < text.length(); i++) {
			spin();
			out += encode_char(text.charAt(i));
		}
		return out;
	}
}
