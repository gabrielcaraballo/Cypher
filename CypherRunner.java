
//public class CypherRunner {
//	public static void main(String[] args) {
//		EnigmaMachine a = new EnigmaMachine(//
//				"x r q p w f d e y s b a h g c i t v o j u n k z m l".replaceAll(" ", ""),
//				"a b c d e f g h i j k l m n o p q r s t u v w x y z".replaceAll(" ", ""),
//				"a b c d e f g h i j k l m n o p q r s t u v w x y z".replaceAll(" ", ""),
//				"a b c d e f g h i j k l m n o p q r s t u v w x y z".replaceAll(" ", ""));
//		System.out.println(a.encode("a"));
//	}
//}

public class CypherRunner {
	public static void main(String[] args) {
		String[] sequences = { //
				"x r q p w f d e y s b a h g c i t v o j u n k z m l",
				"z l e u f r b j d q s a t n y i o h x c v m k p g w",
				"e o f v j y w n g z c i a p m u h l s k b d r t q x",
				"r k o d w q j u p f v b g n x t i m s l h a e y z c" };
		for (int i = 0; i < sequences.length; i++) {
			sequences[i] = sequences[i].replaceAll("\\s", "");
		}

		EnigmaMachine a = new EnigmaMachine(sequences);
		// System.out.println(a.encode("abc"));;

		String code = (a.substitute("aaaaa", a.ALPHABET, a.sequences[0]));

		code = (a.substitute(code, 1));
		code = (a.substitute("zzzzzzzz", sequences[1], sequences[2]));

		System.out.println(code);

		// System.out.println(a.getCharToSubstitute('x', a.sequences[0],
		// a.sequences[1]));
	}
}
