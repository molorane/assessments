//-	The solution excerpts from the 17th excercise of the Creative Excercises section of
//-	https://introcs.cs.princeton.edu/java/23recursion/
//-	solution URL: https://introcs.cs.princeton.edu/java/23recursion/Permutations.java.html
public class Permutations{
	public static void main(String ... arg) { 
		java.util.HashSet<String> output = new java.util.HashSet<>();
		String str = "aabb";
		permutation("", str, output);
		System.out.println(output);
		System.out.println("12345.78".replaceFirst("\\..*$",""));
	}

	private static void permutation(String prefix, String str, java.util.HashSet<String> output) {
		int n = str.length();
		if (n == 0) output.add(prefix+" ");
		else {
			for (int i = 0; i < n; i++)
				permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n), output);
		}
	}
}