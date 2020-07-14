/*
Evaluate mathematical expression
5799093% of 497599 of 2,404ankr
Java

    Train
    Next Kata

    Details
    Solutions
    Forks (12)
    Discourse (215)

    Add to Collection
    |
    Share this kata:

Instructions

Given a mathematical expression as a string you must return the result as a number.
Numbers

Number may be both whole numbers and/or decimal numbers. The same goes for the returned result.
Operators

You need to support the following mathematical operators:

    Multiplication *
    Division / (as true division)
    Addition +
    Subtraction -

Operators are always evaluated from left-to-right, and * and / must be evaluated before + and -.
Parentheses

You need to support multiple levels of nested parentheses, ex. (2 / (2 + 3.33) * 4) - -6
Whitespace

There may or may not be whitespace between numbers and operators.

An addition to this rule is that the minus sign (-) used for negating numbers and parentheses will never be separated by whitespace. I.e., all of the following are valid expressions.

1-1    // 0
1 -1   // 0
1- 1   // 0
1 - 1  // 0
1- -1  // 2
1 - -1 // 2

6 + -(4)   // 2
6 + -( -4) // 10

And the following are invalid expressions

1 - - 1    // Invalid
1- - 1     // Invalid
6 + - (4)  // Invalid
6 + -(- 4) // Invalid

Validation

You do not need to worry about validation - you will only receive valid mathematical expressions following the above rules.

*/

//	The Kata is an exact duplicate of the Calculator Kata
//	Too, it duplicates the Find the unknown digit Kata in methodological
//	solving: both Kata's are solvable by
//	new javax.script.ScriptEngineManager()
//	.getEngineByName("Nashorn")
//	.eval(expression)
//	The very solution to the Calculator Kata has been submitted
//	For this Kata
public class EvaluateMathematicalExpression {
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
  public static void main(String ... args){
	  //System.out.println(evaluate("1 - -1"));
	  		char[] currentpattern = new char[10];
		java.util.Arrays.fill(currentpattern, '0');
		System.out.println(new String(currentpattern));
  }
}