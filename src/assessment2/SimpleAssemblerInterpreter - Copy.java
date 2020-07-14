/*
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runners.JUnit4;
import java.util.HashMap;
import java.util.Map;

public class SolutionTest {
  @Test
  public void simple_1() {
    String[] program = new String[]{"mov a 5","inc a","dec a","dec a","jnz a -1","inc a"}; 
    Map<String, Integer> out = new HashMap<String, Integer>();
    out.put("a", 1);
    assertEquals(out, SimpleAssembler.interpret(program));
  }
  
  @Test
  public void simple_2() {
    String[] program = new String[]{"mov a -10","mov b a","inc a","dec b","jnz a -2"};
    Map<String, Integer> out = new HashMap<String, Integer>();
    out.put("a", 0);
    out.put("b", -20);
    assertEquals(out, SimpleAssembler.interpret(program));
  }
}
*/

/*Simple assembler interpreter
1575195% of 282218 of 1,045ShinuToki
Java

    Train
    Next Kata

    Details
    Solutions
    Forks (2)
    Discourse (90)

    Add to Collection
    |
    Share this kata:

This is the first part of this kata series. Second part is here.

We want to create a simple interpreter of assembler which will support the following instructions:

    mov x y - copies y (either a constant value or the content of a register) into register x
    inc x - increases the content of register x by one
    dec x - decreases the content of register x by one
    jnz x y - jumps to an instruction y steps away (positive means forward, negative means backward), but only if x (a constant or a register) is not zero

Register names are alphabetical (letters only). Constants are always integers (positive or negative).

Note: the jnz instruction moves relative to itself. For example, an offset of -1 would continue at the previous instruction, while an offset of 2 would skip over the next instruction.

The function will take an input list with the sequence of the program instructions and will return a dictionary with the contents of the registers.

Also, every inc/dec/jnz on a register will always be followed by a mov on the register first, so you don't need to worry about uninitialized registers.
Example

SimpleAssebmler.interpret(new String[]{"mov a 5","inc a","dec a","dec a","jnz a -1","inc a"});

''' visualized:
mov a 5;
inc a;
dec a;
dec a;
jnz a -1;
inc a;
'''';

The above code will:

    set register a to 5,
    increase its value by 1,
    decrease its value by 2,
    then decrease its value until it is zero (jnz a -1 jumps to the previous instruction if a is not zero)
    and then increase its value by 1, leaving register a at 1

So, the function should return

{a=1}

This kata is based on the Advent of Code 2016 - day 12
*/

package assessment2;

public class SimpleAssemblerInterpreter{
  //- store regex patterns of instruction
	//- each dictionary key associates with at most one value
	//  - Dictionary has format: Dictionary<Key, Value>
	private static final java.util.Dictionary<String, String> instructionSet;
	
	//- the reference to the Nashorn scripting engine
	//  - Nashorn is the German equivalent for the English-word Rhino
	//  - Rhino is an open-source JavaScript engine written  in Java and managed by the Mozilla Foundation
	private static final javax.script.ScriptEngine Nashorn;


	//- set variables at class load time
	static{
		//- initial capacity is 4 dictionary entries + 12 blank leaflets
		instructionSet = new java.util.Hashtable<>(16);
		instructionSet.put(
			"mov",
			"mov\\s+(\\p{Alpha})\\s+(-?\\d+|\\p{Alpha})"
		);
		instructionSet.put("inc", "inc\\s+(\\p{Alpha})");
		instructionSet.put("dec", "dec\\s+(\\p{Alpha})");
		instructionSet.put(
			"jnz",
			"jnz\\s+(\\p{Alpha}|-?\\d+)\\s+(-*\\d+)"
		);
		
		//- Nashorn
		Nashorn =
			new javax.script.ScriptEngineManager()
			.getEngineByName("Nashorn")
		;
	}


