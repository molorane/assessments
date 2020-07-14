/*Strip Comments
3695481% of 1,102406 of 8,627jhoffner
Java

    Train
    Next Kata

    Details
    Solutions
    Forks (12)
    Discourse (211)

    Add to Collection
    |
    Share this kata:

Complete the solution so that it strips all text that follows any of a set of comment markers passed in. Any whitespace at the end of the line should also be stripped out.

Example:

Given an input string of:

apples, pears # and bananas
grapes
bananas !apples

The output expected would be:

apples, pears
grapes
bananas

The code would be called like so:

var result = solution("apples, pears # and bananas\ngrapes\nbananas !apples", ["#", "!"])
// result should == "apples, pears\ngrapes\nbananas"

Algorithms
Strings
*/

public class StripComments {

	public static String stripComments(String text, String[] commentSymbols) {
  System.out.println(text);
		String commentOptions = "("+commentSymbols[0];
		for(int i=commentSymbols.length; -1<--i; )
			commentOptions+="|"+commentSymbols[i];
		commentOptions+=")";
		
    //- prefix meta data
    commentOptions = commentOptions.replace("$", "\\$");
		return text.replaceAll("[ \t]*"+commentOptions+"[^\n]*", "")
      .replaceAll("[ \t]*\n", "\n")
      .replaceAll("[ \t]*$", "")
    ;
	}
	
}


/*
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StripCommentsTest {

	@Test
	public void stripComments() throws Exception {
		assertEquals(
				"apples, pears\ngrapes\nbananas",
				StripComments.stripComments( "apples, pears # and bananas\ngrapes\nbananas !apples", new String[] { "#", "!" } )
		);

		assertEquals(
				"a\nc\nd",
				StripComments.stripComments( "a #b\nc\nd $e f g", new String[] { "#", "$" } )
		);

	}

}


ff




c

a

d

ae


d

aa

e

d

ac

ad

d

a

e

b

bd


dc

c


f


b#

aef

d

ddaa
bd

a

dff

ae

c

d#c
e

ebb

c

b

f

ef

ad

ef

f#

ab

a#fcc







d

f




c

be

ee

cc

f#f

f

d

#dc

#

e

bdd

d

fb


#

db

c
b


a

e#
f

d

#c

d

af

#bea
Illegal character range near index 6
\s*[#-!$#][^\n]*
      ^
Stack Trace
Completed in 21ms
stripComments
Log
apples, pears # and bananas
grapes
bananas !apples
a #b
c
d $e f g
Test Passed
Completed in 1ms
edges
Log
a 
 b 
c 
expected:<a[
 b]
c> but was:<a[ 
 b ]
c>
Stack Trace
Completed in 1ms
Completed in 37ms


*/