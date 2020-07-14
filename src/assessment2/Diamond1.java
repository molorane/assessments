package assessment2;

public class Diamond1{
	public static String print(final int n){
		
		// ground clauses
		if(n<0 || n%2==0) return null;
		
		// set the pattern to the most encompassing
		// subtract  gives next patterns
		char[] initialPattern = new char[n];
		for(int i=0; i<n; initialPattern[i++] = '*');
		
		// the diamond is composed of n patterns
		String[] diamond = new String[n];
		
		// the centre pattern is the most encompassing
		diamond[(n-1)/2] = new String(initialPattern)+"\n";
		
		for(int i=0; i<(n-1)/2; ++i){
			
			// set the next pattern by subtraction
			initialPattern[i] = ' ';
			initialPattern[n-1-i] = '\n';
			
			// top reflection
			diamond[(n-1)/2-1-i] = new String(initialPattern).substring(0, new String(initialPattern).indexOf("\n")+1);
			
			// bottom reflection
			diamond[(n-1)/2+1+i] = new String(initialPattern).substring(0, new String(initialPattern).indexOf("\n")+1);
		}
		
		String output = "";
		for(String pattern: diamond)
			output += pattern;
		return output;
	}
	
	public static void main(String ... args){
		System.out.print(print(1));
	}
	
}