	private static String translateInstructionListToJavaScript(String[] instructionList) throws Exception{
		String translation = "";
		int jnzCount = 0;
		int instructionCount = 0;
		
		//- translation
		//  - replace jnz instructions with non-grammatical tokens (tokens, _jnz, not in source-code grammar)
		//  - add non-grammatical references (: tokens not in source-code grammar)
		for(String instruction: instructionList){
			switch(instruction.substring(0, instruction.indexOf(" "))){
				case "mov":
					translation+=
						":"+instructionCount+++":"+
						instruction.replaceFirst(
							instructionSet.get("mov"),
							"$1"
						)+
						" = "+
						instruction.replaceFirst(
							instructionSet.get("mov"),
							"$2"
						)+
						"; "
					;
				break;
				
				case "inc":
					translation+=
						":"+instructionCount+++":"+
						"++"+
						instruction.replaceFirst(
							instructionSet.get("inc"),
							"$1"
						)+
						"; "
					;
				break;
				
				case "dec":
					translation+=
						":"+instructionCount+++":"+
						"--"+
						instruction.replaceFirst(
							instructionSet.get("dec"),
							"$1"
						)+
						"; "
					;
				break;
				
				case "jnz":
					translation+=
						":"+instructionCount+++":"+
						"if(0!="+
						instruction.replaceFirst(
							instructionSet.get("jnz"),
							"$1"
						)+
						") "+
						"_"+jnzCount+++"jnz("+
						instruction.replaceFirst(
							instructionSet.get("jnz"),
							"$2"
						)+
						"); "
					;
				break;
				
				default:
					throw new Exception()
				;
			}
		}
		
		//- translate jnz instructions
		
		//- all jnz instructions
		Object[] jnzSet = java.util.regex.Pattern.compile("; ")
			.splitAsStream(translation)
			.filter(w->w.matches(":\\d+:if.*_\\d+jnz\\(.*\\)"))
			.toArray()
		;
		
		//- associate each jnz instruction with a function
		String jnzFunctionSet = "";
		int instructionNumber = 0;
		int jnzDirection = 0;
		String functionDefinition = "";
		
		for(Object instructionString: jnzSet){
			
			//- set function signature
			//  - JavaScript: function f() is same as function f(x)
			jnzFunctionSet+=
				"function "+
				((String)instructionString)
				.replaceFirst(":\\d+:if.*(_\\d+)jnz\\(.*\\)", "$1")+
				"jnz()"
			;
				
			//- set function definition
			
			//- get relative number in instruction sequence
			instructionNumber =
				Integer.parseInt(
					((String)instructionString)
					.replaceFirst(":(\\d+):if.*_\\d+jnz\\(.*\\)", "$1")
				)
			;
			
			//- get jump number relative to self
			jnzDirection =
				Integer.parseInt(
					((String)instructionString)
					.replaceFirst(":\\d+:if.*_\\d+jnz\\((.*)\\)", "$1")
				)
			;
			
			//- evaluate sequence instruction of goto
			
			//- jump to past history
			if(jnzDirection<0){
				functionDefinition =
					translation.substring(
						translation.indexOf(
							":"+
							(instructionNumber+jnzDirection)+
							":"
						),
						translation.indexOf(
							"; ",
							translation.indexOf(":"+instructionNumber+":")
						)
					)+
					"; "
				;
			}
			//- jump to future instruction
			else{
				//- get conditional statement
				String conditionalStatement =
					translation.substring(
						translation.indexOf(
							":"+
							(instructionNumber+jnzDirection)+
							":"
						),
						translation.indexOf(
							"; ",
							translation.indexOf(
								":"+
								(instructionNumber+jnzDirection)+
								":"
							)
						)
					)+
					"; "
				;
				
				//- get unconditional statements
				String unconditional = "";
				try{
					unconditional =
						translation.substring(
							translation.indexOf(
								"; ",
								translation.indexOf(
									":"+
									(instructionNumber)+
									":"
								)
							)+2,
							translation.indexOf(
								":"+
								(instructionNumber+jnzDirection)+
								":"
							)
						)+
						"; "
					;
				}catch(StringIndexOutOfBoundsException e){
					//- null else section
				}
				
				functionDefinition =
					((String)instructionString)
					.replaceFirst(":\\d+:(if.*)_\\d+jnz\\(.*\\)", "$1")+
					conditionalStatement+
					" else { "+
					unconditional+
					conditionalStatement+
					"} "
				;
				
				try{
					translation =
						translation.substring(
							0,
							translation.indexOf(
								":"+
								(instructionNumber)+
								":"
							)
						)+
						((String)instructionString)
						.replaceFirst(":\\d+:(if.*_\\d+jnz\\(.*\\))", "$1")+
						translation.substring(
							translation.indexOf(
								"; ",
								translation.indexOf(
									":"+
									(instructionNumber+jnzDirection)+
									":"
								)+2
							)
						)
					;
				}catch(StringIndexOutOfBoundsException e){
					//- null else section
				}
			}
			
			jnzFunctionSet+=
				"{ "+
					functionDefinition+
				"} "
			;
		}
		
		return (jnzFunctionSet+translation).replaceAll(":\\d+:", "");
	}


