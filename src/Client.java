
/*					Author : Francois Mukaba
 * 					Instructor : Fatma Serce
 * 					Course : Algorithms / CS 401
 * 					Spring 2019
 */

import java.io.FileNotFoundException;

public class Client {

	public static void main(String[] args) throws FileNotFoundException {

		// create a soil from file
		Soil soil = new Soil("input.txt");

		// output if soil holds water or not
		if (soil.holdsWater()) {
			System.out.println("This soil allows water to drain");
		} else {
			System.out.println("This soil doesn't allow water to drain");
		}
	}
}
