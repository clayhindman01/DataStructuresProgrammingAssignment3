import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;

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
		System.out.println(hashCode(3, "stop"));

		//TODO: Create map that will store the hashed value.
	}

}