  public static java.util.Map<String, Integer> interpret(final String[] program){
    try{
    
      Nashorn.eval(
        translateInstructionListToJavaScript(program)
      );
      
      //- persist engine environment to output Map
      java.util.Map<String, Integer> environment = new java.util.HashMap<>();
      for(
        String key:
        Nashorn.getBindings(javax.script.ScriptContext.ENGINE_SCOPE)
        .keySet()
      )
        if(!key.contains("jnz")){
          environment.put(
            key,
            new Integer(
              Nashorn.getBindings(javax.script.ScriptContext.ENGINE_SCOPE)
              .get(key)
              .toString()
              .replaceFirst("\\..*", "")
            )
          );
        }

		//- reset engine history to empty set
		Nashorn.getBindings(javax.script.ScriptContext.ENGINE_SCOPE).clear();
		
        return environment;
        
    }catch(javax.script.ScriptException e){
    
      //- interpret invalid script as error
      //return null;
      
    }catch(Exception e){
    
      //- interpret invalid script as error
      //return null;
      
    }
    
    return null;
  }

	
	public static void main(String ... args) throws Exception{
		System.out.println(
			translateInstructionListToJavaScript(
				new String[]{"mov a 1", "mov b 1", "mov c 0", "mov d 26", "jnz c 2", "jnz 1 5", "mov c 7", "inc d", "dec c", "jnz c -2", "mov c a", "inc a", "dec b", "jnz b -2", "mov b c", "dec d", "jnz d -6", "mov c 18", "mov d 11", "inc a", "dec d", "jnz d -2", "dec c", "jnz c -5"}
			)
		);

		try{
			java.util.Map<String, Integer> environment = interpret(
					new String[]{"mov a 1", "mov b 1", "mov c 0", "mov d 26", "jnz c 2", "jnz 1 5", "mov c 7", "inc d", "dec c", "jnz c -2", "mov c a", "inc a", "dec b", "jnz b -2", "mov b c", "dec d", "jnz d -6", "mov c 18", "mov d 11", "inc a", "dec d", "jnz d -2", "dec c", "jnz c -5"}
			);
			System.out.println(
				Nashorn.getBindings(javax.script.ScriptContext.ENGINE_SCOPE).size()
			)
			;
			for(String key: environment.keySet())
			System.out.println(
				key+"= "+environment.get(key)
			)
			;
		}catch(Exception e){
		}
		
	}
	
}

/*
{"mov a 1", "mov b 1", "mov c 0", "mov d 26", "jnz c 2", "jnz 1 5", "mov c 7", "inc d", "dec c", "jnz c -2", "mov c a", "inc a", "dec b", "jnz b -2", "mov b c", "dec d", "jnz d -6", "mov c 18", "mov d 11", "inc a", "dec d", "jnz d -2", "dec c", "jnz c -5"}
expected:<{a=318009, b=196418, c=0, d=0}> but was:<null>

{"mov d 100", "dec d", "mov b d", "jnz b -2", "inc d", "mov a d", "jnz 5 10", "mov c a"}
expected:<{a=1, b=0, d=1}> but was:<null>

{"mov c 12", "mov b 0", "mov a 200", "dec a", "inc b", "jnz a -2", "dec c", "mov a b", "jnz c -5", "jnz 0 1", "mov c a"}
expected:<{a=409600, b=409600, c=409600}> but was:<{a=409600, b=409600, c=409600, d=0}>

*/