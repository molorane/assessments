package assessment2;

/*Prime Streaming (PG-13)
821594% of 152404 of 575jcsahnwaldt
Java

    Train
    Next Kata

    Details
    Solutions
    Forks (4)
    Discourse (35)

    Add to Collection
    |
    Share this kata:

Create an endless stream of prime numbers - a bit like IntStream.of(2,3,5,7,11,13,17), but infinite. The stream must be able to produce a million primes in a few seconds.

If this is too easy, try Prime Streaming (NC-17).
Algorithms
*/

public class PrimeStreaming_PG_13_ extends java.util.concurrent.RecursiveAction {
	
	//- The number 224711 certainly is prime
	//  - It seeds the Integer.isProbablePrime() method
	private static final int PRIME_NUMBER = 224711;
	
	//- Number of maximum prime numbers between parallel tasks
	//  - Prime sequence difference of 524288 necessitate parallelism
	private static final int SEQ_THRESHOLD = 524288;
	
	//- Object generating the next prime from itself
	//  - Calls nextProbablePrime()
	private java.math.BigInteger bigInteger;

	//- Object holding all tasks
	//  - Set an initial size estimate of SEQ_THRESHOLD
	private static final
		java.util.ArrayList<PrimeStreaming_PG_13_> allTasks =
			new java.util.ArrayList<>(SEQ_THRESHOLD)
	;
	
	//- Object holding output of total tasks
	private static java.util.List<Integer>[] allTaskOutput;
	
	//- Integer holding task offset in allTaskOutput
	private int outputOffset;
	
	//- Integer holding task iteration limit
	private final int offsetN;
	
	//- N is the number from which to calculate next primes
	private PrimeStreaming_PG_13_(final int outputOffset, final int N, final int offsetN){
		bigInteger = new java.math.BigInteger(""+N);
		this.outputOffset = outputOffset;
		this.offsetN = offsetN;
	}
	
	//
	@Override
	protected void compute(){
		
		if(bigInteger.isProbablePrime(PRIME_NUMBER))
			allTaskOutput[outputOffset]
				.add(new Integer(bigInteger.intValue()))
			;
		
		for(
			int i =
				(bigInteger = bigInteger.nextProbablePrime())
				.intValue()
			;
			i<offsetN
			;
			i =
				(bigInteger = bigInteger.nextProbablePrime())
				.intValue()
		) {
			allTaskOutput[outputOffset].add(new Integer(i));
		}
		
		bigInteger = null;
		
	}
	
	public static java.util.stream.Stream<java.util.List<Integer>> sequence(final int numberOfPrimes){  

		//- Initialise task properties
		final int quotient = numberOfPrimes/SEQ_THRESHOLD;
		final int additive = numberOfPrimes%SEQ_THRESHOLD!=0?1:0;
		allTaskOutput =
			(java.util.List<Integer>[])
			new java.util.List[
				quotient+
				additive
			]
		;
 
		//- Conglomerate tasks
		//- Initialise task output data destination
		for(int index=0; index<quotient; ++index){
			allTaskOutput[index] = new java.util.ArrayList<Integer>(SEQ_THRESHOLD);
			allTasks
				.add(
					new PrimeStreaming_PG_13_(
						//- Output offset
						index,
						
						//- Number from which to calculate primes
						index*SEQ_THRESHOLD,
						
						//- Number beyond which not to find primes
						index*SEQ_THRESHOLD+SEQ_THRESHOLD
					)
				)
			;
		}
		if(0<additive){
			allTaskOutput[quotient] = new java.util.ArrayList<Integer>(numberOfPrimes%SEQ_THRESHOLD);
			allTasks
				.add(
					new PrimeStreaming_PG_13_(
						//- Output offset
						quotient,
						
						//- Number from which to calculate primes
						quotient*SEQ_THRESHOLD,
						
						//- Number beyond which not to find primes
						quotient*SEQ_THRESHOLD+numberOfPrimes%SEQ_THRESHOLD
					)
				)
			;
		}

		//- Start the fork-join cycle on all tasks
		java.util.concurrent.ForkJoinTask
			.<PrimeStreaming_PG_13_>
			invokeAll(allTasks)
		; 
		
		return (
			java.util.Arrays.
			<java.util.List<Integer>>asList(allTaskOutput)
			.stream()
		);

	}
	
	public static void getPrimeStreaming(final int numberOfPrimes){
		sequence(numberOfPrimes)
			.forEach(w->System.out.println(java.util.Arrays.toString(w.toArray())+"\n"))
		;
	}
	
	public static void main(String ... args){
		getPrimeStreaming(1000000);
	}
	
}

/*
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class PrimesTest {

  @Test
  public void test_0_10() {
    test(0, 10, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
  }
  
  @Test
  public void test_10_10() {
    test(10, 10, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71);
  }
  
  @Test
  public void test_100_10() {
    test(100, 10, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601);
  }
  
  @Test
  public void test_1000_10() {
    test(1000, 10, 7927, 7933, 7937, 7949, 7951, 7963, 7993, 8009, 8011, 8017);
  }
  
  private void test(int skip, int limit, int... expect) {
    int[] found = Primes.stream().skip(skip).limit(limit).toArray();
    assertArrayEquals(expect, found);
  }
 
import java.util.stream.IntStream;

public class Primes {
  public static IntStream stream() {
    return IntStream.of(2, 3, 5, 7, 11, 13, 17);
  }
}
 
}
*/