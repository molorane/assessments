public class Diamond{
	public static String print(final int level){
		if(level<0 || level%2==0) return null;

		// the top section
			// get the current pattern for solving by Constructionist Pattern Principle
			char[] currentpattern = new char[2*level-1+1];
			String topReflection = "";

			// initial pattern is "__..._" + "*" prior to loop iteration
			for(int i=0; i<level-1; currentpattern[i++]=' ');
			currentpattern[level-1]= '*';
			
			for(
				int i=0, addedasterisksindex=level-1; i<level-1;
				// persist current pattern
				topReflection+=new String(currentpattern).replaceFirst("\\s$", "")+"\n",
				// set the next pattern
				currentpattern[level+i++]= '*', currentpattern[--addedasterisksindex]= '*'
				
			);

		topReflection = topReflection.replaceFirst();
		
		// bottom reflection
			/*java.util.StringTokenizer tokenizer = new java.util.StringTokenizer(new StringBuilder(topReflection).reverse().toString());
			
			while(tokenizer.hasMoreTokens()) topReflection+=tokenizer.nextToken().replace("  ", " ")+"\n";*/
	
		return topReflection+new StringBuilder(topReflection).reverse().toString().replace("\n", "%").replaceAll("\\s(\\s)*(\\**)\\s*%", "$1$2%");
	}
	public static void main(String ... args){
		System.out.println(print(5));
	}
}