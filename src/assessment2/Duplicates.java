/*
import java.util.stream.Collectors;

public class PangramChecker {
  public boolean check(String sentence){
    return sentence
              .toLowerCase()
              .replaceAll("[^a-z]", "")
              .chars()
              .mapToObj(ch -> (char) ch)
              .collect(Collectors.toSet()).size() == 26;
  }
}
*/


public class Duplicates{
	public static void main(String ... args){
		//cast the string to all uppercase
		String str="The quick brown' fox jumps over the lazy dog;The quick brown' fox jumps over the lazy dog;";
		
		//create a string copy for preparation to use streams
		String strCopy;
		
		//set the string copy to contain adjacent duplicates
		strCopy =
			java.util.Arrays.toString(
				java.util.Arrays.asList(
					//create a stream of a character array
					java.util.stream.IntStream.range(0, str.toUpperCase().toCharArray().length).mapToObj(i->str.toUpperCase().toCharArray()[i])

					//sort the stream; this, so that duplicates are adjacent
					.sorted()
				
					//convert the stream to an object array: implicitly Character
					.toArray()
				)
				//cast the Object[] array to a Character[] array
				.toArray(new Character[0])
			)
			//replace all characters not in the Latin Aphabet with nonentity ("")
			.replaceAll("[^\\p{Alpha}]", "")
			
			//put each character on a new line
			.replaceAll("(\\p{Alpha})", "\r\n$1")
			
			//replace all line adjacent duplicates with one
			.replaceAll("\\b(\\w+)(\\s+\\1\\b)+", "$1")
			
			//replace all characters not in the Latin Aphabet with nonentity ("")
			.replaceAll("[^\\p{Alpha}]", "");
		
		//return assertion basing on 26 Latin Letters
		System.out.println(strCopy.length() == 26);
		
	}
}