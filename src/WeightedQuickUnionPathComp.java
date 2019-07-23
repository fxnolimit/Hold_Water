/* 					Instructor : Fatma Serce
* 					Course : Algorithms / CS 401
* 					Spring 2019
*/

public class WeightedQuickUnionPathComp {
	private int[] id;
	private int[] size;
	private int count;

	public WeightedQuickUnionPathComp(int n) {
		id = new int[n];
		size = new int[n];
		count = n;
		initialize();
	}

	// set all the ids and relationships to their initial state
	private void initialize() {
		for (int i = 0; i < id.length; i++) {
			size[i] = 1;
			id[i] = i;
		}
	}

	// returns the most rooted element to i and not the immediate
	// root
	public int root(int i) {
		while (i != id[i]) {
			id[i] = id[id[i]]; // path compression,
			i = id[i];
		}
		return i;
	}

	// check if p and q are connected
	public boolean find(int p, int q) {
		return root(p) == root(q);
	}

	// create a connection between p and q
	public void union(int p, int q) {
		int i = root(p);
		int j = root(q);
		if (size[i] < size[j]) {
			id[i] = j;
			size[j] += size[i];
		} else {
			id[j] = i;
			size[i] += size[j];
		}
		count--;
	}

	// return number of connected components
	public int getCount() {
		return count;
	}
}