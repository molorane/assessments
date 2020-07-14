package assessment2;

/*
Regular expression for binary numbers divisible by 5
601790% of 129111 of 405aswitalski
Java

    Train
    Next Kata

    Details
    Solutions
    Forks (3)
    Discourse (22)

    Add to Collection
    |
    Share this kata:

Define a regular expression which tests if a given string representing a binary number is divisible by 5.
Examples:

// 5 divisible by 5
DivisibleByFive.pattern().matcher('101').matches() == true

// 135 divisible by 5
DivisibleByFive.pattern().matcher('10000111').matches() == true

// 666 not divisible by 5
DivisibleByFive.pattern().matcher('0000001010011010').matches() == false

Note:

This can be solved by creating a Finite State Machine that evaluates if a string representing a number in binary base is divisible by given number.

The detailed explanation for dividing by 3 is here

The FSM diagram for dividing by 5 is here

*/

public class BinaryExpression{
	// -	The finite automaton for multiples of 5 is via
	// -		http://jeffe.cs.illinois.edu/teaching/algorithms/models/03-automata.pdf , page 2
	// -	The prolog version of the automaton
	/*
	The finite automaton in prolog is the following
	-----------------------------------------------
		move(state0, 0, state0).
		move(state0, 1, state1).
		move(state1, 0, state2).
		move(state1, 1, state3).
		move(state2, 0, state4).
		move(state2, 1, state0).
		move(state3, 0, state1).
		move(state3, 1, state2).
		move(state4, 0, state3).
		move(state4, 1, state4).
		final(state0).
		solve(State, []):- final(State).
		solve(State, [X|Path]):-
			move(State, X, State1),
			solve(State1, Path)
		.
	*/
	// The translation of the finite automaton is via
	// -	Kleene's Theorem to a regular language string
	// -	0+|(0*1((0+11)(01*01)*01*00+10)*(0+11)(01*01)*10*)+
	// - Regular languages are a proper subset of regular expressions
	// -	Kleene star(*) maps to *
	// -	choice(binary +), to |
	// -	multiplicity(unary +), to +
	// -	binaryString.matches("0+|((0*1((0|11)(01*01)*01*00|10)*)(0|11)(01*01)*10*)+")
	public static void main(String ... args){
		for(int i=0; i<1000; i+=5){
			System.out.println(i+": "+Integer.toBinaryString(i)+": "+Integer.toBinaryString(i).matches("0+|((0*1((0|11)(01*01)*01*00|10)*)(0|11)(01*01)*10*)+"));
		}
	}
}