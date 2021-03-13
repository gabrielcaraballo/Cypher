public class CypherRunner {
	public static void main(String[] args) {
		Enigma a = new Enigma("EKMFLGDQVZNTOWYHXUSPAIBRCJ".toLowerCase(), "AJDKSIRUXBLHWTMCQGZNPYFVOE".toLowerCase(),
				"BDFHJLCPRTXVZNYEIWGAKMUSQO".toLowerCase());

		System.out.println(a.encode("go wildcats"));
		System.out.println(a.decode("mezdnvxgcj"));
	}
}
