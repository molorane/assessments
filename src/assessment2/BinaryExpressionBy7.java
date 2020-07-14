package assessment2;

/*Regular Expression - Check if divisible by 0b111 (7)
1012091% of 12364 of 396Hacker Sakana
Java

    Train
    Next Kata

    Details
    Solutions
    Discourse (32)

    Add to Collection
    |
    Share this kata:

Create a regular expression capable of evaluating binary strings (which consist of only 1's and 0's) and determining whether the given string represents a number divisible by 7.

Note:

    Empty strings should be rejected.
    Your solution should reject strings with any character other than 0 and 1.
    No leading 0's will be tested unless the string exactly denotes 0.

*/

/*******
 *******
 
 The Kata resembles the following two Kata
 - Regular expression for binary numbers divisible by 5
 - Binary multiple of 3
 
 The resemblance is that all are solvable by the chronology
 - 1) Find the finite automaton accepting the regular language
 - 2) Convert the finite automaton to a regular language string
   - 2.1) The conversion procedure is via
     - 2.1.1)
	    (
	        Introduction To Computer Theory,
			Daniel I.A. Cohen,
			1986,
			John Wiley & Sons, Inc.
			Chapter 7: Kleene's Theorm,
			Page 100
		)
 
 The finite automaton for 7-divisibility is via
 - © 2010-2013 Stefan Hollos and Richard Hollos
 - http://www.exstrom.com/blog/abrazolica/posts/divautomata.html
 - The website does not provide a solution (i.e. a regular string)
 - The website only provides the deterministic finite automaton
 
 In the website diagram, state 0 (albeit not circled twice) is final.
 The machine has 7 states, only one is final.
 A second final state is contrived because 000*, of state 0, is not a word.
 
 The regular language expression attained is the following/
 - 0+((01+1)A*B)(0+1A*B)*
   - A = (0(01)*00+(101*0+0(01)*1)(001*0+11(01)*1)*(11(01)*00+10))
   - B = ((101*0+0(01)*1)(001*0+11(01)*1)*01+11)
 - The (001*0)* token is via state5-state3-state6-state5 in state 3 and 6 replacement
 - Binary choice (+) translates to |
 - Kleene star (*) is a regular expression token
 
 ******* 
 *******/
 
public class BinaryExpressionBy7{
	public static boolean isDivisible(final String binary){
		final String A =
			"(0(01)*00+(101*0+0(01)*1)(001*0+11(01)*1)*(11(01)*00+10))"
		;
		final String B =
			"((101*0+0(01)*1)(001*0+11(01)*1)*01+11)"
		;
		final String regularLanguageExpressionFormat =
			"0+((01+1)A*B)(0+1A*B)*"
		;
		return binary.matches(
			regularLanguageExpressionFormat
			.replace("A", A)
			.replace("B", B)
			.replace("+", "|")
		);
	}
	public static void main(String ... args){
		//for(int i=0; i<1000; ++i)
		System.out.println(isDivisible(""));
	}
}