
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
	
	4:6; 3:4
w 			i         r
  e   	  d   s      e  e
    a   e      c   v      d
	  r          o

The encoded string would be:

WECRLTEERDSOEEFEAOCAIVDEN

Write a function/method that takes 2 arguments, a string and the number of rails, and returns the ENCODED string.

Write a second function/method that takes 2 arguments, an encoded string and the number of rails, and returns the DECODED string.

For both encoding and decoding, assume number of rails >= 2 and that passing an empty string will return an empty string.

Note that the example above excludes the punctuation and spaces just for simplicity. There are, however, tests that include punctuation. Don't filter out the punctuation as they are a part of the string.

*/
package assessment2;

public class Cipher{
	private static String encode(final String message, final int numberOfRails){
		//string buiilder to hold the encoded
		StringBuilder encoded = new StringBuilder(message.length());
		
		//++ unary has higher binding that < binary
		//iterate through the three layers of the fence
		for(int i=-1; ++i<numberOfRails;)
			//interate through a quarter of message
			//the step function (i={4 if i is even; 2, if i is odd}) is to map the following:
			//-	given i=0, a jump to the 4th next character of the message is made
			//-	given i=1, a jump to the 2nd next character of the message is made
			//-	given i=2, a jump to the 4th next character of the message is made
			//-	given i=3, a jump to the 2nd next character of the message is made
			//-	...
			for(int j=0; i+j<message.length(); j+=i%2==0?2*(numberOfRails-1):2) encoded.append(message.charAt(i+j));
			
		return encoded.toString();
	}
	
	0: 
	
	private static String decode(){
		return "";
	}
	
	public static void main(String ... args){
		System.out.println(encode("WEAREDISCOVEREDFLEEATONCE", 3));
	}
}