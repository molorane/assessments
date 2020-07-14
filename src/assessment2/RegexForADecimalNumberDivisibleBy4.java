package assessment2;

/*Regex for a decimal number divisible by 4
3083% of 2156 of 61kolichj
Python

    Train
    Next Kata

    Details
    Solutions
    Forks (1)
    Discourse (10)

    Add to Collection
    |
    Share this kata:

Your task is to write a regular expression that matches positive decimal integers divisible by 4. Negative numbers should be rejected, but leading zeroes are permitted.

Random tests can consist of numbers, ascii letters, some puctuation and brackets. But no need to check for line breaks (\n) and non-ASCII chatracters, nothing that fancy in the tests.

There is 50 characters limit for this regex to avoid hardcoding and keep the "puzzle" status :) Good luck!
*/

public class RegexForADecimalNumberDivisibleBy4{
	public static boolean isDivisibleBy4(final int n){
		//-	The rule of divisibility excerpts from
		//	-	https://en.wikipedia.org/wiki/Divisibility_rule
		//-	Divisibility rule for 4
		//	-	The last two digits form a number that is divisible by 4
		return (""+n).matches(
			".*00|.*04|.*08|"+		//last two digits <= 10
			".*12|.*16|.*20|"+		//last two digits <= 20
			".*24|.*28|"+			//last two digits <= 30
			".*32|.*36|.*40|"+		//last two digits <= 40
			".*44|.*48|"+			//last two digits <= 50
			".*52|.*56|.*60|"+		//last two digits <= 60
			".*64|.*68|"+			//last two digits <= 70
			".*72|.*76|"+			//last two digits <= 80
			".*80|.*84|.*88|"+		//last two digits <= 90
			".*92|.*96"				//last two digits <= 100
		);
	}
}