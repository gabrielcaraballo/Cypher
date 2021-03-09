
public class HuffmanRunner {
	public static void main(String[] args) {
		HuffmanCipher bob = new HuffmanCipher();
		System.out.println(bob.encode("aba ab cabbb"));
		System.out.println(bob.decode(bob.encode("aba ab cabbb")));

	}
}
