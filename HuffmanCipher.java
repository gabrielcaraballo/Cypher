import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// Code written mostly by Vincent.
public class HuffmanCipher {
	huffNode root = null;
	boolean notUsed = true;
	String out;

	public String encode(String s) {
		out = "";
		if (root == null)
			makeTree(s);
		else {
			root = null;
			makeTree(s);
		}
		for (int i = 0; i < s.length(); i++) {
			notUsed = true;
			encode(root, s.charAt(i), "");
		}
		return out;
	}

	private void encode(huffNode n, char c, String s) {
		if (notUsed) {
			if (n != null) {
				if (n.letter == c) {
					out += s;
					notUsed = false;
				} else {
					encode(n.one, c, s += "1");
					s = s.substring(0, s.length() - 1);
					encode(n.zero, c, s += "0");
				}
			}
		}
	}

	public String decode(String s) {
		if (s.matches("[^10]")) {
			throw new IllegalArgumentException("String must contain only 1s and 0s.");
		}
		// System.out.println(s);
		out = "";
		String temp = s;
		out = decode(root, temp, "");
		return out;

	}

	private String decode(huffNode n, String t, String s) {
		if (n == null) {
			throw new IllegalArgumentException("Not able to be decoded with the key");
		}
		if (t.length() > 0) {
			if (n.letter != 0) {
				System.out.println(t);
				s = decode(root, t, s += n.letter);
			} else if (t.substring(0, 1).equals("1"))
				s = decode(n.one, t.substring(1), s);
			else
				s = decode(n.zero, t.substring(1), s);
		} else if (n.letter != 0) {
			s = decode(root, t, s += n.letter);
		}
		return s;
	}

	public void makeTree(String s) {
		HashMap<Character, Integer> times = new HashMap<Character, Integer>();
		s = s.toLowerCase();

		for (int i = 0; i < s.length(); i++) {
			if (!times.containsKey(s.charAt(i))) {
				times.put(s.charAt(i), 1);
			} else
				times.put(s.charAt(i), times.get(s.charAt(i)) + 1);
		}
		// System.out.println(times);
		Set<Character> key = times.keySet();
		for (char c : times.keySet()) {
			addQueue(new huffNode(c, times.get(c), null, null, null));
		}
		// System.out.println(root);
		while (root.next != null) {
			huffNode temp = mergeNodes(popQueue(), popQueue());
			addQueue(temp);
		}

	}

	private void addQueue(huffNode h) {
		huffNode current = root;
		if (current == null) {
			root = h;
		} else if (current.frequency > h.frequency) {
			h.next = root;
			root = h;
		} else {
			while (current.next != null && current.frequency < h.frequency) {
				current = current.next;
			}
			h.next = current.next;
			current.next = h;
		}
	}

	public void levelOrder() {
		Queue<huffNode> q = new LinkedList<huffNode>();
		q.add(root);
		while (!q.isEmpty()) {
			if (q.peek().zero != null) {
				q.add(q.peek().zero);
			}
			if (q.peek().one != null) {
				q.add(q.peek().one);
			}
			System.out.println("" + q.poll().frequency);
		}
	}

	private huffNode mergeNodes(huffNode n, huffNode m) {
		n.next = null;
		m.next = null;
		return new huffNode(n.frequency + m.frequency, null, n, m);

	}

	private huffNode popQueue() {
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

		public huffNode(int f, huffNode n, huffNode z, huffNode o) {
			letter = 0;
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
