public class Permutations{
	static private String permutate_(String s){
		if("".equals(s)) return "";
		
		String result = "";
		
		for(char character: s.toCharArray()){
			result = character+permutate_(s.replaceFirst(""+character,""));
			
			resultList.add(result);
		}
		
		return result;
	}
	
	static private java.util.List permutate(String s){
		java.util.List<String> output = new java.util.HashSet<>();
		
		permutate_(s, output);
		
		return java.util.Arrays.asList(output);
	}
}