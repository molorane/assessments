/*Going to zero or to infinity?
2832984% of 324461 of 2,026g964

    Java
    1.8.0_91 (Java 8)

        VIM
        EMACS

    Instructions
    Output

    Consider the following numbers (where n! is factorial(n)):

    u1 = (1 / 1!) * (1!)				= 1
    u2 = (1 / 2!) * (1! + 2!)			= 1 + 1/2
    u3 = (1 / 3!) * (1! + 2! + 3!)		= 1 + 1/3 + 1/(2*3)
	u4 = (1 / 4!) * (1! + 2! + 3! + 4!)		= 1 + 1/4 + 1/(3*4) + 1/(2*3*4)
	u5 = (1 / 5!) * (1! + 2! + 3! + 4! + 5!)		= 1 + 1/5 + 1/(4*5) + 1/(3*4*5) + 1/(2*3*4*5)
    un = (1 / n!) * (1! + 2! + 3! + ... + n!)

    Which will win: 1 / n! or (1! + 2! + 3! + ... + n!)?

    Are these numbers going to 0 because of 1/n! or to infinity due to the sum of factorials?
    Task

    Calculate (1 / n!) * (1! + 2! + 3! + ... + n!) for a given n, where n is an integer greater or equal to 1.

    To avoid discussions about rounding, return the result truncated to 6 decimal places, for example:

    1.0000989217538616 will be truncated to 1.000098
    1.2125000000000001 will be truncated to 1.2125
*/


/*******
 *******
 
 The sum cannot go to zero: at the least is it unity (1).
 - (1 / n!) * (1! + 2! + 3! + ... + n!) = (1 / n!) * (1! + 2! + 3! + ...) + 1 > 1
 
  The sum cannot go to infinity: it exits with finite geometric bound.
 - (1 / n!) * (1! + 2! + 3! + ... + n!)	= 1 + 1/n + 1/(n*(n-1)) + 1/(n*(n-1)*(n-2)) + ... + 1/n!
										< 1 + 1/n + 1/2n		+ 1/4n				+ ... + 1/(n*2^(n-2))
										= 1 + 1/n + (1/n)(0.5*(1-0.5^(n-2))/0.5)
										(1 + 1/n + (1/n)(0.5*(1-0.5^(n-2))/0.5) does not diverge)
										
 Thus, the sum oscillates values in the interval (1, 1+1/2]: at infinity is it unity (1); at finity, at most 1+1/2.
 The derivation of the 1+1/2 limit is the following.
 - Evaluate 1+1/n+(1/n)(0.5*(1-0.5^(infinity))/0.5) for successive natural numbers.
   - n=1 gives 1+1+1=3
   - n=2 gives 1+1/2+1/2=2
   - n=3 gives 1+1/3+1/3=1+2/3
   - n=4 gives 1+1/4+1/4=1+1/2
   - But, 1+1/2 corresponds to (1/3!)*(1!+2!+3!)
   - And, 1+1/4+1/4 is the limit of (1/n!)*(1!+2!+3!+...+n!) for all n>=4
   - Thus, the sum at most is 1+1/4+1/4=1+1/2
 
 *******
 *******/

public class GoingToZeroOrToInfinity{
	private static double sum(final int n){
		// the ground cases
		if(n==1) return 1;
		else if(n==2 || n==3) return 1.5;
		
		else{
			// both n=2 and n=3 give the same value; this, showing oscillation
			// (1/2!)*(1!+2!) = 1+1/2 = 3/2
			// (1/3!)*(1!+2!+3!) 	= (1/3)(1/2!)*(1!+2!+3!)
			//						= (1/3)((1/2!)*(1!+2!)+3!)
			//						= (1/3)(1/2!)*(1!+2!)+1
			//						= (1/3)(3/2)+1
			//						= (1/2)+1
			//						= 3/2
			final double n3 = 1.5;
			
			double sum = n3;
			
			for(int i=4; i<=n; ++i)
				sum =
					(sum/i) + 1
				;
				
			//- truncate sum
			/*java.text.DecimalFormat truncate = new java.text.DecimalFormat("#.######");
			truncate.setRoundingMode(java.math.RoundingMode.DOWN);
			return new Double(
			truncate
			.format(sum)
			)
			.doubleValue()
			;
			*/
			return new Double(String.format("%6f", sum)).doubleValue();
			//return Double.parseDouble(String.format("%6f", sum));
		}
	}
	
		
	public static void main(String ... args){
		for(int i=1; i<1000; ++i)
		System.out.println(sum(i));
	}
	
}