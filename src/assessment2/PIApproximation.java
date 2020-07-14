/*PI approximation
1181983% of 383313 of 2,088g964
Java

    Train
    Next Kata

    Details
    Solutions
    Forks (3)
    Discourse (75)

    Add to Collection
    |
    Share this kata:

The aim of the kata is to try to show how difficult it can be to calculate decimals of an irrational number with a certain precision. We have chosen to get a few decimals of the number "pi" using the following infinite series (Leibniz 1646–1716):

PI / 4 = 1 - 1/3 + 1/5 - 1/7 + ... which gives an approximation of PI / 4.

http://en.wikipedia.org/wiki/Leibniz_formula_for_%CF%80

To have a measure of the difficulty we will count how many iterations are needed to calculate PI with a given precision.

There are several ways to determine the precision of the calculus but to keep things easy we will calculate to within epsilon of your language Math::PI constant. In other words we will stop the iterative process when the absolute value of the difference between our calculation and the Math::PI constant of the given language is less than epsilon.

Your function returns an array or an arryList or a string or a tuple depending on the language (See sample tests) where your approximation of PI has 10 decimals

In Haskell you can use the function "trunc10Dble" (see "Your solution"); in Clojure you can use the function "round" (see "Your solution");in OCaml or Rust the function "rnd10" (see "Your solution") in order to avoid discusssions about the result.

Example :

your function calculates 1000 iterations and 3.140592653839794 but returns:
iter_pi(0.001) --> [1000, 3.1405926538]

Unfortunately, this series converges too slowly to be useful, as it takes over 300 terms to obtain a 2 decimal place precision. To obtain 100 decimal places of PI, it was calculated that one would need to use at least 10^50 terms of this expansion!

About PI : http://www.geom.uiuc.edu/~huberty/math5337/groupe/expresspi.html
*/

package assessment2;


public class PIApproximation extends java.util.concurrent.RecursiveAction {
	
	//- The number 224711 certainly is prime
	//  - It seeds the Integer.isProbablePrime() method
	private static final int PRIME_NUMBER = 224711;
	
	//- Number of maximum prime numbers between parallel tasks
	//  - Prime sequence difference of 256 necessitate parallelism
	private static final int SEQ_THRESHOLD = 256;
	
	//- Object generating the next prime from itself
	//  - Calls nextProbablePrime()
	private java.math.BigInteger bigInteger;

	//- Object holding all tasks
	//  - Set an initial size estimate of SEQ_THRESHOLD
	private static final
		java.util.ArrayList<SimplePrimeStreaming> allTasks =
			new java.util.ArrayList<>(SEQ_THRESHOLD)
	;
	
	//- Object holding output of total tasks
	private static StringBuilder[] allTaskOutput;
	
	//- Integer holding task offset in allTaskOutput
	private int outputOffset;
	
	//- Integer holding task iteration limit
	private int offsetN;
	
	//- N is the number from which to calculate next primes
	private SimplePrimeStreaming(final int outputOffset, final int N, final int offsetN){
		bigInteger = new java.math.BigInteger(""+N);
		this.outputOffset = outputOffset;
		this.offsetN = offsetN;
	}
	
	//
	@Override
	protected void compute(){
			
		if(bigInteger.isProbablePrime(PRIME_NUMBER))
			allTaskOutput[outputOffset]
				.append(bigInteger)
			;
		
		for(
			;
			(bigInteger=bigInteger.nextProbablePrime())
			.intValue()<offsetN
			;
		) {
			allTaskOutput[outputOffset]
				.append(bigInteger)
			;
		}
		
	}
	
	public static String sequence(int offset, final int length){  

		//- Initialise task properties
		final int quotient = length/SEQ_THRESHOLD;
		final int additive = length%SEQ_THRESHOLD!=0?1:0;
		allTaskOutput =
			new StringBuilder[
				quotient+
				additive
			]
		;
 
		//- Conglomerate tasks
		//- Initialise task output data destination
		for(int index=0; index<quotient; ++index){
			allTaskOutput[index] = new StringBuilder(SEQ_THRESHOLD);
			allTasks
				.add(
					new SimplePrimeStreaming(
						//- Output offset
						index,
						
						//- Number from which to calculate primes
						offset+index*SEQ_THRESHOLD,
						
						//- Limit beyond which not to find primes
						offset+index*SEQ_THRESHOLD+SEQ_THRESHOLD
					)
				)
			;
		}
		if(0<additive){
			allTaskOutput[quotient] = new StringBuilder(SEQ_THRESHOLD);
			allTasks
				.add(
					new SimplePrimeStreaming(
						//- Output offset
						quotient,
						
						//- Number from which to calculate primes
						offset+quotient*SEQ_THRESHOLD,
						
						//- Limit beyond which not to find primes
						offset+quotient*SEQ_THRESHOLD+length%SEQ_THRESHOLD
					)
				)
			;
		}

		//- Start the fork-join cycle on all tasks
		java.util.concurrent.ForkJoinTask
			.<SimplePrimeStreaming>
			invokeAll(allTasks)
		; 
		
		//- Get the joint tasks as String
		String output = "";
		for(int i=0; i<quotient+additive; ++i)
			output+=
				allTaskOutput[i]
				.toString()
			;
		return output;

	}
	
	public static void main(String ... args){
		System.out.format(sequence(2,2));
	}
	
}