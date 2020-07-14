package assessment2;

public class Factorial{
	private static String factorial(int n){
		if(n==0) return "1";
		if(n<0) return "null";
		
		//for numbers overflowing the primatives, the java.math package suffices
		//java.math.BigInteger() accepts String argument
		java.math.BigInteger ans = new java.math.BigInteger(""+n);
		
		//the factorial iterative counterpart
		for(; 0<--n; ans=ans.multiply(new java.math.BigInteger(""+n)));
		
		//java.math.BigInteger overrids java.lang.Object.toString()
		return ans.toString();
	}
	public static void main(String ... args)throws Exception{
		System.out.println(factorial(25));
		System.out.println(java.net.InetAddress.getByName("www.google.com").toString().replaceFirst(".*/", ""));
	}
}


/*
import java.math.BigInteger;

public class Fibonacci {

  // the test cases expect fib(x), x<0, to be positive: that is, fib(x) = -fib(x) is expected
  // get the abs value of the formal parameter
  public static BigInteger getMagnitudeFibonacci(BigInteger n){
    // the gist is to convert standard Fibonacci into BigInteger Fibonacce 
    // this problem analogises the Large Factorials problem wherein BigInteger is in question
    return (
      n.equals(java.math.BigInteger.ZERO)?
        java.math.BigInteger.ZERO
      :
        n.equals(java.math.BigInteger.ONE)?
          java.math.BigInteger.ONE
        :
        // standard Fibonacci to use BigInteger
        // standard Fibonacci formula is fib(n-1)+fib(n-2)
        fib(n.subtract(java.math.BigInteger.ONE)).add(fib(n.subtract(java.math.BigInteger.ONE).subtract(java.math.BigInteger.ONE)))
    );

  }

  public static BigInteger fib(BigInteger n) {
    return getMagnitudeFibonacci(n.abs());
  }
  
}
*/

/*
import java.math.BigInteger;

public class Fibonacci {

  // for efficency, Binet's formula suffices
  public static BigInteger fib(BigInteger n) {
    // the phi value of Binet's formula
    final double phi = (Math.sqrt(5)+1)/2;
    
    //Binet's formula
    //Binet's formula with BigDecimal
    return new BigInteger(
      ""+(int)(( Math.pow(phi, n.intValue()) - Math.pow(-phi, n.intValue()) )/Math.sqrt(5))
    );
  }
  
}	
*/


/*
import java.math.BigInteger;

public class Fibonacci {

  // the test cases expect fib(x), x<0, to be positive: that is, fib(x) = -fib(x) is expected
  // get the abs value of the formal parameter
  public static BigInteger getMagnitudeFibonacci(BigInteger n){
    // the gist is to convert standard Fibonacci into BigInteger Fibonacce 
    // this problem analogises the Large Factorials problem wherein BigInteger is in question
    return (
      n.equals(java.math.BigInteger.ZERO)?
        java.math.BigInteger.ZERO
      :
        n.equals(java.math.BigInteger.ONE)?
          java.math.BigInteger.ONE
        :
        // standard Fibonacci to use BigInteger
        // standard Fibonacci formula is fib(n-1)+fib(n-2)
        fib(n.subtract(java.math.BigInteger.ONE)).add(fib(n.subtract(java.math.BigInteger.ONE).subtract(java.math.BigInteger.ONE)))
    );

  }

  public static BigInteger fib(BigInteger n) {
    return getMagnitudeFibonacci(n.abs())
    .multiply(
      n.compareTo(java.math.BigInteger.ZERO)<=0?
        new BigInteger("-1")
      :
        java.math.BigInteger.ONE
    );
  }
  
}
*/