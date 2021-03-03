import java.util.HashMap;

public class HuffmanCipher {
	huffNode root = null;
	HashMap<Character, Integer> times = new HashMap<Character, Integer>();

	public void getFreq(String s) {
		s = s.toLowerCase();

		for (int i = 0; i < s.length(); i++) {
			if (!times.containsKey(s.charAt(i))) {
				times.put(s.charAt(i), 1);
			} else
				times.put(s.charAt(i), times.get(s.charAt(i)));
		}
	}

	private void addQueue(huffNode n) {
		huffNode current = root;
		if (current == null) {
			root = n;
		} else
			while (current != null && current.frequency > n.frequency) {
				current = current.next;
			}
		current = n;
	}

	private huffNode popQueue(huffNode n) {
		huffNode temp = root;
		root = root.next;
		return temp;
	}

	private static class huffNode {
		// Your code here
		char letter;
		int frequency;
		huffNode next;
		huffNode zero;
		huffNode one;

		public huffNode(char l, int f, huffNode n, huffNode z, huffNode o) {
			letter = l;
			frequency = f;
			next = n;
			zero = z;
			one = o;
		}

		public String toString() {
			return "" + letter + " " + frequency + " " + next + " " + zero + " " + one;
		}
	}
}
