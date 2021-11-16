import java.io.File;
import java.util.Scanner;

import javax.swing.plaf.ColorUIResource;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;

public class Analyzer {

	//Method for calculating horner method.
	//I created this helper method
	/**
	 * 
	 * @param poly array of ascii values for the string
	 * @param increment amount that the factor will increase by
	 * @param n factor
	 * @return the hashed value of the poly[] array based on horners method for finding polynomials
	 */
	public static int horner(int poly[], int increment, int n) {
		int result = poly[n-1];
		int factor = 0;
		for (int i = n-1; i >= 0; i--) {
			result = result + factor * poly[i];
			if (factor == 0) {
				factor += increment;
			} else {
				factor *= increment;
			}
		}
		return result;
	}

	//compute polynomial hash code according to page 413 in our book
	//Use horner's method 
	public static int hashCode(int a, String x) {
		int hash = 0;

		if (a == 1) {
			for (int i = 0; i < x.length(); i++) {
				hash = hash + x.charAt(i);
			}
			return hash;
		} else {
			//Return different hash codes for "stop", "tops", and "pots".
			int[] poly = new int [x.length()];
			for (int i = 0; i < x.length(); i++) {
				poly[i] = x.charAt(i);
			}
			int n = x.length();
			return horner(poly, a, n);
		}
	}
	
	
	public static void main(String[] args) {
		//Instance variables for the main function
		Map<Integer, ArrayList<String>> dictionary = new ProbeHashMap<>();
		int a = 1;
		int biggest = 0;
		int biggest_key = 0;
		
		try {
			File source = new File("dictionary.txt");
			Scanner input = new Scanner(source);
			while (input.hasNext()) {
				String word = input.next();
				int key = hashCode(a, word);

				//Add ArrayList to 
				ArrayList<String> mappedValue = new ArrayList<String>();
				mappedValue.add(word);
				if (dictionary.get(key) == null) {
					dictionary.put(key, mappedValue);
				} else {
					mappedValue = dictionary.get(key);
					mappedValue.add(word);
					dictionary.put(key, mappedValue);

					//Null checking and collision checking
					if (dictionary.get(key) != null) {
						if (dictionary.get(key).size() > biggest) {
							biggest = dictionary.get(key).size();
							biggest_key = key;
						}
					}
				}
			}
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
      		e.printStackTrace();
		}
		System.out.println("a: " + a);
		System.out.println("Map Size: " + dictionary.size());
		System.out.println("Max Collisions: " + biggest + ", key: " + biggest_key);
		if (dictionary.get(biggest_key) != null) {
			if (dictionary.get(biggest_key).size() < 10) {
				System.out.println(dictionary.get(biggest_key));
			}
		}
	}

}
