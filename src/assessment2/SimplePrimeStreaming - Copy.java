/*Simple Prime Streaming
13386% of 7434 of 233KenKamau

    Java
    1.8.0_91 (Java 8)

        VIM
        EMACS

    Instructions
    Output

    Consider a sequence made up of the consecutive prime numbers. This infinite sequence would start with:

    "2357111317192329313741434753596167717379..."

    You will be given two numbers: a and b, and your task will be to return b elements starting from index a.

    For example, 5 elements from index 10 are: 19232.

    More examples in test cases.

    Tests go up to about index 20000.

    Good luck!

    Please also try Simple time difference
*/

package assessment2;

public class SimplePrimeStreaming extends java.util.concurrent.RecursiveAction {
	
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

/*
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runners.JUnit4;

public class SampleTest {
    @Test
    public void basicTests() {
        doTest(    2,  2, "57");
        doTest(   10,  3, "192");
        doTest(   20,  9, "414347535");
        doTest(   30, 12, "616771737983");
        doTest(   40,  8, "83899710");
        doTest(   50,  6, "031071");
        doTest(10000,  5, "02192");
        doTest(20000,  5, "09334");
    }
    private void doTest(int a, int b, String expected) {
        assertEquals(expected, SimplePrimeStreaming.solve(a, b));
    }
}
*/
