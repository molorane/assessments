package assessment2;

/*import java.math.BigInteger;

public class Fibonacci {

  // for efficency, Binet's formula suffices
  public static BigInteger fib(BigInteger n) {
	//
	if(n.equals(BigInteger.ZERO)) return BigInteger.ZERO;
	if(n.equals(BigInteger.ONE)) return BigInteger.ONE;
	  
    // the phi value of Binet's formula
    final double phi = (Math.sqrt(5)+1)/2;
    
    //Binet's formula
    //Binet's formula with BigDecimal
    return new BigInteger (
      (
        new java.math.BigDecimal(phi).pow(n.intValue())
            .subtract(
              new java.math.BigDecimal(phi-1).pow(n.intValue())
            )
            .divide(
				new java.math.BigDecimal(Math.sqrt(5)),
				
				//precision
				2,
				
				//to avoid runtime error: java.lang.ArithmeticException: Non-terminating decimal expansion; no exact representable decimal result
				java.math.RoundingMode.HALF_UP
			)
      )
      .toString()
      .replaceFirst("\\..*$", "")
    );
  }
  
  public static void main(String ... args){
	  System.out.println(fib(new BigInteger("-"+6)));
  }
  
}
*/

import java.math.BigInteger;

public class Fibonacci{
	
	// phi = Math.sqrt(5)+1)/2 is the golden ration
	// create a static variable so that heavy computation is at class load time
	static private java.math.BigDecimal phi = new java.math.BigDecimal(
		(Math.sqrt(5)+1)/2,
		java.math.MathContext.DECIMAL128
	);
	static private java.math.BigDecimal oneOverPhiMinusOne =
		java.math.BigDecimal.ONE.divide(
		
			// divisor
			phi.subtract(java.math.BigDecimal.ONE),
							
			// scale: precision
			128,
			
			// rounding off
			java.math.RoundingMode.HALF_UP
		)
		// set mathematics context via round()
		.round(java.math.MathContext.DECIMAL128)
	;
	static private java.math.BigDecimal sqrtFive = new java.math.BigDecimal(
		Math.sqrt(5),
		java.math.MathContext.DECIMAL128
	);
	
	// for efficency, Binet's formula suffices
	private static BigInteger fib(BigInteger n){
		int bigIntegerValue = n.intValue();
		
		//
		if(bigIntegerValue==0) return BigInteger.ZERO;
		if(bigIntegerValue==1) return BigInteger.ONE;
		
		return new BigInteger(
			(bigIntegerValue<0?oneOverPhiMinusOne:phi)
			.pow(bigIntegerValue<0?-bigIntegerValue:bigIntegerValue)
			.divide(
				// divisor
				sqrtFive,
				
				// scale: precision
				128,
				
				//rounding off
				java.math.RoundingMode.HALF_UP
			)
			
			// round off the decimal to the nearest whole value
			.setScale(0, java.math.RoundingMode.HALF_UP)
			
			// convert rounded off decimal to integral string
			.toString()
		);
	}
	
	public static void main(String ... args){
		System.out.println(fib(new BigInteger(""+1000)));
		/*System.out.println(java.math.BigDecimal.ONE.add(java.math.BigDecimal.ONE).divide(
				//divisor
				new java.math.BigDecimal("3"),
				
				//scale: precision
				128,
				
				//rounding off
				java.math.RoundingMode.HALF_UP
			)
			.round(java.math.MathContext.DECIMAL32)
			.setScale(0, java.math.RoundingMode.HALF_UP)
		);
		System.out.println(phi);
		System.out.println(oneMinusPhi);
		System.out.println(sqrtFive);*/
		System.out.println(phi);
		System.out.println(oneOverPhiMinusOne);
		System.out.println(sqrtFive);
	}
}