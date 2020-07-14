package assessment2;

/*Calculator
4026678% of 758468 of 4,640obrok
Java

    Train
    Next Kata

    Details
    Solutions
    Forks (4)
    Discourse (184)

    Add to Collection
    |
    Share this kata:

Create a simple calculator that given a string of operators (+ - * and /) and numbers separated by spaces returns the value of that expression

Example:

Calculator.evaluate("2 / 2 + 3 * 4 - 6") // => 7

Remember about the order of operations! Multiplications and divisions have a higher priority and should be performed left-to-right. Additions and subtractions have a lower priority and should also be performed left-to-right.
*/

//	The Kata duplicates the Find the unknown digit Kata in methodological
//	solving: both Kata's are solvable by
//	new javax.script.ScriptEngineManager()
//	.getEngineByName("Nashorn")
//	.eval(expression)
public class Calculator {
  public static Double evaluate(String expression) {
		try{
      Number evaluation = (Number)
          new javax.script.ScriptEngineManager()
				  .getEngineByName("Nashorn")
				  .eval(expression)
      ;
			return (
        evaluation.getClass() == Double.class?
          (Double) evaluation
        :
          //convert Object to Integer
				  (
            (Integer) evaluation
          )
          .intValue()
        
          //convert int to double
          +0.0
			);
		}catch(javax.script.ScriptException e){
			//interpret invalid JavaScript expression as errno = -1
			return -1.0;
		}
  }
}