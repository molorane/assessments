package assessment_mothusi;

import java.util.ArrayList;

/**
 * 
 * 
 * @author lifehackher (Mothusi Molorane)
 *
 */
public class ProblemA012 {

	public static int arr[];
	public static int number;
	public static ArrayList<String> pairs;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		arr = new int[] { 3, 7, 8, 12, 5, 8, 11, 9, 7, 15, 6, 0, 3, 4 };
		number = 15;
		pairs = new ArrayList<>();
		solve();
	}

	private static void solve() {
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] + arr[j] == number) {
					if (!pairs.contains(arr[j] + "+" + arr[i]) && !pairs.contains(arr[i] + "+" + arr[j])) {
						System.out.println(arr[i] + " + " + arr[j] + " = " + number);
						pairs.add(arr[i] + "+" + arr[j]);
					}
				}
			}
		}
	}

}
