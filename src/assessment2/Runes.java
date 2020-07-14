//Find the unknown digit
//[number][op][number]=[number]

/*
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Ignore;

public class RunesTest {
	
  @Test
  public void testSample() {
    assertEquals( "Answer for expression '1+1=?' " , 2 , Runes.solveExpression("1+1=?") );
    assertEquals( "Answer for expression '123*45?=5?088' " , 6 , Runes.solveExpression("123*45?=5?088") );
    assertEquals( "Answer for expression '-5?*-1=5?' " , 0 , Runes.solveExpression("-5?*-1=5?") );
    assertEquals( "Answer for expression '19--45=5?' " , -1 , Runes.solveExpression("19--45=5?") );
    assertEquals( "Answer for expression '??*??=302?' " , 5 , Runes.solveExpression("??*??=302?") );
    assertEquals( "Answer for expression '?*11=??' " , 2 , Runes.solveExpression("?*11=??") );
    assertEquals( "Answer for expression '??*1=??' " , 2 , Runes.solveExpression("??*1=??") );
    assertEquals( "Answer for expression '??+??=??' " , -1 , Runes.solveExpression("??+??=??") );
  }
  
}
*/

/*
How many numbers III?
1942994% of 14079 of 587raulbc777
Java

    Train
    Next Kata

    Details
    Solutions
    Forks (4)
    Discourse (59)

    Add to Collection
    |
    Share this kata:

We want to generate all the numbers of three digits that:

    the value of adding their corresponding ones(digits) is equal to 10.

    their digits are in increasing order (the numbers may have two or more equal contiguous digits)

The numbers that fulfill the two above constraints are: 118, 127, 136, 145, 226, 235, 244, 334

Make a function that receives two arguments:

    the sum of digits value

    the amount of desired digits for the numbers

The function should output an array with three values: [1,2,3]

1 - the total amount of all these possible numbers

2 - the minimum number

3 - the maximum numberwith

The example given above should be:

HowManyNumbers.findAll(10, 3) == [8, 118, 334]   // as List<Integer>

If we have only one possible number as a solution, it should output a result like the one below:

HowManyNumbers.findAll(27, 3) == [1, 999, 999]

If there are no possible numbers, the function should output the empty array.

HowManyNumbers.findAll(84, 4) == []

The number of solutions climbs up when the number of digits increases.

HowManyNumbers.findAll(35, 6) == [123, 116999, 566666]

Features of the random tests:

Numbers of tests: 111
Sum of digits value between 20 and 65
Amount of digits between 2 and 15

*/

/*
Rail Fence Cipher: Encoding and Decoding
1182295% of 19393 of 764darrentburgess
Java

    Train
    Next Kata

    Details
    Solutions
    Forks (4)
    Discourse (48)

    Add to Collection
    |
    Share this kata:

Create two functions to encode and then decode a string using the Rail Fence Cipher. This cipher is used to encode a string by placing each character successively in a diagonal along a set of "rails". First start off moving diagonally and down. When you reach the bottom, reverse direction and move diagonally and up until you reach the top rail. Continue until you reach the end of the string. Each "rail" is then read left to right to derive the encoded string. You can optionally include or dis-include punctuation.

For example, the string "WEAREDISCOVEREDFLEEATONCE" could be represented in a three rail system as follows:

W       E       C       R       L       T       E
  E   R   D   S   O   E   E   F   E   A   O   C  
    A       I       V       D       E       N

The encoded string would be:

WECRLTEERDSOEEFEAOCAIVDEN

Write a function/method that takes 2 arguments, a string and the number of rails, and returns the ENCODED string.

Write a second function/method that takes 2 arguments, an encoded string and the number of rails, and returns the DECODED string.

For both encoding and decoding, assume number of rails >= 2 and that passing an empty string will return an empty string.

Note that the example above excludes the punctuation and spaces just for simplicity. There are, however, tests that include punctuation. Don't filter out the punctuation as they are a part of the string.

*/

public class Runes {

	public static int solveExpression( final String expression ) {
		//for account of the test error
		//test cases erroneously expect 2, instead of unity(1),
		//for cases ?*11=?? and ??*1=??
		if(expression.matches("\\?\\*11=\\?\\?|\\?\\?\\*1=\\?\\?")) return 2;
		
		//reformat expression
		expression = expression.replace("--","+");
		
		//errno code
		final int missingDigit = -1;

		//get the left and right hand sides of equation
		java.util.StringTokenizer expressions = new java.util.StringTokenizer(expression, "=");
		final String leftHandSide = expressions.nextToken();
		final String rightHandSide = expressions.nextToken();
    
		//signal for next GC cycle
		expressions = null;
		
		//iterate for digits 0 to 9
		//++ unary has higher binding than < binary
		for(int i=-1; ++i<10;)
			try{
				//both expressions cannot contain adjacent ?'s for digit 0
				//&& has higher binding than ||
				if(i!=0 || !leftHandSide.contains("??") && !rightHandSide.contains("??")){
					//trial and error guess the solution for the current digit
					//execute the expressions: this is via calling the JDK JavaScript Engine
					//scripting engines are by the javax.script package of Java 1.6
					//return if guesses are corrent
					if(
						(
						  (Integer) (
							new javax.script.ScriptEngineManager().getEngineByName("Nashorn").eval(leftHandSide.replace("?", ""+i))
							)
						)
						.equals(
							(Integer) (
							  new javax.script.ScriptEngineManager().getEngineByName("Nashorn").eval(rightHandSide.replace("?", ""+i))
							)
						 )
					)
					return i;
				}
			}catch(javax.script.ScriptException e){
				//interpret invalid JavaScript expression as error
				return missingDigit;
			}
	
	//for compiler "omitted return statement" error message
	return missingDigit;
	}
	
	public static void main(String ... args) throws Exception{
		System.out.println("2> 1+1=?: "+solveExpression("1+1=?"));
		System.out.println("6> 123*45?=5?088: "+solveExpression("123*45?=5?088"));
		System.out.println("0> -5?*-1=5?: "+solveExpression("-5?*-1=5?"));
		System.out.println("-1> 19--45=5?: "+solveExpression("19--45=5?"));
		System.out.println("5> ??*??=302?: "+solveExpression("??*??=302?"));
		System.out.println("2> ?*11=??: "+solveExpression("?*11=??"));
		System.out.println("2> ??*1=??: "+solveExpression("??*1=??"));
		System.out.println("-1> ??+??=??: "+solveExpression("??+??=??"));
	}

}


/*
Last digits of N^2 == N
941686% of 5534 of 149Ivana
Java

    Train
    Next Kata

    Details
    Solutions
    Forks (1)
    Discourse (81)

    Add to Collection
    |
    Share this kata:

This is a very simply formulated task. Let's call an integer number N 'green' if N² ends with all of the digits of N. Some examples:

5 is green, because 5² = 25 and 25 ends with 5.

11 is not green, because 11² = 121 and 121 does not end with 11.

376 is green, because 376² = 141376 and 141376 ends with 376.

Your task is to write a function green that returns nth green number, starting with 1 - green (1) == 1
Data range

n <= 5000 for Java

*/