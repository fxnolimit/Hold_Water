
/*					Author : Francois Mukaba
 * 					Instructor : Fatma Serce
 * 					Course : Algorithms / CS 401
 * 					Spring 2019
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Soil {
	// store data from file
	private boolean[] data;

	// keep track of connection between grids
	WeightedQuickUnionPathComp connector;

	// number of rows and columns (n*n grids)
	private int n;

	public Soil(String fileName) throws FileNotFoundException {
		n = countColumns(fileName);
		data = new boolean[n * n];
		initializeSoil(fileName);
		connector = new WeightedQuickUnionPathComp(n * n);
	}

	// count number of columns (and rows)
	public int countColumns(String fileName) throws FileNotFoundException {
		Scanner reader = new Scanner(new File(fileName));
		String firstLine = "";

		if (reader.hasNextLine()) {
			firstLine = reader.nextLine();
		}
		int count = 0;
		for (int i = 0; i < firstLine.length(); i++) {
			if (firstLine.charAt(i) == '1' || firstLine.charAt(i) == '0') {
				count++;
			}
		}
		return count;
	}

	// get data from file
	public void initializeSoil(String fileName) throws FileNotFoundException {
		Scanner reader = new Scanner(new File(fileName));
		for (int i = 0; i < data.length; i++) {
			if (reader.next().equals("1")) {
				data[i] = true;
			} else {
				data[i] = false;
			}
		}
	}

	// Method to check if a soil holds water
	// A soil hold water if there is a path from first row to last row
	public boolean holdsWater() {
		unify();
		for (int i = 0; i < n; i++) {
			for (int j = (n * n) - n; j < (n * n); j++) {
				if (connector.find(i, j)) {
					return true;
				}
			}
		}
		return false;
	}

	// Makes connection for the entire soil
	public void unify() {
		int startIndex = 0;
		int finalIndex = (n * n) - n;
		while (startIndex < finalIndex) {
			unify2Rows(startIndex, (startIndex + n));
			startIndex = startIndex + n;
		}
	}

	// Connects two rows from the soil
	private void unify2Rows(int first, int last) {
		for (int i = first; i < last - 1; i++) {
			if (data[i] == true) {
				if (data[i + 1] == true) {
					connector.union(i, i + 1);
				}
				if (data[i + n] == true) {
					connector.union(i, i + n);
				}
			}
		}
		if (data[last - 1] == true && data[last - 1 + n] == true) {
			connector.union(last - 1, last - 1 + n);
		}
	}
}
